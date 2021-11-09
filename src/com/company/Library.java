package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private static Library instance = null;
    private final ArrayList<User> userList;
    private final ArrayList<Book> bookList;

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public List<User> getUserList() {
        return userList;
    }

    private Library(){
        userList = new ArrayList<>();
        bookList = new ArrayList<>();
        List<String> BookName = FileReader.getFileName();
        List<Integer> BookDate = FileReader.getDate();
        List<String> BookAuthor = FileReader.getAuthor();
        for (int i = 0; i < BookName.size(); i++) {
            bookList.add(new Book(BookName.get(i), BookAuthor.get(i), BookDate.get(i)));
        }
        List<String> Names = FileReader.getName();
        List<String> Surnames = FileReader.getSurname();
        List<String> Emails = FileReader.getEmail();
        for (int i = 0; i < Names.size(); i++) {
            userList.add(new User(new Abonement(Names.get(i),Surnames.get(i),Emails.get(i))));
        }
    }

    public Book getRandomBook(){
        int rand = (int) (Math.random() * bookList.size() - 1);
        return bookList.get(rand);
    }

    public static Library getInstance(){
        if(instance == null)
            instance = new Library();
        return instance;
    }

    public void addBook(Book book){
        bookList.add(book);
    }

    public void removeBook(Book book){
        bookList.remove(book);
    }

    public List<Book> sortBooks(){
        return bookList.stream().sorted(new Book.DateComparator()).collect(Collectors.toList());
    }
}
