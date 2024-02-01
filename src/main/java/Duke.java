import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Duke {

    private final String name = "Duke";
    private final String exitMessage = "bye";
    private final FileHandler fileHandler = new FileHandler();
    private List<Task> tasks;

    public Duke() {
        tasks = fileHandler.readFromFile();
    }

    public void welcomeMessage() {
        divider();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        divider();
    }


    public void numberOfTasks() {
        System.out.println("Now you have " + tasks.size() +" tasks in the list.");
    }

    public void echoAdd(Task t) {
        divider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        numberOfTasks();
        divider();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        divider();
    }

    public boolean isBye(String str) {
        return str.equalsIgnoreCase(exitMessage);
    }

    public boolean checkList(String str) {
        return str.equalsIgnoreCase("list");
    }

    public boolean checkMark(String str) {
        return str.startsWith("mark");
    }

    public boolean checkUnMark(String str) {
        return str.startsWith("unmark");
    }

    public boolean checkTodo(String str) {
        return str.startsWith("todo");
    }

    public boolean checkDeadline(String str) {
        return str.startsWith("deadline");
    }

    public boolean checkEvent(String str) {
        return str.startsWith("event");
    }

    public boolean checkDelete(String str) {
        return str.startsWith("delete");
    }

    public void typeMessage() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!isBye(input)) {
            divider();
            try {
                if (checkList(input)) {
                    listTasks();
                } else if (checkMark(input)) {
                    markTask(input);
                } else if (checkUnMark(input)) {
                    unmarkTask(input);
                } else if (checkTodo(input)) {
                    addTodo(input);
                } else if (checkDeadline(input)) {
                    addDeadline(input);
                } else if (checkEvent(input)) {
                    addEvent(input);
                } else if (checkDelete(input)) {
                    deleteTask(input);
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                divider();
                System.out.println("ERROR : " + e.getMessage());
                divider();
            }

            input = sc.nextLine();
        }

        exit();
        sc.close();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        divider();
    }

    public void markTask(String input) {
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        tasks.get(index).markAsDone();
        try {
            divider();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            divider();
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }


    }

    public void unmarkTask(String input) {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        tasks.get(index).unmarkAsDone();
        try {
            divider();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            divider();
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }


//    public void addTodo(String input) {
//        String description = input.substring(5).trim();
//        Todo newTodo = new Todo(description);
//        tasks.add(newTodo);
//        try {
//            echoAdd(newTodo);
//            fileHandler.saveInFile(tasks);
//        } catch (FileIOException e) {
//            System.out.println("Error saving task: " + e.getMessage());
//        }
//    }

    public void addTodo(String str)
            throws DukeException{
        String[] todo = str.split("todo ?+");
        try {
            if (todo.length > 0) {
                Todo toDo = new Todo(todo[0]);
                tasks.add(toDo);
                echoAdd(toDo);
            } else {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            divider();
            System.out.println(e.getMessage());
            divider();
        }
    }
//    public void addDeadline(String input) {
//        String[] parts = input.substring(9).trim().split("/by", 2);
//        String description = parts[0].trim();
//        String by = parts[1].trim();
//        Deadline newDeadline = new Deadline(description, by);
//        tasks.add(newDeadline);
//        try {
//            echoAdd(newDeadline);
//            fileHandler.saveInFile(tasks);
//        } catch (FileIOException e) {
//            System.out.println("Error saving task: " + e.getMessage());
//        }
//    }

    public void addDeadline(String input) {
        try {
            String[] parts = input.substring(9).trim().split("/by ", 2);
            String description = parts[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime byDateTime = LocalDateTime.parse(parts[1].trim(), formatter);
            Deadline newDeadline = new Deadline(description, byDateTime);
            tasks.add(newDeadline);
            echoAdd(newDeadline);
            fileHandler.saveInFile(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HHmm'.");
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }


//    public void addEvent(String input) {
//        try {
//            String[] parts = input.substring(6).trim().split("/from ", 2);
//            if (parts.length < 2) {
//                System.out.println("Invalid event format. Please use 'event description /from start to end'.");
//                return;
//            }
//            String description = parts[0].trim();
//            String[] times = parts[1].trim().split(" /to ", 2);
//            if (times.length < 2) {
//                System.out.println("Invalid event time format. Please use 'from start to end'.");
//                return;
//            }
//            String start = times[0].trim();
//            String end = times[1].trim();
//            // Assuming Event constructor takes description, start, and end times
//            Event newEvent = new Event(description, start, end);
//            tasks.add(newEvent);
//            try {
//                echoAdd(newEvent);
//                fileHandler.saveInFile(tasks);
//            } catch (FileIOException e) {
//                System.out.println("Error saving task: " + e.getMessage());
//            }
//        } catch (Exception e) {
//            System.out.println("Error adding event: " + e.getMessage());
//        }
//    }

    public void addEvent(String input) {
        try {
            String[] parts = input.substring(6).trim().split("/from ", 2); // Splitting on "/from "
            if (parts.length < 2) {
                System.out.println("Invalid event format. Please use 'event description /from start to end'.");
                return;
            }
            String description = parts[0].trim();
            String[] times = parts[1].trim().split(" /to ", 2); // Splitting the times on " /to "
            if (times.length < 2) {
                System.out.println("Invalid event time format. Please use 'from start to end'.");
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime startDateTime = LocalDateTime.parse(times[0].trim(), formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(times[1].trim(), formatter);
            Event newEvent = new Event(description, startDateTime, endDateTime);
            tasks.add(newEvent);
            echoAdd(newEvent);
            fileHandler.saveInFile(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HHmm'.");
        } catch (Exception e) {
            System.out.println("Error adding event: " + e.getMessage());
        }
    }


//    public void deleteTask(String input) {
//        int index = Integer.parseInt(input.substring(7).trim()) - 1;
//        tasks.remove(index);
//        try {
//            fileHandler.saveInFile(tasks);
//        } catch (FileIOException e) {
//            System.out.println("Error saving task: " + e.getMessage());
//        }
//    }

    public void deleteTask(String str) {
        String[] string = str.split(" ");
        int index = Integer.parseInt(string[1]);
        Task t = tasks.remove(index);
        divider();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + t.toString());
        numberOfTasks();
        divider();
    }

    public void divider() {
        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.welcomeMessage();
        duke.typeMessage();
    }
}

// Task, Todo, Deadline, Event are assumed to be separate classes.
// FileIOException is assumed to be a custom exception class.