package org.minxc.emp.system.simplemq.handler.biz;

import java.io.Serializable;

import org.minxc.emp.system.api.jms.consumer.JmsConsumer;
/**
 * 做公共逻辑,如日志等
 * @author min.xianchang
 *
 */
public abstract class AbsBizMessageConsumer<T extends Serializable> implements JmsConsumer<T>{

}
