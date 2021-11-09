package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<String, List<Listener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, Listener listener) {
        List<Listener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, Listener listener) {
        List<Listener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, User user, Book book) {
        List<Listener> users = listeners.get(eventType);
        for (Listener listener : users) {
            try {
                listener.update(eventType, user,  book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
