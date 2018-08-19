package org.minxc.emp.system.simplemq.handler.msg;

import java.util.List;

import org.springframework.stereotype.Component;

import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.system.api.jms.model.msg.NotifyMessage;

/**
 * 短消息发送处理器。
 */
@Component
public class SmsHandler extends AbsNotifyMessageHandler<NotifyMessage> {

    @Override
    public String getType() {
        return "sms";
    }

    @Override
    public boolean sendMessage(NotifyMessage message) {
    	
        // 调用阿里大于
        List<User> recievers =null; message.getReceivers();
        String content = message.getTextContent();
        String templateCode = null;//message.getSmsTemplateNo();

        if (StringUtil.isEmpty(content) || BeanUtils.isEmpty(recievers)) return false;


        for (User user : recievers) {
            if (StringUtil.isEmpty(user.getMobile())) continue;
			
	/*		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend(alidayuSetting.getExtend());
			req.setSmsType("normal");
			req.setSmsFreeSignName(alidayuSetting.getFreeSignName());
			
			String parmString =TaoBaoUtil.buildParams(vo);
			req.setSmsParamString(parmString);
			req.setRecNum(user.getMobile());
			req.setSmsTemplateCode(templateCode);
			try {
				AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			} catch (ApiException e) {
				e.printStackTrace();
			}*/
        }
        return true;
    }

    @Override
    public String getTitle() {
        return "短信";
    }

    @Override
    public boolean getIsDefault() {
        return false;
    }

    @Override
    public boolean getSupportHtml() {
        return true;
    }

}
