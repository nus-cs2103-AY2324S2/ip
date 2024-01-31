package Parsing;

import Exceptions.YpxmmException;

import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> parse(String input) throws YpxmmException {
        ArrayList<String> parsedResult = new ArrayList<>();
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch (command) {
        case "bye":
        case "list":
        case "getcommands":
            parsedResult.add(command);
            return parsedResult;
        case "mark":
            try {
                int index = Integer.parseInt(splitInput[1]);
                parsedResult.add(command);
                parsedResult.add(splitInput[1]);
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Brother, key in mark <space> then a valid number");
            } catch (NumberFormatException n) {
                throw new YpxmmException("You tell me now what task am I supposed to mark if you don't provide me with a number?");
            }
        case "unmark":
            try {
                int index = Integer.parseInt(splitInput[1]);
                parsedResult.add(command);
                parsedResult.add(splitInput[1]);
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Brother, key in unmark <space> then a valid number");
            } catch (NumberFormatException n) {
                throw new YpxmmException("You tell me now what task am I supposed to unmark if you don't provide me with a number?");
            }
        case "todo":
            try {
                parsedResult.add(command);
                String[] info = input.split("todo ");
                if (info[1].isBlank()) {
                    throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                }
                parsedResult.add(info[1].trim());
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("You trying to test my patience ah? Type \"get commands\" if u blur and dunno how to use me properly.");
            }
        case "deadline":
            try {
                parsedResult.add(command);
                String[] info = input.split("/");
                if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                    throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                }
                parsedResult.add(info[0].substring(9).trim());
                parsedResult.add(info[1].trim());
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("You trying to test my patience ah? Check that u got key in the deadline lehhh\n" +
                        "Type \"get commands\" if u blur and dunno how to use me properly.");
            }
        case "event":
            try {
                parsedResult.add(command);
                String[] info = input.split("/");
                if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                    throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                }
                parsedResult.add(info[0].substring(6).trim());
                parsedResult.add(info[1].trim());
                parsedResult.add(info[2].trim());
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh brother last warning ah. Check that u got key in the start and end time\n" +
                        "Type \"get commands\" if u blur and dunno how to use me properly.");
            }
        case "delete":
            try {
                int index = Integer.parseInt(splitInput[1]);
                parsedResult.add(command);
                parsedResult.add(splitInput[1]);
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Brother, key in delete <space> then a valid number");
            } catch (NumberFormatException n) {
                throw new YpxmmException("You tell me now what task am I supposed to delete if you don't provide me with a number?");
            }
        default:
            throw new YpxmmException("Sorry bro, idk what that means. You try type in \"getcommands\" then see if got what u want.");
        }
    }
}
