package com.mx.srk.ivs.ejb;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.mx.srk.ivs.ws.JourneyEndPoint;

/**
 * Message-Driven Bean implementation class for: JourneyMDB
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "JourneyTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") }, mappedName = "jms/JourneyTopic")
@ResourceAdapter("activemq-rar.rar")
public class JourneyMDB implements MessageListener {

	/**
	 * Default constructor.
	 */
	public JourneyMDB() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("Message Received:" + textMessage.getText());
			JourneyEndPoint.updateAll(textMessage.getText());
		} catch (JMSException | IOException e) {
			e.printStackTrace();
		}
	}

}
