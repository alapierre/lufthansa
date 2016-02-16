/*
 * Copyright 2014-09-18 the original author or authors.
 */
package pl.altkom.sping.batch.sample.processor;

import org.springframework.batch.item.ItemProcessor;
import pl.altkom.sping.batch.sample.model.Report;



/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class CustomItemProcessor implements ItemProcessor<Report, Report> {

    @Override
    public Report process(Report item) throws Exception {
        System.out.println("Processing..." + item);
        return item;
    }

}
