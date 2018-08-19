package org.minxc.emp.system.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.system.api.service.SerialNoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.system.api.constant.SystemStatusCode;
import org.minxc.emp.system.core.manager.SerialNoManager;
import org.minxc.emp.system.core.model.SerialNo;
import com.github.pagehelper.Page;

/**
 * 流水号生成管理
 */
@RestController
@RequestMapping("/sys/serialNo/")
public class SystemSerialNoController extends GenericController {
    @Resource
    private SerialNoManager serialNoManager;
    @Resource
    private SerialNoService serialNoService;


    /**
     * 流水号生成列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SerialNo> SerialNoList = (Page<SerialNo>) serialNoManager.query(queryFilter);
        return new PageJson(SerialNoList);
    }

    public static void main(String[] args) {

    }

    /**
     * 根据ID获取内容
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    @CatchError(write2response = true)
    public void getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SerialNo SerialNo = serialNoManager.get(id);
        writeSuccessData(response, SerialNo);
    }

    /**
     * 保存流水号生成信息
     *
     * @param request
     * @param response
     * @param SerialNo
     * @throws Exception void
     */
    @RequestMapping("save")
    @CatchError
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SerialNo SerialNo) throws Exception {
        String resultMsg = null;

        boolean rtn = serialNoManager.isAliasExisted(SerialNo.getId(), SerialNo.getAlias());
        if (rtn) {
            throw new BusinessException("别名已经存在!", SystemStatusCode.SERIALNO_EXSIT);
        }

        if (StringUtil.isEmpty(SerialNo.getId())) {
            serialNoManager.create(SerialNo);
            resultMsg = "添加流水号生成成功";
        } else {
            serialNoManager.update(SerialNo);
            resultMsg = "更新流水号生成成功";
        }

        writeSuccessResult(response, resultMsg);
    }


    /**
     * 批量删除流水号生成记录
     *
     * @param request
     * @param response
     * @throws Exception void
     */
    @RequestMapping("remove")
    @CatchError("删除流水号失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");

        serialNoManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除流水号成功");
    }

    /**
     * 取得流水号生成分页列表
     *
     * @param request
     * @param response
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("showlist")
    public PageJson showlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SerialNo> SerialNoList = (Page<SerialNo>) serialNoManager.query(queryFilter);
        return new PageJson(SerialNoList);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("preview")
    public List<SerialNo> preview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String alias = RequestUtil.getString(request, "alias");
        List<SerialNo> identities = serialNoManager.getPreviewIden(alias);

        return identities;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getNextIdByAlias")
    public ResultMsg<String> getNextIdByAlias(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String alias = RequestUtil.getString(request, "alias");
        if (serialNoManager.isAliasExisted(null, alias)) {
            String nextId = serialNoService.genNextNo(alias);
            return getSuccessResult(nextId, "请求成功");
        }
        return getSuccessResult("", "请求成功");
    }
}
