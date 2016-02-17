/**
 * Copyright 2016-02-16 the original author or authors.
 */
package pl.com.softproject.esb.jmx.util;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class MessageConverter {

    public static String convertMessage(Message message) {

        try {

            if (message instanceof TextMessage)
                return ((TextMessage) message).getText();
            else if(message instanceof BytesMessage)
                return convertBytesMessage((BytesMessage)message, "UTF-8");
            else throw new IllegalArgumentException("Unsupported message type [" + message.getClass() + "]. MessageConverter by default only supports TextMessages and BytesMessages.");

        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static String convertBytesMessage(BytesMessage message) {
        return convertBytesMessage(message, "UTF-8");
    }

    public static String convertBytesMessage(BytesMessage message, String encoding) {

        if(message instanceof BytesMessage) {

            BytesMessage byteMessage = (BytesMessage) message;
            try {
                byte[] byteArr = new byte[(int)byteMessage.getBodyLength()];
                byteMessage.readBytes(byteArr);
                String msg = new String(byteArr, encoding);

               return msg;

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("not a BytesMessage");
        }

    }

}
