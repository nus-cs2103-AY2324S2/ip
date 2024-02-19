package commands;


import exceptions.TaskYapperException;
import main.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


/**
 * Defines the set of commands that can be executed within the TaskYapper application.
 * Each enum constant represents a distinct command and defines its own execution behavior.
 */

public enum Command {
    /**
     * Command to terminate the application. It saves the current state of tasks before exiting.
     */
    BYE {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) {
            storage.saveTasks(tasks);
            return "Stoppin' the YAP...\n";
        }
    },
    /**
     * Command to list all tasks in the task list.
     */
    YAP {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) {
            return tasks.yapTasks();
        }
    },
    /**
     * Command to mark a specified task as done.
     */
    MARK {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            String[] inputs = message.split(" ");
            int index;
            try {
                index = Integer.parseInt(inputs[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new TaskYapperException("You didn't specify an input integer yapper");
            } catch (NumberFormatException e) {
                throw new TaskYapperException("Input has to be an integer value, yapper");
            }
            storage.saveTasks(tasks);
            return tasks.markTaskAsDone(index);
        }
    },
    /**
     * Command to unmark a specified task as done.
     */
    UNMARK {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            // Extract task index from argument and mark the task as not done
            String[] inputs = message.split(" ");
            int index;
            try {
                index = Integer.parseInt(inputs[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new TaskYapperException("You didn't specify an input integer yapper");
            } catch (NumberFormatException e) {
                throw new TaskYapperException("Provide an integer value, yapper");
            }
            storage.saveTasks(tasks);
            return tasks.unmarkTaskAsDone(index);
        }
    },
    /**
     * Command to add a todo task to tasklist.
     */
    ADD_TODO {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            // Create a new tasks.ToDo task and add it to the task list
            Task task = tasks.initTask(message, "todo");
            tasks.addTasktoTaskList(task);
            storage.saveTasks(tasks);
            return ui.triggerAddMessage(task);
        }
    },
    /**
     * Command to add a deadline task to tasklist.
     */
    ADD_DEADLINE {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            // Create a new tasks.Deadline task and add it to the task list
            Task task = tasks.initTask(message, "deadline");
            tasks.addTasktoTaskList(task);
            storage.saveTasks(tasks);
            return ui.triggerAddMessage(task);
        }
    },
    /**
     * Command to add a event task to tasklist.
     */
    ADD_EVENT {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            // Create a new tasks.Event task and add it to the task list
            Task task = tasks.initTask(message, "event");
            tasks.addTasktoTaskList(task);
            storage.saveTasks(tasks);
            return ui.triggerAddMessage(task);
        }
    },

    /**
     * Command to delete a task from tasklist by index.
     */
    DELETE {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            String[] inputs = message.split(" ");
            int index;
            try {
                index = Integer.parseInt(inputs[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new TaskYapperException("You didn't specify an input integer yapper");
            } catch (NumberFormatException e) {
                throw new TaskYapperException("Provide an integer value, yapper");
            }
            Task task = tasks.removeTaskfromTaskList(index);
            storage.saveTasks(tasks);
            return ui.triggerDeleteMessage(task);
        }
    },
    FIND {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            try {
                String[] inputs = message.split(" ");
                String queryString = inputs[1];
                TaskList tempTaskList = tasks.filterByString(queryString);
                return tempTaskList.yapTasks();
            } catch (IndexOutOfBoundsException e) {
                throw new TaskYapperException("Provide a suitable keyword/phrase, yapper");
            }
        }
    },
    SCHEDULE {
        @Override
        public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
            try {
                String[] inputs = message.split(" ");
                String queryString = inputs[1];
                TaskList tempTaskList = tasks.filterByDate(queryString);
                return tempTaskList.yapTasks();
            } catch (IndexOutOfBoundsException e) {
                throw new TaskYapperException("Provide a suitable keyword/phrase, yapper");
            }
        }
    };


    /**
     * Executes the command with the given parameters.
     * Each command defines its own execution behavior.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI instance for user interaction.
     * @param storage The storage instance for saving and loading tasks.
     * @param message The additional message or data required for command execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws TaskYapperException {
        throw new TaskYapperException("This command does not take any arguments.");
    }


}
