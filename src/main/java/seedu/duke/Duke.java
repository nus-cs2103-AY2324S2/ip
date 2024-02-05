package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Duke </h1>
 * Duke program is a chatbot that maintains your todo list of todo tasks, events and deadlines.
 * Simply type "todo //name of your task" and Bobby, your friendly assistant will add it to
 * your task list and it will be stored in the data on your hard disk as well!
 * Type "list" to view all the tasks in your task list.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Duke {
    private static Storage store;
    private UI ui;

    private TaskList taskList;

    public Duke(String filePath) {
        ui = new UI(store);
        taskList = new TaskList();
        store = new Storage(filePath, taskList);
        try {
            store.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.intro();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            try {
                if (userInput.startsWith("mark")) {
                    taskList.mark(Parser.parseNum(userInput));
                } else if (userInput.startsWith("unmark")) {
                    taskList.unmark(Parser.parseNum(userInput));
                } else if (userInput.startsWith("delete")){
                    taskList.delete(Parser.parseNum(userInput));
                } else if (userInput.startsWith("todo")) {
                    taskList.addItem(new Task(Parser.parseTodo(userInput)), true);
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = Parser.parseDeadline(userInput);
                    String task = parts[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime deadline = LocalDateTime.parse(parts[1], formatter);
                    taskList.addItem(new Deadline(task, deadline), true);
                } else if (userInput.startsWith("event")) {
                    String[] parts = Parser.parseEvent(userInput);
                    taskList.addItem(new Event(parts[0], parts[1], parts[2]), true);
                } else if (userInput.equals("list")) {
                    taskList.printList();
                } else if (userInput.startsWith("find")) {
                    store.findFromFile(Parser.parseFind(userInput));
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            userInput = scanner.nextLine();
        }
        ui.bye();
    }
    public static void main(String[] args) {
        String filePath = "./data/Duke.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (FileAlreadyExistsException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        new Duke(filePath).run();
    }
}
