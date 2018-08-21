package org.minxc.emp.base.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.model.BaseModel;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.manager.Manager;
import com.github.pagehelper.Page;


/**
 * 
* 项目名称：base-rest   
* 类名称：BaseController   
* 类描述： controller的基础类 , 包含了常用的增删查改的方法，需要定制化可自行覆盖
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:10:21   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:10:21   
* 修改备注：   
* @version  1.0  
*
 */
public abstract class BaseController<T extends BaseModel> extends GenericController{
	
    protected abstract String getModelDesc();

    @Autowired
    Manager<String,T> manager;
    
    /**
     * 分页列表
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<T> pageList = (Page<T>) manager.query(queryFilter);
        return new PageJson(pageList);
    }

    /**
     * 获取对象
     */
    @RequestMapping("get")
    @CatchError
    public ResultMsg<T> get(@RequestParam String id) throws Exception {
       T t = manager.get(id);
       return getSuccessResult(t);
    }

    /**
     * 保存
     */
    @RequestMapping("save")
    @CatchError
    public ResultMsg<String> save(@RequestBody T t) throws Exception {
        String desc;
        if (StringUtil.isEmpty(t.getId())) {
            desc = "添加%s成功";
            manager.create(t);
        } else {
            manager.update(t);
            desc = "更新%s成功";
        }
        return getSuccessResult(String.format(desc, getModelDesc()));
    }

    /**
     * 批量删除
     */
    @RequestMapping("remove")
    @CatchError
    public ResultMsg<String> remove(@RequestParam String id) throws Exception {
         String[] aryIds = StringUtil.getStringAryByStr(id);
         manager.removeByIds(aryIds);
         return getSuccessResult(String.format("删除%s成功", getModelDesc()));
    }
  
}