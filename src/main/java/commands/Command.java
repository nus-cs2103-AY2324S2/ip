package commands;

import ui.Ui;
import main.Storage;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

/**
 * Defines the set of commands that can be executed within the Duke application.
 * Each enum constant represents a distinct command and defines its own execution behavior.
 */

public enum Command {
    /**
     * Command to terminate the application. It saves the current state of tasks before exiting.
     */
    BYE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            storage.saveTasks(tasks);
        }
    },
    /**
     * Command to list all tasks in the task list.
     */
    YAP {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            tasks.yapTasks();
        }
    },
    /**
     * Command to mark a specified task as done.
     */
    MARK {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Extract task index from argument and mark the task as done
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            tasks.markTaskAsDone(index);
            storage.saveTasks(tasks);
        }
    },
    /**
     * Command to unmark a specified task as done.
     */
    UNMARK {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Extract task index from argument and mark the task as not done
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            tasks.unmarkTaskAsDone(index);
            storage.saveTasks(tasks);
        }
    },
    /**
     * Command to add a todo task to tasklist.
     */
    ADD_TODO {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new tasks.ToDo task and add it to the task list
            Task task = tasks.initTask(message, "todo");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    /**
     * Command to add a deadline task to tasklist.
     */
    ADD_DEADLINE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new tasks.Deadline task and add it to the task list
            Task task = tasks.initTask(message, "deadline");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    /**
     * Command to add a event task to tasklist.
     */
    ADD_EVENT {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new tasks.Event task and add it to the task list
            Task task = tasks.initTask(message, "event");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },

    /**
     * Command to delete a task from tasklist by index.
     */
    DELETE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            Task task = tasks.removeTaskfromTaskList(index);
            ui.triggerDeleteMessage(task);
            storage.saveTasks(tasks);
        }
    },
    FIND {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            String[] inputs = message.split(" ");
            String queryString = inputs[1];
            ArrayList<Task> tempList = new ArrayList<>();
            TaskList tempTaskList = tasks.filter(queryString);
            tempTaskList.yapTasks();
        }
    }
    ;


    /**
     * Executes the command with the given parameters.
     * Each command defines its own execution behavior.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI instance for user interaction.
     * @param storage The storage instance for saving and loading tasks.
     * @param message The additional message or data required for command execution.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
        throw new UnsupportedOperationException("This command does not take any arguments.");
    }


}
