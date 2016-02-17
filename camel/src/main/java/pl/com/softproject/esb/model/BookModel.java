/**
 * Copyright 2016-02-17 the original author or authors.
 */
package pl.com.softproject.esb.model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */

@CsvRecord(separator = ",", crlf = "UNIX")
public class BookModel implements Serializable{

    @DataField(pos = 1, trim = true)
    private String category;

    @DataField(pos = 2, trim = true)
    private String title;

    @DataField(pos = 3, defaultValue = "en", trim = true)
    private String titleLanguage;

    @DataField(pos = 4, trim = true)
    private String author1;

    @DataField(pos = 5, trim = true)
    private String author2;

    @DataField(pos = 6, pattern = "dd-MM-yyyy", trim = true)
    private Date publishDate;

    @DataField(pos = 7, precision = 2)
    private BigDecimal price;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLanguage() {
        return titleLanguage;
    }

    public void setTitleLanguage(String titleLanguage) {
        this.titleLanguage = titleLanguage;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", titleLanguage='" + titleLanguage + '\'' +
                ", author1='" + author1 + '\'' +
                ", author2='" + author2 + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", price=" + price +
                '}';
    }
}
