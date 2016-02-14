/**
 * Copyright 2016-02-10 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class CamelInXML {

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("camel.xml");

        CamelContext camelContext = ctx.getBean(CamelContext.class);
        ProducerTemplate template = camelContext.createProducerTemplate();

        for (int i = 0; i < 10; i++) {
            template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
        }

        Thread.sleep(1000);

    }

}
