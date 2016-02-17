/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.endpoint;


import org.apache.camel.Header;
import pl.com.softproject.esb.model.BookModel;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class OrderServiceImpl {

    public BookModel lookup(@Header("id") String id) {
        System.out.println(id);

        BookModel bookModel = new BookModel();

        bookModel.setAuthor1("aaaa");
        bookModel.setCategory("ddddd");

        return bookModel;

    }

}
