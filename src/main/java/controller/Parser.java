package controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class Parser {
    static DateTimeFormatter dtfInput = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Parses input string in the form of [cmd] [index/name] [args...]
    // Returns keys (cmd, name, /by, /from, /to) and their respective values
    static Hashtable<String, String> parseInput(String input) {
        String[] tokens = input.split(" ");

        Hashtable<String, String> result = new Hashtable<>();
        result.put("cmd", tokens[0]);
        String type = "name";
        StringBuilder arg = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                result.put(type, arg.toString().trim());
                type = tokens[i];
                arg.setLength(0);
            } else {
                if (tokens[i] != null) {
                    arg.append(" ").append(tokens[i]);
                }
            }
        }
        result.put(type, arg.toString().trim());
        return result;
    }

    // Checks if the task index supplied is within range and returns a valid index
    // Returns -1 for out of range indices
    static int parseIndex(String index) throws NumberFormatException {
        return Integer.parseInt(index) - 1;
    }

    // Checks if the string is null, empty or filled with spaces
    static void checkNullOrEmpty(String s) throws IllegalArgumentException {
        if (s == null | s.isEmpty() | s.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    static LocalDateTime parseDateTime(String s) throws NullPointerException, DateTimeParseException {
        return LocalDateTime.parse(s, dtfInput);
    }
}
