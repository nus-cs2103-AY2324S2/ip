package hirwan;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The parser class which deals with all functionality related to interpreting the users command into the correct action
 */
class Parser {

    /**
     * The Translate method that takes in the command to be translated into an int representing the action to be carried
     * out by the hirwan chatbot
     * @param text The command to be interpreted by the chatbot and which is inputted by the user
     * @return An integer representing the command to be carried out by the hirwan chatbot that is parsed into the
     * hirwan class
     */
    public static int translate(String text) {

        if (text.equals("list")) {
            return 1;
        } else if (text.startsWith("todo")) {
            return 2;
        } else if (text.startsWith("deadline")) {
            return 3;
        } else if (text.startsWith("event")) {
            return 4;
        } else if (text.startsWith("mark")) {
            return 5;
        } else if (text.startsWith("unmark")) {
            return 6;
        } else if (text.startsWith("delete")) {
            return 7;
        } else if (text.equals("bye")) {
            return 8;
        } else {
            return 9;
        }
    }
}