/**
 * Copyright 2016-02-13 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class MyMessageListener implements MessageListener {

    private MessageConverter messageConverter;

    public void onMessage(Message message) {

        System.out.println("wiadomosc:");

        try {

            if (message instanceof TextMessage) {

                System.out.println(((TextMessage) message).getText());

            } else {
                System.out.println("wiadomośc nie tekstowa");
                System.out.println(message.getClass());

                if(messageConverter != null) {
                    Object res = messageConverter.fromMessage(message);
                    System.out.println(res);
                } else {
                    System.out.println("brak konwertera");
                }


            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }

    }

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }
}
