package seedu.bobby;

import javafx.application.Platform;
import seedu.bobby.task.Task;
import seedu.bobby.task.Event;
import seedu.bobby.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


/**
 * <h1> Bobby </h1>
 * Bobby program is a chat bot that maintains your todo list of todo tasks, events and deadlines.
 * Simply type "todo //name of your task" and Bobby, your friendly assistant will add it to
 * your task list and it will be stored in the data on your hard disk as well!
 * Type "list" to view all the tasks in your task list.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Bobby {
    private static Storage store;
    private UI ui;

    private TaskList taskList;

    public Bobby(String filePath) {
        File file = new File(filePath);
        //@@author bachletuankhai-reused
        //Reused this code snippet that bachletuankhai shared on the github forum
        //from https://github.com/nus-cs2103-AY2324S2/forum/issues/134 with minor modifications
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        taskList = new TaskList();
        store = new Storage(filePath, taskList);
        try {
            store.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns String of Bobby's response to commands, to be printed out in GUI.
     * @param input
     * @return String of response to various commands
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
                LocalDateTime deadline = Parser.parseDeadlineCommand(input);
                return taskList.addItem(new Deadline(task, deadline), true);
            } else if (input.startsWith("event")) {
                String[] parts = Parser.parseEvent(input);
                return taskList.addItem(new Event(parts[0], parts[1], parts[2]), true);
            } else if (input.equalsIgnoreCase("list")) {
                return taskList.printList();
            } else if (input.startsWith("find")) {
                return Storage.findFromFile(Parser.parseFind(input));
            } else if (input.startsWith("update")) {
                int taskNum = Parser.parseNum(input);
                String[] parts = Parser.parseUpdate(input);
                return taskList.update(taskNum, parts[0], parts[1]);
            } else if (input.equalsIgnoreCase("bye")){
                scheduleShutdown();
                return UI.bye();
            } else {
                throw new BobbyException();
            }
        } catch (BobbyException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Schedules a 1 second timer. Starts the timer when user enters a "bye" command
     * and exits the program when the timer runs out. This 1 second timer is to ensure that the
     * user can see the bye message by Bobby before it shuts down.
     */
    public void scheduleShutdown() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 1000);
    }

    public static void main(String[] args) {
    }
}
