/**
 * Copyright 2016-02-10 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

import javax.jms.ConnectionFactory;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class FileToQChoose {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        final Namespaces ns = new Namespaces("order", "http://www.softproject.com.pl/lilu/model/order");

        camelContext.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file://d:/orders?charset=UTF-8").choice()
                        .when().xpath("/order:order/orygin-country-code = 'en'", ns).to("test-jms:queue:orders.en")
                        .otherwise().to("test-jms:queue:orders.pl");
            }
        });

        camelContext.start();

        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }

}
