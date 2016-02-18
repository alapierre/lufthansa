/**
 * Copyright 2016-02-18 the original author or authors.
 */
package pl.com.softproject.esb.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class HeaderProcessor implements Processor {

    private String contentType;

    public HeaderProcessor(String contentType) {
        this.contentType = contentType;
    }

    public void process(Exchange exchange) throws Exception {
        Object in = exchange.getIn().getBody();
        exchange.getOut().setBody(in);
        exchange.getOut().setHeader(Exchange.FILE_CONTENT_TYPE, contentType);
    }
}
