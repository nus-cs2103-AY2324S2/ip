package Parsing;

import Exceptions.YpxmmException;

import java.util.ArrayList;

public class ParserStub {
    public String parseStub(String input) {
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch (command) {
            case "bye":
            case "list":
            case "getcommands":
                return input;
            case "mark":
                try {
                    int index = Integer.parseInt(splitInput[1]);
                    return command + " " + splitInput[1];
                } catch (IndexOutOfBoundsException e) {
                    return "Brother, key in mark <space> then a valid number";
                } catch (NumberFormatException n) {
                    return "You tell me now what task am I supposed to mark if you don't provide me with a number?";
                }
            case "unmark":
                try {
                    int index = Integer.parseInt(splitInput[1]);
                    return command + " " + splitInput[1];
                } catch (IndexOutOfBoundsException e) {
                    return "Brother, key in unmark <space> then a valid number";
                } catch (NumberFormatException n) {
                    return "You tell me now what task am I supposed to unmark if you don't provide me with a number?";
                }
            case "todo":
                try {
                    String[] info = input.split("todo ");
                    if (info[1].isBlank()) {
                        return "Help la, can just tell me what is the name of your task anot?";
                    }
                    return command + " " + info[1].trim();
                } catch (IndexOutOfBoundsException e) {
                    return "You trying to test my patience ah? Type \"get commands\" if u blur and dunno how to use me properly.";
                }
            case "deadline":
                try {
                    String[] info = input.split("/");
                    if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                        return "Help la, can just tell me what is the name of your task anot?";
                    }
                    return command + " " + info[0].substring(9).trim() + " " + info[1].trim();
                } catch (IndexOutOfBoundsException e) {
                    return "You trying to test my patience ah? Check that u got key in the deadline lehhh\n" +
                            "Type \"get commands\" if u blur and dunno how to use me properly.";
                }
            case "event":
                try {
                    String[] info = input.split("/");
                    if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                        return "Help la, can just tell me what is the name of your task anot?";
                    }
                    return command + " " + info[0].substring(6).trim() + " " +
                            info[1].trim() + " " + info[2].trim();
                } catch (IndexOutOfBoundsException e) {
                    return "Eh brother last warning ah. Check that u got key in the start and end time\n" +
                            "Type \"get commands\" if u blur and dunno how to use me properly.";
                }
            case "delete":
                try {
                    int index = Integer.parseInt(splitInput[1]);
                    return command + " " + splitInput[1];
                } catch (IndexOutOfBoundsException e) {
                    return "Brother, key in delete <space> then a valid number";
                } catch (NumberFormatException n) {
                    return "You tell me now what task am I supposed to delete if you don't provide me with a number?";
                }
            case "find":
                try {
                    String[] info = input.split("find ");
                    return command + " " + info[1];
                } catch (IndexOutOfBoundsException e) {
                    return "What u want me to find??";
                }
            default:
                return "Sorry bro, idk what that means. You try type in \"getcommands\" then see if got what u want.";
        }
    }
}
