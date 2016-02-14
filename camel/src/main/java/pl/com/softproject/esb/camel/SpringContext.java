/**
 * Copyright 2016-02-10 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.ConnectionFactory;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class SpringContext {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("test-jms:queue:test.queue").to("file://test.txt");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();

        camelContext.start();

        for (int i = 0; i < 10; i++) {
            template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
        }


        // wait a bit and then stop
        Thread.sleep(1000);
        camelContext.stop();

    }

}
