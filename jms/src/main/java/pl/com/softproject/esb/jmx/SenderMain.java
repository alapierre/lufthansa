/**
 * Copyright 2016-02-13 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class SenderMain {

    public static void main(String[] args) {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("sender-context.xml");

        JmsTemplate jms = ctx.getBean(JmsTemplate.class);

        jms.send(new MessageCreator() {

            public Message createMessage(Session sn) throws JMSException {
                TextMessage message = sn.createTextMessage("ala ma kota");
                message.setStringProperty("format", "TEXT");

                return message;
            }
        });
    }

}
