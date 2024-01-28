package duke;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Parser {
    private Scanner scanner;
    private TaskList myTasks;

    public Parser(Scanner scanner, TaskList myTasks) {
        this.scanner = scanner;
        this.myTasks = myTasks;
    }

    public boolean processCmd(String command) {
        String border = "____________________________________________________________";

        System.out.println(border);
        try {
            String cmd = command.split(" ")[0];
            String params = command.substring(cmd.length()).trim();
            switch (cmd) {
                case "bye":
                    return false;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    myTasks.printTasks();
                    break;
                case "mark":
                    if (params.length() == 0) {
                        throw new DukeException.MarkParamsException();
                    }
                    int num = Integer.valueOf(params);
                    myTasks.markTask(num);
                    Task t = myTasks.getTask(num);

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    break;
                case "unmark":
                    if (params.length() == 0) {
                        throw new DukeException.MarkParamsException();
                    }
                    num = Integer.valueOf(params);
                    myTasks.unmarkTask(num);
                    t = myTasks.getTask(num);

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    break;
                case "delete":
                    if (params.length() == 0) {
                        throw new DukeException.DeleteParamsException();
                    }
                    num = Integer.valueOf(params);
                    Task toDelete = myTasks.getTask(num);
                    myTasks.deleteTask(num);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(toDelete);
                    System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                    break;
                case "todo":
                    if (params.length() == 0) {
                        throw new DukeException.TodoDescriptionMissingException();
                    }

                    String desc = params;

                    Task newTask = new Todo(desc);
                    myTasks.addTask(newTask);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                    break;
                case "deadline":
                    if (!params.contains("/by")) {
                        throw new DukeException.DeadlineDetailsMissingException();
                    }

                    desc = params.split("/by")[0].trim();
                    String by = params.split("/by")[1].trim();

                    // Check if by is in valid date format
                    if (Dates.isValidInputDate(by)) {
                        LocalDateTime dateObj = Dates.inputStr2DateTime(by);
                        newTask = new Deadline(desc, dateObj); // Create date object
                    } else {
                        newTask = new Deadline(desc, by);
                    }
                    myTasks.addTask(newTask);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                    break;
                case "event":
                    if (!params.contains("/from") || !params.contains("/to")) {
                        throw new DukeException.EventDetailsMissingException();
                    }

                    desc = params.split("/from")[0];
                    String from = params.split("/from")[1].split("/to")[0].trim();
                    String to = params.split("/to")[1].trim();

                    if (Dates.isValidInputDate(from) && Dates.isValidInputDate(to)) {
                        LocalDateTime dateObjFrom = Dates.inputStr2DateTime(from);
                        LocalDateTime dateObjTo = Dates.inputStr2DateTime(to);
                        newTask = new Event(desc, dateObjFrom, dateObjTo); // Create date object
                    } else {
                        newTask = new Event(desc, from, to);
                    }
                    myTasks.addTask(newTask);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + myTasks.size() + " tasks in the list");
                    break;
                case "clear": // Clears the tasklist
                    myTasks.taskList.clear();
                    System.out.println("Got it. I've cleared the database.");
                    break;
                default:
                    throw new DukeException.UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println("duke.DukeException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("An unexpected error occurred.");
        }
        System.out.println(border);
        return true;
    }
}
