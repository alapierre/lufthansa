package pl.szkolenie.orders;

import org.junit.Test;

import pl.com.softproject.lilu.model.order.Comments;
import pl.com.softproject.lilu.model.order.Order;

import java.io.File;

/**
 * Copyright 2016-02-15 the original author or authors.
 */
public class OrderSerializerTest {

    @Test
    public void testFromFile() {

        OrderSerializer serializer = new OrderSerializer();

        serializer.fromFile(new File("src/main/resources/za-pobraniem.xml"));

    }

    @Test
    public void testToFile() {

        OrderSerializer serializer = new OrderSerializer();

        Order order = new Order();

        Comments.Comment comment = new Comments.Comment();
        comment.setType("type");
        comment.setValue("skksksks");


        Comments c = new Comments();
        c.getComment().add(comment);

        order.setComments(c);

        serializer.toFile(order, "order.xml", false);

    }

}