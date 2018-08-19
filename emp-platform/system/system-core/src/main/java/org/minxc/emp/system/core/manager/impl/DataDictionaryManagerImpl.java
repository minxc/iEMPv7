package org.minxc.emp.system.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.system.core.dao.DataDictDao;
import org.minxc.emp.syetem2.manager.SystemTreeManager;
import org.minxc.emp.syetem2.manager.SystemTreeNodeManager;
import org.minxc.emp.syetem2.model.SystemTree;
import org.minxc.emp.syetem2.model.SystemTreeNodeImpl;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.query.DefaultQueryField;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.api.constant.SystemStatusCode;
import org.minxc.emp.system.core.model.DataDict;
import org.minxc.emp.system.core.manager.DataDictManager;
/**
 * 数据字典 Manager处理实现类
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-05-16 14:39:58
 */
@Service("dataDictManager")
public class DataDictionaryManagerImpl extends BaseManager<String, DataDict> implements DataDictManager{
	@Resource
    private DataDictDao dataDictDao;
	@Resource
	private SystemTreeNodeManager sysTreeNodeMananger;
	@Resource
	private  SystemTreeManager sysTreeMananger;
	

	@Override
	public List<DataDict> getDictNodeList(String dictKey,Boolean hasRoot) {
		return dataDictDao.getDictNodeList(dictKey,hasRoot);
	}
	
	
	@Override
	public void create(DataDict dataDict) {
		Integer count = 0 ;
		if(DataDict.TYPE_DICT.equals(dataDict.getDictType())) {
			dataDict.setDictKey(dataDict.getKey());
			count = dataDictDao.isExistDict(dataDict.getKey(),null);
		}else {
			count = dataDictDao.isExistNode(dataDict.getDictKey(),dataDict.getKey() , null);
		}
		
		if(count != 0) {
			throw new BusinessException(dataDict.getKey()+"字典已经存在",SystemStatusCode.PARAM_ILLEGAL);
		}
		
		super.create(dataDict);
	}
	
	
	@Override
	public void update(DataDict dataDict) {
		int count = 0 ;
		if(DataDict.TYPE_DICT.equals(dataDict.getDictType())) {
			dataDict.setDictKey(dataDict.getKey());
			count = dataDictDao.isExistDict(dataDict.getKey(),dataDict.getId());
		}else {
			count = dataDictDao.isExistNode(dataDict.getKey(), dataDict.getDictKey(), dataDict.getId());
		}
		
		if(count != 0) {
			throw new BusinessException(dataDict.getKey()+"字典Key已经存在",SystemStatusCode.PARAM_ILLEGAL);
		}
		
		super.update(dataDict);
	}


	@Override
	public JSONArray getDictTree() {
		QueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("dict_type_", "dict", QueryOperation.EQUAL);
		
		List<DataDict> dicts = dataDictDao.query(filter);
		
		SystemTree sysTree = sysTreeMananger.getByKey("dict");
		List<SystemTreeNodeImpl> nodeList = sysTreeNodeMananger.getByTreeId(sysTree.getId());
		JSONArray jsonArray = new JSONArray();
		
		for(SystemTreeNodeImpl sysTreeNode : nodeList) {
			JSONObject object = new JSONObject();
			object.put("id", sysTreeNode.getId());
			object.put("name", sysTreeNode.getName());
			object.put("parentId", sysTreeNode.getParentId());
			object.put("type", "type");
			object.put("noclick",true);
			jsonArray.add(object);
		}
		
		for(DataDict dict : dicts) {
			JSONObject object = new JSONObject();
			object.put("id", dict.getId());
			object.put("name", dict.getName());
			object.put("key", dict.getDictKey());
			object.put("icon", "fa-check-square-o");
			object.put("parentId", dict.getTypeId());
			object.put("type", "dict");
			jsonArray.add(object);
		}
		
		return jsonArray;
	}

}
