package BadApple.task;

import BadApple.main.BadAppleException;
import BadApple.main.BadPingGuo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Parser {
    /*
    @@author Lim Zheng Ting,
    Github source : https://github.com/AL-ZT/ip/blob/branch-A-Assertions/src/main/java/duke/Parser.java
    Adapted idea of using a hashmap to shorten the methods of Parser.
     */
    static final HashMap<String, List<String>> CMD_LIST = new HashMap<>() {{
        put("todo", List.of("todo"));
        put("deadline", Arrays.asList("deadline", "/by"));
        put("event", Arrays.asList("event", "/from", "/to"));
        put("list", List.of("list"));
        put("mark", List.of("mark"));
        put("unmark", List.of("unmark"));
        put("delete", List.of("delete"));
        put("find", List.of("find"));
        put("!help", List.of("!help"));
        put("bye", List.of("bye"));
    }};


    /**
     * Takes in a user query and performs the necessary reading and writing to a file on the drive.
     * The file should exist, and is by default handled by BadPingGuo
     * @param s the query the user would like to make
     */
    public static Command ProcessQuery(String s) throws IOException, BadAppleException {
        String[] tokens = s.split(" ", 2);
        if (!CMD_LIST.containsKey(tokens[0])) {
            throw new BadAppleException("I didn't quite catch that, use !help to see what I can do!");
        }

        String command = tokens[0];

        List<String> parameters = CMD_LIST.get(command);

        // Error check if a parameter is missing.
        for (String param : parameters) {
            if (!s.contains(param)) {
                throw new BadAppleException(command + " is missing " + param) ;
            }
        }

        return new Command(tokens[0], s);
    }
}
