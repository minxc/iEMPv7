package org.minxc.emp.system.simplemq.handler.msg;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import org.minxc.emp.base.core.util.PropertyUtil;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.system.api.jms.model.msg.NotifyMessage;
import org.minxc.emp.system.api.service.SysIdentityConvert;
import org.minxc.emp.system.util.EmailUtil;

/**
 * 邮件消息处理器。
 */
@Component
public class MailHandler extends AbsNotifyMessageHandler<NotifyMessage> {
	@Resource
	SysIdentityConvert sysIdentityConvert;
	
    @Override
    public String getType() {
        return "email";
    }


    @Override
    public String getTitle() {
        return "邮件";
    }


    @Override
    public boolean getSupportHtml() {
        return true;
    }

	@Override
	public boolean sendMessage(NotifyMessage notifMessage) {
		String fromEmail = PropertyUtil.getProperty("mail.address");
	  
        List<User> recievers = sysIdentityConvert.convert2Users(notifMessage.getReceivers());
        
        for (User reciver : recievers) {
            String email = reciver.getEmail();
            if (StringUtil.isEmpty(email)) continue;
            try {
                EmailUtil.sendEmail(email, "", "", fromEmail, notifMessage.getSubject(), notifMessage.getHtmlContent());
            } catch (MessagingException e) {
            	LOG.error(JSON.toJSONString(notifMessage));
            	LOG.error("发送邮件失败！",e);
            }
        }
        LOG.debug("发送邮件成功 ：{}",JSON.toJSONString(notifMessage));
        return true;
	}

}
