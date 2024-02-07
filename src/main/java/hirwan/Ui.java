package hirwan;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The UI class which handles all interactions with the user including the inputs from the keyboard and the
 * output on the screen
 */
class Ui {

    /**
     * The input class that reads in the text that the user inputs
     * @return The string representation of the input of the user
     */
    public static String input() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }

    public static String input(String input) {
        return input;
    }

    /**
     * The output method that prints the String desired to the user
     * @param printText The string to be printed and displayed to the user
     */
    public static void output(String printText) {
        System.out.println(printText);
    }
}
