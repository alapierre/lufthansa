/**
 * Copyright 2016-02-13 the original author or authors.
 */
package pl.com.softproject.esb.model;

import java.io.Serializable;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class Person implements Serializable {

    private String name;
    private String lastName;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
