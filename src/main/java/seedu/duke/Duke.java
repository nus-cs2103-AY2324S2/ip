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
import java.time.LocalDateTime;


/**
 * <h1> Duke </h1>
 * Duke program is a chat bot that maintains your todo list of todo tasks, events and deadlines.
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
        taskList = new TaskList();
        store = new Storage(filePath, taskList);
        try {
            store.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            if (input.startsWith("mark")) {
                return taskList.mark(Parser.parseNum(input));
            } else if (input.startsWith("unmark")) {
                return taskList.unmark(Parser.parseNum(input));
            } else if (input.startsWith("delete")){
                return taskList.delete(Parser.parseNum(input));
            } else if (input.startsWith("todo")) {
                return taskList.addItem(new Task(Parser.parseTodo(input)), true);
            } else if (input.startsWith("deadline")) {
                String task = Parser.parseDeadlineTask(input);
                LocalDateTime deadline = Parser.parseDeadline(input);
                return taskList.addItem(new Deadline(task, deadline), true);
            } else if (input.startsWith("event")) {
                String[] parts = Parser.parseEvent(input);
                return taskList.addItem(new Event(parts[0], parts[1], parts[2]), true);
            } else if (input.equalsIgnoreCase("list")) {
                return taskList.printList();
            } else if (input.startsWith("find")) {
                return Storage.findFromFile(Parser.parseFind(input));
            } else if (input.equalsIgnoreCase("bye")){
                return UI.bye();
            } else {
                throw new DukeException();
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        String filePath = "./data/Duke.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
