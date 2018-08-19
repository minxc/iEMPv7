package org.minxc.emp.system.groovy;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.minxc.emp.system.api.groovy.GroovyScriptEngine;
import org.minxc.emp.system.api.groovy.Script;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import org.minxc.emp.base.core.util.AppUtil;

import groovy.lang.GroovyShell;

/**
 * 脚本引擎用于执行groovy脚本。<br/>
 * 实现了IScript接口的类。 可以在脚本中使用。
 */
@Component
public class GroovyScriptEngineImpl implements GroovyScriptEngine, ApplicationListener{

    private Log logger = LogFactory.getLog(GroovyScriptEngineImpl.class);
    private GroovyBinding groovyBinding = new GroovyBinding();
    
    @Override
    public void execute(String script) {
        executeObject(script, null);
    }

    @Override
    public void execute(String script, Map<String, Object> vars) {
        executeObject(script, vars);
    }

    @Override
    public boolean executeBoolean(String script, Map<String, Object> vars) {
        return (Boolean) executeObject(script, vars);
    }
 
    @Override
    public String executeString(String script, Map<String, Object> vars) {
        return (String) executeObject(script, vars);
    }

   
    @Override
    public int executeInt(String script, Map<String, Object> vars) {
        return (Integer) executeObject(script, vars);
    }
 
    @Override
    public float executeFloat(String script, Map<String, Object> vars) {
        return (Float) executeObject(script, vars);
    }

    @Override
    public Object executeObject(String script, Map<String, Object> vars) {
        //在执行时清除流程变量。
        groovyBinding.clearVariables();
        
        if(logger.isDebugEnabled()) {
        	logger.debug("执行:" + script);
        	logger.debug("variables:" +vars+"");
        }
        GroovyShell shell = new GroovyShell(groovyBinding);
        this.setParameters(shell, vars);

        script = script.replace("&apos;", "'").replace("&quot;", "\"")
                .replace("&gt;", ">").replace("&lt;", "<")
                .replace("&nuot;", "\n").replace("&amp;", "&");

        Object rtn = shell.evaluate(script);
        return rtn;
    }

    /**
     * 设置执行参数。
     *
     * @param shell
     * @param vars
     */
    private void setParameters(GroovyShell shell, Map<String, Object> vars) {
        if (vars == null) return;
        Set<Map.Entry<String, Object>> set = vars.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it .hasNext();){
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it .next();
            shell.setVariable(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public void onApplicationEvent(ApplicationEvent arg0) {
    	Map<String, Script> scirptImpls =	AppUtil.getImplInstance(Script.class);
    	for(Entry<String, Script> scriptMap : scirptImpls.entrySet()) {
    		groovyBinding.setProperty(scriptMap.getKey(), scriptMap.getValue());
    	}
    }
}