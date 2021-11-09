package com.company;

public abstract class Listener {
    abstract void update(String event, User user, Book book) throws Exception;
}
