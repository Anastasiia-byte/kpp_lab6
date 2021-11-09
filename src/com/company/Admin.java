package com.company;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

public class Admin extends Listener{
    private Map<User, List<Book>> booksUserTook;
    private Map<Book, List<Date>> dateBookWasTaken;
    private Library library;

    public Admin(){
        booksUserTook = new HashMap<>();
        dateBookWasTaken = new HashMap<>();
        library = Library.getInstance();
    }

    private void manageTaking(User user, Book book){
        library.removeBook(book);
        if(!booksUserTook.containsKey(user)){
            booksUserTook.put(user, new ArrayList<>());
        }
        booksUserTook.get(user).add(book);

        dateBookWasTaken.put(book, new ArrayList<>());
        Date now = new Date(System.currentTimeMillis());
        dateBookWasTaken.get(book).add(now);
        dateBookWasTaken.get(book).add(new Date(now.getTime() + 5000));

    }

    private void manageReturning(User user, Book book) throws Exception {
        if(!booksUserTook.containsKey(user)){
            throw new Exception("User has no book to return!");
        }
        library.addBook(book);

        Abonement userAbonement = user.getAbonement();
        System.out.println("User " + userAbonement.getName() + userAbonement.getSurname() +
                " took the book " + book.getName() + "at " + dateBookWasTaken.get(book).get(0) + ".\n" +
                "Had to return: " + dateBookWasTaken.get(book).get(1) + ".\n" +
                "Returned: " + new Date(System.currentTimeMillis()));
        dateBookWasTaken.remove(book);
        booksUserTook.get(user).remove(book);
        System.out.println();
    }

    @Override
    void update(String event, User user, Book book) throws Exception {
        switch (event){
            case "take":
                manageTaking(user, book);
                break;
            case "return":
                manageReturning(user, book);
                break;
            default:
                break;
        }
    }

    public void usersWithMoreThanTwoBooks(){
        List<User> resultList = booksUserTook
                .keySet()
                .stream()
                .filter(u -> booksUserTook.get(u).size() > 2)
                .collect(Collectors.toList());

        System.out.println("Users who took more than two books: ");
        for (User user : resultList)
            System.out.println(user);
        System.out.println();
    }

    public void checkBooksTakenByAuthor(){
        String author;
        System.out.println("Enter the author`s name: ");
        Scanner s = new Scanner(System.in);
        author = s.nextLine();
        s.close();

        int result = 0;
        for (User user : booksUserTook.keySet()){
            result += booksUserTook
                    .get(user)
                    .stream()
                    .filter(b -> b.getAuthor().equals(author))
                    .count();

        }

        System.out.println("Books with author " + author + ": " + result);
        System.out.println();



    }

    public void maxBooksAmount(){
        int max = booksUserTook
                .values()
                .stream()
                .max(new Comparator<List<Book>>() {
                    @Override
                    public int compare(List<Book> o1, List<Book> o2) {
                        return Integer.compare(o1.size(), o2.size());
                    }
                })
                .get()
                .size();
        System.out.println("Max amount of books taken: " + max);
        System.out.println();
    }

    public void manageSendingOut(){
        List<User> lessThanTwo = booksUserTook
                .keySet()
                .stream()
                .filter(u -> booksUserTook.get(u).size() < 2)
                .collect(Collectors.toList());

        List<User> moreThanTwo = booksUserTook
                .keySet()
                .stream()
                .filter(u -> booksUserTook.get(u).size() > 2)
                .collect(Collectors.toList());

        System.out.println("Message about new books in the library goes to: \n");
        lessThanTwo.forEach(System.out::println);

        System.out.println("Message about soon due returning date goes to: \n");
        moreThanTwo.forEach(System.out::println);
        System.out.println();
    }

    public void createDebtorsList(){
        Map<User, Map<Book, Long>> userListMap = new HashMap<>();

        boolean isEmpty = true;
        for (User user : booksUserTook.keySet()){
            List<Book> bookList = user
                    .getBooksList()
                    .stream()
                    .filter(b -> dateBookWasTaken
                            .get(b)
                            .get(1)
                            .before(new Date()))
                    .collect(Collectors.toList());
            if (bookList.size() != 0)
                isEmpty = false;
            Map<Book, Long> deadline = new HashMap<>();
            for (Book b : bookList) {
                deadline.put(b, new Date().getTime() - dateBookWasTaken.get(b).get(1).getTime());
            }
            userListMap.put(user, deadline);
        }

        if(!isEmpty) System.out.println("List of debtors : \n");
        else System.out.println("Everything is okay . List of debtors is empty:)\n");

        for (Map.Entry<User, Map<Book, Long>> entry : userListMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                String str = "[";
                for (Map.Entry<Book, Long> entry1 : entry.getValue().entrySet()) {
                    str += entry1.getKey() + ": Return of the book is expired for " + entry1.getValue() + " days , ";
                }
                System.out.println(entry.getKey() + " : " + str + "]");
            }
        }

        System.out.println();

    }
}
