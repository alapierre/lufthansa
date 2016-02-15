/**
 * Copyright 2016-02-13 the original author or authors.
 */
package pl.com.softproject.esb.jmx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class SenndOrderMain {

    public static void main(String[] args) {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("sender-context.xml");

        OrderSendService service = ctx.getBean(OrderSendService.class);
        service.sendSampleOrder();

    }

}
