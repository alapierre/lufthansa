/**
 * Copyright 2016-02-10 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.esb.model.Person;

import javax.jms.ConnectionFactory;
import java.util.Scanner;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class ObjectToJson {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("jms:queue:obj").marshal().json(JsonLibrary.Jackson)
                    .to("jms:queue:json");
            }
        });

        camelContext.start();

        ProducerTemplate template = camelContext.createProducerTemplate();

        Person p = new Person();
        p.setLastName("ąłłńśćźż");
        p.setName("JAn");

        for (int i = 0; i < 10; i++) {
            template.sendBody("jms:queue:obj", p);
        }

        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }

}
