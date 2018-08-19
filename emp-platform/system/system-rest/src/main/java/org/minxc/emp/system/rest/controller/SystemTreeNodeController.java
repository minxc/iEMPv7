package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.syetem2.manager.SystemTreeManager;
import org.minxc.emp.syetem2.manager.SystemTreeNodeManager;
import org.minxc.emp.syetem2.model.SystemTreeNodeImpl;

/**
 * <pre>
 * 描述：sysTreeNode层的controller
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:下午5:11:06
 * 版权:summer
 * </pre>
 */
@Controller
@RequestMapping("/sys/sysTreeNode/")
public class SystemTreeNodeController extends GenericController {


    @Autowired
   private SystemTreeManager sysTreeManager;
    @Autowired
    private SystemTreeNodeManager sysTreeNodeManager;

    /**
     * <pre>
     * sysTreeEdit.html的saveNode后端
     * 保存树节点
     * </pre>
     *
     * @param request
     * @param response
     * @param sysTreeNode
     * @throws Exception
     */
    @RequestMapping("save")
    @CatchError(write2response = true, value = "保存系统树节点失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemTreeNodeImpl sysTreeNode) throws Exception {
        if (StringUtil.isEmpty(sysTreeNode.getId())) {
            sysTreeNode.setId(UniqueIdUtil.getSuid());
            handleNewSysTreeNode(sysTreeNode);
            sysTreeNodeManager.create(sysTreeNode);
        } else {
            sysTreeNodeManager.update(sysTreeNode);
        }
        writeSuccessData(response, sysTreeNode, "保存系统树节点成功");
    }

    /**
     * <pre>
     * 获取sysTreeNode的后端
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getNodes")
    @ResponseBody
    public List<SystemTreeNodeImpl> getNodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SystemTreeNodeImpl> nodes = null;
        String treeId = RequestUtil.getString(request, "treeId");
        String key = RequestUtil.getString(request, "nodeKey");
        String treeKey = RequestUtil.getString(request, "treeKey");
        if (StringUtil.isNotEmpty(treeKey) && StringUtil.isEmpty(treeId)) {
            treeId = sysTreeManager.getByKey(treeKey).getId();
        }

        if (StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(treeId)) {
            SystemTreeNodeImpl node = sysTreeNodeManager.getByTreeIdAndKey(treeId, key);
            nodes = sysTreeNodeManager.getStartWithPath(node.getPath());
        } else if (StringUtil.isNotEmpty(treeId)) {
            nodes = sysTreeNodeManager.getByTreeId(treeId);
        }
        return nodes;
    }

    /**
     * <pre>
     * 批量删除
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("remove")
    @CatchError(write2response = true, value = "删除系统树节点失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        for (String id : aryIds) {
            SystemTreeNodeImpl node = sysTreeNodeManager.get(id);
            sysTreeNodeManager.removeByPath(node.getPath()+"%");
        }
        writeSuccessResult(response, "删除系统树节点成功");
    }

    /**
     * <pre>
     * 处理一下新节点的数据
     * </pre>
     *
     * @param sysTreeNode
     */
    private void handleNewSysTreeNode(SystemTreeNodeImpl sysTreeNode) {
        // 新增时处理一下path
        if (StringUtil.isNotEmpty(sysTreeNode.getPath())) {
            sysTreeNode.setPath(sysTreeNode.getPath() + sysTreeNode.getId() + ".");
        } else {
            sysTreeNode.setPath(sysTreeNode.getId() + ".");
        }

        // 新增处理sn
        // 获取同级节点
        List<SystemTreeNodeImpl> nodes = sysTreeNodeManager.getByParentId(sysTreeNode.getParentId());
        sysTreeNode.setSn(nodes.size() + 1);
    }
}
