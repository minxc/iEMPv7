package org.minxc.emp.system.api.jms.producer;

import java.util.List;

import org.minxc.emp.system.api.jms.model.JmsDTO;

public interface JmsProducer {

	void sendToQueue(JmsDTO message);

	void sendToQueue(List<JmsDTO> jmsDto);

}