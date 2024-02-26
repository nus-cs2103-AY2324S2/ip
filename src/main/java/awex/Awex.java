package awex;

import java.io.IOException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TodoTask;

public class Awex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Awex() {
        this.storage = new Storage("./list.txt");
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendGreeting() {
        return this.ui.greeting();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String[] arr = input.split(" ", 2);
        if (input.equals("bye")) {
            try {
                storage.store(tasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ui.farewell();
        } else if (input.equals("list")) {
            if (arr.length > 1) {
                return ui.allInstructions();
            } else if (tasks.isEmpty()) {
                return ui.emptyListMessage();
            } else {
                return ui.showListMessage(tasks);
            }
        } else if (arr[0].equals("find")) {
            if (arr.length < 2) {
                return ui.allInstructions();
            } else if (tasks.isEmpty()) {
                return ui.emptyListMessage();
            } else {
                return ui.showFindMessage(tasks, arr[1]);
            }
        } else if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
            String[] array = input.split(" ");
            if (array.length != 2) {
                return ui.wrongMarkDeleteFormatMessage(arr[0]);
            } else {
                int i = Integer.parseInt(array[1]);
                int len = tasks.size();
                if (i == 0 || i > len) {
                    return ui.wrongIndexMessage(i, len);
                } else {
                    if (arr[0].equals("delete")) {
                        return ui.deleteTaskMessage(i, tasks);
                    } else {
                        Task t = tasks.get(i - 1);
                        t.changeStatus(arr[0]);
                        return ui.changeStatusMessage(arr[0], t);
                    }
                }
            }
        } else {
            Task t;
            if (arr[0].equals("todo")) {
                if (arr.length == 1) {
                    return ui.failedTaskCreationMessage("todo");
                }
                t = TodoTask.of(arr[1]);
            } else if (arr[0].equals("deadline")) {
                String[] array = input.split("/");
                if (array.length != 2) {
                    return ui.failedTaskCreationMessage("deadline");
                }
                t = DeadlineTask.of(arr[1]);
            } else if (arr[0].equals("event")) {
                String[] array = input.split("/");
                if (array.length != 3) {
                    return ui.failedTaskCreationMessage("event");
                }
                t = EventTask.of(arr[1]);
            } else {
                return ui.allInstructions();
            }
            tasks.add(t);
            return ui.newTaskAddedMessage(tasks.size(), t);
        }
    }
}