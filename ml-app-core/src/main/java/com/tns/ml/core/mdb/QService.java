package com.tns.ml.core.mdb;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class QService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QService.class);

	@Resource(mappedName = "java:/jms/queue/OEDesignQueue")
	private Queue oedQueue;

	@Resource(mappedName = "java:/ConnectionFactory")
	// @Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory cf;

	private void enque(Serializable design) {
		try (Connection connection = cf.createConnection()) {

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer publisher = session.createProducer(oedQueue);

			connection.start();

			ObjectMessage msg = session.createObjectMessage(design);
			publisher.send(msg);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
