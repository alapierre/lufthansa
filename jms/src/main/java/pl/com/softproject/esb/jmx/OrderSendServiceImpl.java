/**
 * Copyright 2016-02-15 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import pl.com.softproject.lilu.model.order.Comments;
import pl.com.softproject.lilu.model.order.Order;
import pl.szkolenie.orders.OrderSerializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
@Service
public class OrderSendServiceImpl implements OrderSendService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendSampleOrder() {

        OrderSerializer serializer = new OrderSerializer();

        Order order = new Order();

        Comments.Comment comment = new Comments.Comment();
        comment.setType("type");
        comment.setValue("skksksks");


        Comments c = new Comments();
        c.getComment().add(comment);

        order.setComments(c);

        final String content = serializer.toString(order, false);

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(content);

                message.setStringProperty("format", "XML");

                return message;
            }
        });

    }

}
