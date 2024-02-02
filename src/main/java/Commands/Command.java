package Commands;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

import Exceptions.YpxmmException;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Enum representing different commands that can be executed by the program.
 */
public enum Command {

    /**
     * Command to list all tasks.
     */
    LIST {
        /**
         * Executes the command to list all tasks.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.printList(tasklist.tasks);
        }
    },

    /**
     * Command to find tasks with a certain keyword or keywords.
     */
    FIND {
        /**
         * Executes the command to find tasks with a certain keyword or keywords.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.findMessage(tasklist.findTask(parsed.get(1)));
        }
    },

    /**
     * Command to mark a task as done.
     */
    MARK {
        /**
         * Executes the command to mark a task as done.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.tasks.get(index - 1);
                task.markTask();
                storage.reWrite(tasklist);
                ui.markMessage(task);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasklist.tasks.isEmpty() ? "no tasks to mark." : tasklist.tasks.size() +
                                " tasks, enter any number from 1 to " + tasklist.tasks.size()));
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },

    /**
     * Command to unmark a task as done.
     */
    UNMARK {
        /**
         * Executes the command to unmark a task as done.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.tasks.get(index - 1);
                task.unmarkTask();
                storage.reWrite(tasklist);
                ui.unmarkMessage(task);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasklist.tasks.isEmpty() ? "no tasks to unmark." : tasklist.tasks.size() +
                                " tasks, enter any number from 1 to " + tasklist.tasks.size()));
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },

    /**
     * Command to add a todo task.
     */
    TODO {
        /**
         * Executes the command to add a todo task.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new ToDo(parsed.get(1));
                tasklist.addTask(parsed, task);
                storage.appendToFile(task.toWrite());
                ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },

    /**
     * Command to add a deadline task.
     */
    DEADLINE {
        /**
         * Executes the command to add a deadline task.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new Deadline(parsed.get(1).trim(), parsed.get(2).trim());
                tasklist.addTask(parsed, task);
                storage.appendToFile(task.toWrite());
                ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
            }
        }
    },

    /**
     * Command to add an event task.
     */
    EVENT {
        /**
         * Executes the command to add an event task.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new Event(parsed.get(1).trim(), parsed.get(2).trim(), parsed.get(3).trim());
                tasklist.addTask(parsed, task);
                storage.appendToFile(task.toWrite());
                ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
            }
        }
    },

    /**
     * Command to get available commands.
     */
    GETCOMMANDS {
        /**
         * Executes the command to get available commands.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.getCommands();
        }
    },

    /**
     * Command to delete a task.
     */
    DELETE {
        /**
         * Executes the command to delete a task.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.tasks.get(index - 1);
                tasklist.deleteTask(index);
                storage.reWrite(tasklist);
                ui.deleteTaskMessage(task, tasklist);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasklist.tasks.isEmpty() ? "no tasks to delete." : tasklist.tasks.size() +
                                " tasks, enter any number from 1 to " + tasklist.tasks.size()));
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },

    /**
     * Command to exit the program.
     */
    BYE {
        /**
         * Executes the command to exit the program.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.sayGoodbye();
        }
    };

    /**
     * Executes the command with the specified parameters.
     *
     * @param tasklist the list of tasks
     * @param ui the user interface
     * @param storage the storage utility
     * @param parsed the parsed command arguments
     * @throws YpxmmException if an error occurs during execution
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException;
}