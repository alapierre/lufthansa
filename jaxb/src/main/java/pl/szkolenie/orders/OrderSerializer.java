/**
 * Copyright 2016-02-15 the original author or authors.
 */
package pl.szkolenie.orders;

import pl.com.softproject.lilu.model.order.Order;
import pl.com.softproject.utils.xml.BaseXMLSerializer;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class OrderSerializer extends BaseXMLSerializer<Order> {

    public OrderSerializer() {
        super("pl.com.softproject.lilu.model.order", "order.xsd", "");
    }
}
