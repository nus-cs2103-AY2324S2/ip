package service;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {


    public String parse() {
        String s;
        Scanner scanner = new Scanner(System.in);
        try {
            s = scanner.nextLine(); // Use the same Scanner object
            return s;
        } catch (NoSuchElementException e) {
            System.out.println("No input found. Exiting.");
            return null;
        }
    }





}
