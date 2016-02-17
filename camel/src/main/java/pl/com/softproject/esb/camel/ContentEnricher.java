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
import pl.com.softproject.esb.processor.ExampleAggregationStrategy;

import javax.jms.ConnectionFactory;
import java.util.Scanner;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class ContentEnricher {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        final ExampleAggregationStrategy strategy = new ExampleAggregationStrategy();

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file://d:/orders?charset=UTF-8").enrich("test-jms:queue:res.queue", strategy)
                .to("test-jms:queue:test.queue");
            }
        });

        camelContext.start();

        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }

}
