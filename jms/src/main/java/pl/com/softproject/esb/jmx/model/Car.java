/**
 * Copyright 2016-02-15 the original author or authors.
 */
package pl.com.softproject.esb.jmx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String model;
    private int milage;
    private String color;



}
