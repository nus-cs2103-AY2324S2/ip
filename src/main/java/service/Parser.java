package service;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {


    /**
     * Obtains the user's input command.
     * @return The user's commands from the scanner.
     */
    public String parse() {
        String s;
        Scanner scanner = new Scanner(System.in);
        try {
            s = scanner.nextLine(); // Use the same Scanner object
            return s;
        } catch (NoSuchElementException e) {
            return null;
        }
    }





}
