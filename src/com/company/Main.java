package com.company;

import java.util.List;

public class Main {
    private static List<User> userList;

    public static void main(String[] args) throws InterruptedException {
        Library library = Library.getInstance();
        userList = library.getUserList();
        List<Book> bookList = library.sortBooks();
        for (Book book : bookList){
            System.out.println(book);
        }

        Admin admin = new Admin();
        User.getEventManager().subscribe("take", admin);
        User.getEventManager().subscribe("return", admin);

        for (User user : userList){
            user.start();
        }

        Thread.sleep(10000);
        admin.checkBooksTakenByAuthor();

        admin.createDebtorsList();
        admin.manageSendingOut();
        admin.maxBooksAmount();
        admin.usersWithMoreThanTwoBooks();

        for (User u : userList){
            u.join();
        }

    }

}
