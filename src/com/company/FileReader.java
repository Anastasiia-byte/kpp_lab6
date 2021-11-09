package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<String> getFileName(){
        List<String> BookNames= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File("books.txt");
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                if (!name.equals("")) BookNames.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BookNames;
    }
    public static List<Integer> getDate(){
        List<Integer> BookDate= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File("books.txt");
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = (parts[2].trim());
                if (!name.equals("")) BookDate.add(Integer.valueOf(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BookDate;
    }
    public static List<String> getAuthor(){
        List<String> BookAuthor= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File("books.txt");
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[1].trim();
                if (!name.equals("")) BookAuthor.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BookAuthor;
    }
    public static List<String> getName(){
        List<String> ClientNames= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File("Clients.txt");
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0].trim();
                if (!name.equals("")) ClientNames.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ClientNames;
    }
    public static List<String> getSurname(){
        List<String> ClientSurnames= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File("Clients.txt");
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = (parts[1].trim());
                if (!name.equals("")) ClientSurnames.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ClientSurnames;
    }
    public static List<String> getEmail(){
        List<String> ClientEmails= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File("Clients.txt");
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[2].trim();
                if (!name.equals("")) ClientEmails.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ClientEmails;
    }
}
