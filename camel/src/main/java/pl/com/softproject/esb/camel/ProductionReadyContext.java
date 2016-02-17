/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class ProductionReadyContext {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("production-ready.xml");



    }

}
