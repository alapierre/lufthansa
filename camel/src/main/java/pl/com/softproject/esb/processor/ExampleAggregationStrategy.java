/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.processor;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class ExampleAggregationStrategy  implements AggregationStrategy {

    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = null;
        Object resourceResponse = null;

        if(original != null)
            originalBody = original.getIn().getBody();

        if(resource!= null)
            resourceResponse = resource.getIn().getBody();

        System.out.println(originalBody);
        System.out.println(resourceResponse);

        return original;
    }
}
