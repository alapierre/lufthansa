/**
 * Copyright 2016-02-15 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import org.apache.log4j.Logger;

import pl.com.softproject.lilu.model.order.Order;
import pl.szkolenie.orders.OrderSerializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class OrderListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(OrderListener.class);

    private OrderSerializer serializer = new OrderSerializer();

    public void onMessage(Message message) {

        try {

            if(message instanceof TextMessage ) {

                if("XML".equals(message.getStringProperty("format"))) {
                    String content = ((TextMessage) message).getText();

                    Order order = serializer.fromString(content, false);

                    String val = order.getComments().getComment().get(0).getValue();

                    System.out.println(val);

                } else {
                    logger.debug("not XML format message");
                }
            } else {

                System.out.println(message);

                logger.debug("not text message");
            }


        } catch (JMSException ex) {

        }

    }
}
