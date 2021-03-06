package org.minxc.emp.form.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.form.api.model.FormDef;
import org.minxc.emp.form.api.service.FormService;
import org.minxc.emp.form.manager.FormDefManager;


@Service("formService")
public class DefaultFormServiceImpl implements FormService {

    @Resource
    private FormDefManager formDefManager;

    public FormDef getByFormKey(String formKey) {
        FormDef form = formDefManager.getByKey(formKey);
        return form;
    }


    @Override
    public FormDef getByFormId(String formId) {
        return formDefManager.get(formId);
    }


    @Override
    public String getFormExportXml(Set<String> formKeys) {
        List<String> id = new ArrayList<String>();
        for (String formKey : formKeys) {
            org.minxc.emp.form.model.FormDef form = formDefManager.getByKey(formKey);
            id.add(form.getId());
        }
        //	Map<String,String> map = formDefManager.exportForms(id, false);

        return null;//map.get("form.xml");
    }


    @Override
    public void importForm(String formXmlStr) {
        try {
            //formDefManager.importByFormXml(formXmlStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导入表单失败" + e.getMessage(), e);
        }
    }

}
