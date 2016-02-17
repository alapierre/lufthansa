/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.esb.endpoint.OrderServiceImpl;

import javax.jms.ConnectionFactory;
import java.util.Scanner;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class JettyEndpoint {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        final OrderServiceImpl service = new OrderServiceImpl();

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("jetty://http://0.0.0.0:8080/order").bean(service, "lookup");
            }
        });

        camelContext.start();

        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }
}
