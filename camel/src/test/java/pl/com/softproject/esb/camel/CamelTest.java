/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.camel;

import org.apache.camel.ExchangePattern;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class CamelTest extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("test-context.xml");
    }

    @Test
    public void testAsyncProcessing() throws InterruptedException {

        MockEndpoint mockOut = getMockEndpoint("mock:out");
        mockOut.setExpectedMessageCount(10);
        mockOut.setResultWaitTime(5000);

        for (int i = 0; i < 10; i++) {
            template.sendBody("seda:in", ExchangePattern.InOnly, "Message[" + i + "]");
        }

        assertMockEndpointsSatisfied();
    }


}
