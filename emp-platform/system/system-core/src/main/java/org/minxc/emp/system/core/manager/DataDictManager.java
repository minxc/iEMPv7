package org.minxc.emp.system.core.manager;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.system.core.model.DataDict;

/**
 * 数据字典 Manager处理接口
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-05-16 14:39:58
 */
public interface DataDictManager extends Manager<String, DataDict>{

	List<DataDict> getDictNodeList(String dictKey, Boolean hasRoot);

	JSONArray getDictTree();
	
}
