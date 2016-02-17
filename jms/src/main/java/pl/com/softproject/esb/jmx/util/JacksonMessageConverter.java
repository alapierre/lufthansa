/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.jmx.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jms.support.converter.MessageConversionException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class JacksonMessageConverter {

    private String encoding = "UTF-8";
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T  fromMessage(Message message, Class<? extends T> targetJavaType)
            throws JMSException, MessageConversionException, IOException {

        if(message instanceof TextMessage)
            return convertFromTextMessage((TextMessage)message, targetJavaType);
        else if(message instanceof BytesMessage) {
            return convertFromBytesMessage((BytesMessage)message, targetJavaType);
        } else throw new IllegalArgumentException("Unsupported message type [" + message.getClass() + "]. JacksonMessageConverter by default only supports TextMessages and BytesMessages.");
    }

    protected <T> T convertFromTextMessage(TextMessage message, Class<? extends T> targetJavaType) throws JMSException, IOException {
        String body = message.getText();
        return this.objectMapper.readValue(body, targetJavaType);
    }

    protected <T> T convertFromBytesMessage(BytesMessage message, Class<? extends T> targetJavaType) throws JMSException,
                                                                                                   IOException {
        String encoding = this.encoding;

        byte[] bytes = new byte[(int)message.getBodyLength()];
        message.readBytes(bytes);

        try {
            String content = new String(bytes, encoding);
            return this.objectMapper.readValue(content, targetJavaType);
        } catch (UnsupportedEncodingException ex) {
            throw new MessageConversionException("Cannot convert bytes to String", ex);
        }
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
