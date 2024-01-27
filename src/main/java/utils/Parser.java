package utils;

import java.util.Arrays;
import java.util.ArrayList;

import commands.*;
import exceptions.ConvoBotException;
import tasks.*;

public class Parser {
    public static Command parseUserInput(String userInput) throws ConvoBotException {
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        if (inputList.size() == 0) {
            throw new ConvoBotException("Invalid input. Input must not be empty.");
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(inputList.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ConvoBotException("Invalid input. Input must be one of: bye, list, mark, unmark, delete, todo, deadline, event.");
        }

        Command command = null;
        int i = -1, j = -1, k = -1;
        String description;
        
        switch (commandType) {
        case BYE:
            command = new Bye();
            break;
        
        case LIST:
            command = new List();
            break;
        
        case MARK:
            try {
                i = Integer.parseInt(inputList.get(1)) - 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
            }
            if (inputList.size() != 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            command = new Mark(i);
            break;
        
        case UNMARK:
            try {
                i = Integer.parseInt(inputList.get(1)) - 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
            }
            
            if (inputList.size() != 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            command = new Unmark(i);
            break;
        
        case DELETE:
            try {
                i = Integer.parseInt(inputList.get(1)) - 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
            }
            if (inputList.size() != 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            command = new Delete(i);
            break;
        
        case TODO:
            if (userInput.length() == 4) {
                throw new ConvoBotException("Invalid input. The description of a todo cannot be empty.");
            }
            command = new Add(new ToDo(userInput.substring(5)));
            break;
        
        case DEADLINE:
            j = inputList.indexOf("/by");
            if (j == 1) {
                throw new ConvoBotException("Invalid input. The description of a deadline cannot be empty.");
            }
            if (j == -1 || j == inputList.size() - 1) {
                throw new ConvoBotException("Invalid input. Start date missing.");
            }
            description = String.join(" ", inputList.subList(1, j));
            String by = String.join(" ", inputList.subList(j+1, inputList.size()));
            command = new Add(new Deadline(description, DateTime.stringToDate(by)));
            break;
        
        case EVENT:
            j = inputList.indexOf("/from");
            k = inputList.indexOf("/to");
            if (j == 1 || k == 1) {
                throw new ConvoBotException("Invalid input. The description of an event cannot be empty.");
            }
            if (j == -1 || j == inputList.size() - 1 || inputList.get(j+1).equals("/to")) {
                throw new ConvoBotException("Invalid input. Start date missing.");
            }
            if (k == -1 || k == inputList.size() - 1) {
                throw new ConvoBotException("Invalid input. End date missing.");
            }
            description = String.join(" ", inputList.subList(1, j));
            String from = String.join(" ", inputList.subList(j+1, k));
            String to = String.join(" ", inputList.subList(k+1, inputList.size()));
            command = new Add(new Event(description, DateTime.stringToDate(from), DateTime.stringToDate(to)));
            break;
        
        default:
            // impossible to reach
            break;
        }
        return command;
    }

    public static Task parseTaskFromLine(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3 || parts.length > 5) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        boolean isDone = (parts[1] == "1" ? true : false);
        String description = parts[2];
        Task task;
        try {
            switch (parts[0]) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                task = new Deadline(description, isDone, DateTime.stringToDate(parts[3]));
                break;
            case "E":
                task = new Event(description, isDone, DateTime.stringToDate(parts[3]), DateTime.stringToDate(parts[4]));
                break;
            default:
                throw new IllegalArgumentException("Invalid line format: " + line);
            }
        } catch (ConvoBotException e) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        return task;
    }
}
