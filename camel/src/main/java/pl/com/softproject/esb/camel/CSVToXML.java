/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.esb.model.BookModel;

import javax.jms.ConnectionFactory;
import java.util.Scanner;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class CSVToXML {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(ctx.getBean(ConnectionFactory.class)));

        final XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setRootName("bookstore");
        xmlJsonFormat.setEncoding("UTF-8");

        final DataFormat bindy = new BindyCsvDataFormat(BookModel.class);

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file://D:/orders?charset=utf-8")
                        .unmarshal(bindy).marshal().json(JsonLibrary.Jackson)
                        .to("jms:queue:json").unmarshal(xmlJsonFormat)
                        .to("jms:queue:xml");

            }
        });

        camelContext.start();


        Scanner keyboard = new Scanner(System.in);
        keyboard.next();

        camelContext.stop();

    }

}
