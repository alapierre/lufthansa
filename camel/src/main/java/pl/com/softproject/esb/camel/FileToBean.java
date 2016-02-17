/**
 * Copyright 2016-02-10 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.esb.processor.BeanProcessor;

import javax.jms.ConnectionFactory;
import java.util.Scanner;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class FileToBean {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        final BeanProcessor beanProcessor = new BeanProcessor();

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file://d:/orders?charset=UTF-8")
                        .to("test-jms:queue:test.queue");

                from("test-jms:queue:test.queue").bean(beanProcessor);
            }
        });

        camelContext.start();

        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }

}
