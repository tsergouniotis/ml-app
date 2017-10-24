package com.tns.ml.core.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "BookingQueueReceiver", activationConfig = { @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/IrisQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), })
public class OEDMessageReceiverMDB implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(OEDMessageReceiverMDB.class);

	@Inject
	private QService oedService;

	@Override
	public void onMessage(Message message) {
		try {

			Object design = ObjectMessage.class.cast(message).getBody(Object.class);

			// TODO do something

		} catch (Exception e) {
			LOGGER.error("Error during processing the JMS message OED Design", e);
		}

	}

}
