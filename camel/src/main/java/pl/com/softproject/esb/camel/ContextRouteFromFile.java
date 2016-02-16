/**
 * Copyright 2016-02-10 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Scanner;

import javax.jms.ConnectionFactory;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class ContextRouteFromFile {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        ClassPathResource resource = new ClassPathResource("route.xml");

        RoutesDefinition routes = camelContext.loadRoutesDefinition(resource.getInputStream());
        camelContext.addRouteDefinitions(routes.getRoutes());

        ProducerTemplate template = camelContext.createProducerTemplate();

        camelContext.start();

        for (int i = 0; i < 10; i++) {
            template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
        }

        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }

}
