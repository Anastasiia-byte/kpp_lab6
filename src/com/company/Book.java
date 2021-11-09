package com.company;

import java.util.Comparator;
import java.util.Date;

public class Book {
    private String name;
    private String author;
    private Integer publicationYear;

    public Book(String name, String author, int publicationDate){
        this.name = name;
        this.author = author;
        this.publicationYear = publicationDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPublicationDate() {
        return publicationYear;
    }

    public static class DateComparator implements Comparator<Book> {

        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPublicationDate().compareTo(o2.getPublicationDate());
        }
    }



    @Override
    public String toString() {
        return "Book: " +
                "name: '" + name + '\'' +
                ", author: '" + author + '\'' +
                ", publicationDate=" + publicationYear;
    }
}
