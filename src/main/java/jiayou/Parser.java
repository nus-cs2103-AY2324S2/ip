package jiayou;

import java.time.LocalDate;
import jiayou.task.*;

public class Parser {
    private static enum CommandType {
        TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, SEARCH, FIND, BYE
    }

    public void parse(TaskList tasks, String input) {
        try {
            String[] parts = input.split(" ", 2);
            String commandString = parts[0];
            Parser.CommandType command = Parser.CommandType.valueOf(commandString.toUpperCase());
            String content = parts.length > 1 ? parts[1] : "";

            switch (command) {
            case LIST:
                tasks.printList();
                break;
            case MARK:
                if (content.isEmpty()) {
                    throw new ParseException("OOPS!!! I don't know which task to mark. Please add the index after the keyword mark!");
                } else {
                    tasks.markTask(content);
                    break;
                }
            case UNMARK:
                if (content.isEmpty()) {
                    throw new ParseException("OOPS!!! I don't know which task to unmark. Please add the index after the keyword unmark!");
                } else {
                    tasks.unmarkTask(content);
                    break;
                }
            case DELETE:
                if (content.isEmpty()) {
                    throw new ParseException("OOPS!!! I don't know which task to delete. Please add the index after the keyword delete!");
                } else {
                    tasks.deleteTask(content);
                    break;
                }
            case TODO:
                if (content.isEmpty()) {
                    throw new ParseException("OOPS!!! The description of a todo cannot be empty. Please add a description after the keyword todo!");
                } else {
                    ToDo newToDo = new ToDo(content);
                    tasks.addTask(newToDo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newToDo);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                    break;
                    }
            case DEADLINE:
                String[] deadlineParts = content.split(" /by ");
                Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newDeadline);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                break;
            case EVENT:
                String[] eventParts = content.split(" /from | /to ");
                Event newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                tasks.addTask(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newEvent);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                break;
            case SEARCH:
                LocalDate date = LocalDate.parse(content);
                tasks.searchByDate(date);
            case FIND:
                tasks.searchByKeyword(content);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
