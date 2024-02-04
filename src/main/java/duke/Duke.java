package duke;

import duke.task.Task;
import duke.task.Events;
import duke.task.Deadlines;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                    taskList.addItem(new Deadlines(task, deadline), true);
                } else if (userInput.startsWith("event")) {
                    String[] parts = Parser.parseEvent(userInput);
                    taskList.addItem(new Events(parts[0], parts[1], parts[2]), true);
                } else if (userInput.equals("list")) {
                    taskList.printList();
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
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
