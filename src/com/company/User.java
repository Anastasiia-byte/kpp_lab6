package com.company;

import java.util.*;

public class User extends Thread{
    private Abonement abonement;
    private List<Book> booksList;
    private static EventManager eventManager;

    public static EventManager getEventManager(){
        return eventManager;
    }

    public User(Abonement abonement){
        this.abonement = abonement;
        booksList = new ArrayList<>();
        eventManager = new EventManager("take", "return");
    }

    public void takeBook(Book book){
        booksList.add(book);
        eventManager.notify("take", this, book);
    }

    public void returnBook(){
        if(booksList.size() > 0) {
            Book book = booksList.remove(booksList.size() - 1);
            eventManager.notify("return", this, book);
        } else {
            System.out.println("Nothing to return!");
        }
    }

    public Abonement getAbonement() {
        return abonement;
    }

    public List<Book> getBooksList(){
        return booksList;
    }

    @Override
    public String toString() {
        return abonement.toString();
    }

    @Override
    public void run() {
        Library library = Library.getInstance();
        try {
            int rand = (int)(1 + Math.random()*3);
            while (rand > 0) {
                sleep((long) (1000 + Math.random() * 1000));
                this.takeBook(library.getRandomBook());
                rand--;
            }

            while (booksList.size() > 0) {
                sleep((long) (30000 + Math.random() * 12000));
                this.returnBook();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
