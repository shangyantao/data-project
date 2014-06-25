package com.sap.data.app.jms;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import com.sap.data.app.entity.event.E0001;

/**
 * JMS用户变更消息生产者.
 * 
 * 使用jmsTemplate将用户变更消息分别发送到queue与topic.
 * 
 */

public class NotifyMessageProducer {

	private JmsTemplate jmsTemplate;
	private Destination notifyQueue;
	private Destination notifyTopic;

	public void sendQueue(final E0001 e0001) {
		sendMessage(e0001, notifyQueue);
	}

	public void sendTopic(final E0001 e0001) {
		sendMessage(e0001, notifyTopic);
	}

	/**
	 * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
	 */
	private void sendMessage(E0001 e0001, Destination destination) {
		System.out.println(e0001.getRelGroup()+"-----------");
		Map map = new HashMap();
		map.put("relGroup", e0001.getRelGroup());
		map.put("relCode", e0001.getRelCode());
		map.put("itemForRelease", e0001.getItemsForRelease());

		jmsTemplate.convertAndSend(destination, map);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

	public void setNotifyTopic(Destination nodifyTopic) {
		this.notifyTopic = nodifyTopic;
	}
}
