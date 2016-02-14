/**
 * Copyright 2016-02-13 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import pl.com.softproject.esb.jmx.model.Person;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class SendBean {

    public static void main(String[] args) {

        //MapMessageConverter

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("sender-context.xml");

        JmsTemplate jms = ctx.getBean(JmsTemplate.class);

        final Person person = new Person();

        person.setAge(11);
        person.setLastName("Kowalski");
        person.setName("Jan");

        jms.convertAndSend(person, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {

                //message.setStringProperty("format", "JSON");
                message.setJMSType("pl.com.softproject.esb.jmx.model.Person");
                return message;
            }
        });

    }

}
