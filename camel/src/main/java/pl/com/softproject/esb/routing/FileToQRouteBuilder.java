/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.routing;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class FileToQRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://d:/orders?charset=UTF-8").to("activemq:queue:test.queue");
    }
}
