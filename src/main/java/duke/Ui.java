package duke;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printWithLines(String... messages) {
        System.out.println("------------------------------------------");
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public static void showLoadingError() {
        printWithLines("Error loading file");
    }

    public static void parse(TaskList list, String message) {
        try {
            if (message.startsWith("todo")) {
                Parser.handleTodo(list, message);
            } else if (message.startsWith("deadline")) {
                Parser.handleDeadline(list, message);
            } else if (message.startsWith("event")) {
                Parser.handleEvent(list, message);
            } else if (message.equals("list")) {
                Parser.handleList(list);
            } else if (message.startsWith("mark")) {
                Parser.handleMark(list, message);
            } else if (message.startsWith("unmark")) {
                Parser.handleUnmark(list, message);
            } else if (message.startsWith("delete")) {
                Parser.deleteTask(list, message);
            } else if (!message.equals("bye")) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means buddy.");
            }
        } catch (DukeException e) {
            printWithLines(e.getMessage());
        }
    }

}
