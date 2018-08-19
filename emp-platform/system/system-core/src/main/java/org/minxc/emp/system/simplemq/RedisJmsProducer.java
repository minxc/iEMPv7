package org.minxc.emp.system.simplemq;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import org.minxc.emp.system.api.jms.model.JmsDTO;
import org.minxc.emp.system.api.jms.producer.JmsProducer;


@Service("Service")
public class RedisJmsProducer implements JmsProducer {
   @Resource
    private RedisTemplate redisTemplate;
	   
	@Override
	public void sendToQueue(JmsDTO message) {
        redisTemplate.convertAndSend("message", message);
    }

	@Override
	public void sendToQueue(List<JmsDTO> jsmDtos) {
		for(JmsDTO dto : jsmDtos) {
			this.sendToQueue(dto);
		}
	}

}
