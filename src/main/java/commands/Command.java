package commands;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.YpxmmException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;



/**
 * Enum representing different commands that can be executed by the program.
 */
public enum Command {

    /**
     * Command to list all tasks.
     */
    LIST {
        /**
         * Executes the command to list all tasks and returns the result as a string.
         *
         * This method executes the command to list all tasks and returns the result as a string.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @return a string representing the list of tasks
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            return ui.printList(tasklist.getTasks()).trim();
        }
    },

    /**
     * Command to find tasks with a certain keyword or keywords.
     */
    FIND {
        /**
         * Executes the command to find tasks with a certain keyword or keywords and returns the result as a string.
         *
         * This method executes the command to find tasks with a certain keyword or keywords and returns the
         * result as a string.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @return a string representing the message for the found tasks
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            return ui.findMessage(tasklist.findTask(parsed.get(1))).trim();
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
         * @return a string representing the message for marking the task as done
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage,
                              ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.getTasks().get(index - 1);
                task.markTask();
                storage.reWrite(tasklist);
                return ui.markMessage(task);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got "
                        + (tasklist.getTasks().isEmpty() ? "no tasks to mark."
                        : (tasklist.getTasks().size() == 1 ? "1 task," : " " + tasklist.getTasks().size() + " tasks,")
                        + " enter any number from 1 to " + tasklist.getTasks().size()));
            } catch (YpxmmException y) {
                return y.getMessage();
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
         * @return a string representing the message for unmarking the task
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage,
                              ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.getTasks().get(index - 1);
                task.unmarkTask();
                storage.reWrite(tasklist);
                return ui.unmarkMessage(task);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got "
                        + (tasklist.getTasks().isEmpty() ? "no tasks to unmark."
                        : (tasklist.getTasks().size() == 1 ? "1 task," : " " + tasklist.getTasks().size() + " tasks,")
                        + " enter any number from 1 to " + tasklist.getTasks().size()));
            } catch (YpxmmException y) {
                return y.getMessage();
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
         * @return a string representing the message that a todo task has been added
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new ToDo(parsed.get(1));
                tasklist.addTask(parsed, task);
                Collections.sort(tasklist.getTasks());
                storage.reWrite(tasklist);
                return ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                return y.getMessage();
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
         * @return a string representing the message that a deadline task has been added
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new Deadline(parsed.get(1).trim(), parsed.get(2).trim());
                tasklist.addTask(parsed, task);
                Collections.sort(tasklist.getTasks());
                storage.reWrite(tasklist);
                return ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                return y.getMessage();
            } catch (DateTimeParseException e) {
                return "Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)";
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
         * @return a string representing the message that a event task has been added
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new Event(parsed.get(1).trim(), parsed.get(2).trim(), parsed.get(3).trim());
                tasklist.addTask(parsed, task);
                Collections.sort(tasklist.getTasks());
                storage.reWrite(tasklist);
                return ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                return y.getMessage();
            } catch (DateTimeParseException e) {
                return "Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)";
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
         * @return a string representing the commands and how they are used
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            return ui.getCommands();
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
         * @return a string representing the message that a task has been deleted
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage,
                              ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.getTasks().get(index - 1);
                tasklist.deleteTask(index);
                storage.reWrite(tasklist);
                return ui.deleteTaskMessage(task, tasklist);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got "
                        + (tasklist.getTasks().isEmpty() ? "no tasks to delete."
                        : (tasklist.getTasks().size() == 1 ? "1 task," : " " + tasklist.getTasks().size() + " tasks,")
                        + " enter any number from 1 to " + tasklist.getTasks().size()));
            } catch (YpxmmException y) {
                return y.getMessage();
            }
        }
    },

    PRIORITISE {
        /**
         * Executes the command to prioritise a task.
         *
         * @param tasklist the list of tasks
         * @param ui the user interface
         * @param storage the storage utility
         * @param parsed the parsed command arguments
         * @return a string representing the message that a task has been deleted
         * @throws YpxmmException if an error occurs during execution
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage,
                              ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.getTasks().get(index - 1);
                task.setPriority(parsed.get(2));
                storage.reWrite(tasklist);
                return ui.prioritiseTaskMessage(task, tasklist);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got "
                        + (tasklist.getTasks().isEmpty() ? "no tasks to prioritise."
                        : (tasklist.getTasks().size() == 1 ? "1 task," : " " + tasklist.getTasks().size() + " tasks,")
                        + " enter any number from 1 to " + tasklist.getTasks().size()));
            } catch (YpxmmException y) {
                return y.getMessage();
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
         * @return a string representing the goodbye message
         */
        @Override
        public String execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            return ui.sayGoodbye();
        }
    };

    /**
     * Executes the command with the specified parameters.
     *
     * @param tasklist the list of tasks
     * @param ui       the user interface
     * @param storage  the storage utility
     * @param parsed   the parsed command arguments
     * @return a string with its respective representation
     * @throws YpxmmException if an error occurs during execution
     */
    public abstract String execute(TaskList tasklist, Ui ui, Storage storage,
                                   ArrayList<String> parsed) throws YpxmmException;
}
