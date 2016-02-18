/**
 * Copyright 2016-02-18 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.com.softproject.esb.jmx.model.Person;
import pl.com.softproject.esb.jmx.util.MessageConverter;

import javax.jms.BytesMessage;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class JsonListener implements MessageListener {

    private ObjectMapper objectMapper = new ObjectMapper();

    public void onMessage(Message message) {

        if(message instanceof BytesMessage) {
            String value = MessageConverter.convertBytesMessage((BytesMessage) message);

            try {
                Person person = objectMapper.readValue(value, Person.class);
                System.out.println(person);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        } else {
            System.out.println("nie ten typ wiadomości " + message.getClass());
        }

    }
}
