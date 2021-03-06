package org.minxc.emp.bpm.api.model.nodedef;

import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnDef;
import org.minxc.emp.bpm.api.model.def.BpmnProcessDef;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.form.BpmnForm;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：流程任务节点
 */
public interface BpmnNodeDef extends Serializable, BpmnDef {
    /**
     * 取得节点的ID
     *
     * @return
     */
    String getNodeId();

    /**
     * 取得节点的名称
     *
     * @return
     */
    String getName();

    /**
     * 取得节点的类型
     *
     * @return
     */
    NodeType getType();


    /**
     * 设置节点的类型
     *
     * @return
     */
    void setType(NodeType type);

    /**
     * 取得当前节点的所有入口节点集合
     *
     * @return
     */
    List<BpmnNodeDef> getIncomeNodes();

    /**
     * 取得当前节点的所有出口节点集合
     *
     * @return
     */
    List<BpmnNodeDef> getOutcomeNodes();

    /**
     * 获取跳出的任务节点
     *
     * @return List&lt;BpmNodeDef>
     */
    List<BpmnNodeDef> getOutcomeTaskNodes();

    /**
     * 取得当前节点的后续任务节点，只包括流程中的任务节点，不包括内部子流程和外部子流程的节点。
     *
     * @param includeSignTask 是否包含会签节点。
     * @return List&lt;BpmNodeDef>
     */
    List<BpmnNodeDef> getInnerOutcomeTaskNodes(boolean includeSignTask);

    /**
     * 取得节点的事件插件
     *
     * @return List&lt;BpmNodePlugin>
     */
    List<BpmnPluginContext> getBpmPluginContexts();


    /**
     * 获取节点所在的流程定义。
     *
     * @return BpmProcessDef
     * @throws
     * @since 1.0.0
     */
    BpmnProcessDef getBpmProcessDef();

    /**
     * 获得属性
     *
     * @return List&lt;String>
     */
    String getAttribute(String name);

    /**
     * 添加入口的节点定义。
     *
     * @param bpmNodeDef void
     */
    void addIncomeNode(BpmnNodeDef bpmNodeDef);

    /**
     * 添加出口的节点定义。
     *
     * @param bpmNodeDef void
     */
    void addOutcomeNode(BpmnNodeDef bpmNodeDef);

    /**
     * 获取上级节点。
     *
     * @return BpmProcessDef
     */
    BpmnNodeDef getParentBpmNodeDef();

    /**
     * 取得实际的路径。
     *
     * @return String
     */
    String getRealPath();

    /**
     * 获取根的流程定义。
     *
     * @return BpmProcessDef
     */
    BpmnProcessDef getRootProcessDef();

    /**
     * 获取表单定义。
     *
     * @return Form
     */
    BpmnForm getForm();


    BpmnForm getMobileForm();

    /**
     * 根据插件类名获取插件实例上下文定义。
     *
     * @param cls
     * @return BpmPluginContext
     */
    <T> T getPluginContext(Class<T> cls);

    /**
     * 获取节点属性
     *
     * @return
     */
    NodeProperties getNodeProperties();

    /**
     * 获取节点按钮。
     *
     * <pre>
     * 1.获取节点配置按钮数据。
     * 2.如果获取不到,则获取节点的默认按钮。
     * </pre>
     *
     * @return List&lt;Button>
     */
    List<Button> getButtons();
}
