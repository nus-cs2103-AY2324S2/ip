package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

/**
 * Parser class for parsing user inputs in the Zero bot.
 */
public class Parser {
    private static DateTimeFormatter dtfInput = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void setDateTimeFormat(DateTimeFormatter dtf) {
        dtfInput = dtf;
    }

    /**
     * Parses input string in the form of "[cmd] [index/name] [args...]".
     * 
     * <p>[args...] are in the form of "/[arg name] [value]". For example:
     * <blockquote><pre>"/from 20/3/23"</pre></blockquote>
     * 
     * @param input String to parse.
     * @return {@code Hashtable<Key, Value>} with minimally 1 key, "cmd".
     */
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

    /**
     * Parses the input string in 1-based indexing and returns an int in 0-based indexing.
     * 
     * @param index String to parse with 1-based indexing.
     * @return {@code int} with 0-based indexing.
     * @throws NumberFormatException If the string does not contain a parsable integer.
     */
    static int parseIndex(String index) throws NumberFormatException {
        return Integer.parseInt(index) - 1;
    }

    /**
     * Checks if the string is null, empty or filled with blank spaces
     * 
     * @param s String to verify.
     * @throws IllegalArgumentException If the string is null, empty or filled with blank spaces.
     */
    static void checkNullOrEmpty(String s) throws IllegalArgumentException {
        if (s == null | s.isEmpty() | s.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses the input string and returns a {@code LocalDateTime} object.
     * 
     * @param s String to parse, in the form specified in {@link controller.Parser#dtfInput} 
     *     of type {@code DateTimeFormatter}.
     * @return {@code LocalDateTime} object.
     * @throws NullPointerException If the string is null.
     * @throws DateTimeParseException If the string cannot be parsed.
     * 
     */
    static LocalDateTime parseDateTime(String s) throws NullPointerException, DateTimeParseException {
        return LocalDateTime.parse(s, dtfInput);
    }
}
