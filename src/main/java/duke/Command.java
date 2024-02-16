//package duke;
//import java.util.List;
//
///**
// * Represents a command that can be executed by the Duke program.
// */
//public class Command {
//    public enum CommandType {
//        BYE, LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, FIND, INVALID
//
//    }
//    /**
//     * Enumerates the possible types of commands.
//     */
//    private CommandType type;
//    private String argument;
//
//    /**
//     * Constructs a Command object with the specified type and argument.
//     *
//     * @param type     The type of the command.
//     * @param argument The argument associated with the command.
//     */
//
//    public Command(CommandType type, String argument) {
//        this.type = type;
//        this.argument = argument;
//    }
//
//    /**
//     * Executes the command based on its type.
//     *
//     * @param tasks   The TaskList on which the command operates.
//     * @param ui      The Ui used to interact with the user.
//     * @param storage The Storage used to save and load tasks.
//     * @throws DukeException If there is an error executing the command.
//     */
//
//    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
//        switch (type) {
//        case BYE:
//            ui.showGoodbye();
//            break;
//        case LIST:
//            ui.showTaskList(tasks);
//            break;
//        case TODO:
//            tasks.addTask(new ToDo(argument));
//            ui.showTaskAdded(tasks.getTasks().get(tasks.getSize() - 1), tasks.getSize());
//            storage.saveTasks(tasks.getTasks());
//            break;
//        case DEADLINE:
//            String[] deadlineDetails = argument.split("/by");
//            if (deadlineDetails.length == 2) {
//                tasks.addTask(new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim()));
//                ui.showTaskAdded(tasks.getTasks().get(tasks.getSize() - 1), tasks.getSize());
//                storage.saveTasks(tasks.getTasks());
//            } else {
//              //throw new DukeException("Invalid deadline format.
//              Please use: deadline <description>
//                /by <date/time>");
//            }
//            break;
//        case EVENT:
//            // Handle event command
//            String[] eventDetails = argument.split("/from|/to");
//            if (eventDetails.length == 3) {
//                tasks.addTask(new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim()));
//                ui.showTaskAdded(tasks.getTasks().get(tasks.getSize() - 1), tasks.getSize());
//                storage.saveTasks(tasks.getTasks());
//            } else {
//                throw new DukeException("Invalid event format. Please use: event <description> "
//                        + "/from <start> /to <end>");
//            }
//            break;
//        case DELETE:
//            // Handle delete command
//            int taskIndexDelete = Integer.parseInt(argument) - 1;
//            tasks.deleteTask(taskIndexDelete, ui);
//            storage.saveTasks(tasks.getTasks());
//            break;
//        case MARK:
//            // Handle mark command
//            int markIndex = Integer.parseInt(argument) - 1;
//            if (isValidIndex(markIndex, tasks.getSize())) {
//                tasks.markTaskAsDone(markIndex);
//                ui.showTaskMarked(tasks.getTasks().get(markIndex));
//                storage.saveTasks(tasks.getTasks());
//            } else {
//                ui.showInvalidTaskIndex();
//            }
//            break;
//        case UNMARK:
//            // Handle unmark command
//            int unmarkIndex = Integer.parseInt(argument) - 1;
//            if (isValidIndex(unmarkIndex, tasks.getSize())) {
//                tasks.unmarkTaskAsDone(unmarkIndex);
//                ui.showTaskUnmarked(tasks.getTasks().get(unmarkIndex));
//                storage.saveTasks(tasks.getTasks());
//            } else {
//                ui.showInvalidTaskIndex();
//            }
//            break;
//        case FIND:
//            // Handle FIND command
//            List<Task> matchingTasks = tasks.findTasksByKeyword(argument);
//            ui.showMatchingTasks(matchingTasks);
//            break;
//        case INVALID:
//            throw new DukeException("I'm sorry, but I don't know what that means :-(");
//        default:
//            throw new IllegalStateException("Unexpected value: " + type);
//        }
//    }
//
//    /**
//     * Checks if the given index is valid for the current task list.
//     *
//     * @param index The index to be checked.
//     * @param size  The size of the task list.
//     * @return True if the index is valid, false otherwise.
//     */
//    private boolean isValidIndex(int index, int size) {
//        return index >= 0 && index < size;
//    }
//}
package duke;

import java.util.List;

/**
 * Represents a command that can be executed by the Duke program.
 */
public class Command {
    /**
     * * Enumerates the possible types of commands.
     */
    public enum CommandType {
        WELCOME, BYE, LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, FIND, INVALID
    }
    private CommandType type;
    private String argument;
    /**
     * Constructs a Command object with the specified type and argument.
     *
     * @param type     The type of the command.
     * @param argument The argument associated with the command.
     */
    public Command(CommandType type, String argument) {
        this.type = type;
        this.argument = argument;
        //assert argument != null : "Argument should not be null or empty";
    }
    public CommandType getType() {
        return type;
    }
    /**
     * Executes the command based on its type.
     *
     * @param tasks   The TaskList on which the command operates.
     * @param ui      The Ui used to interact with the user.
     * @param storage The Storage used to save and load tasks.
     * @throws DukeException If there is an error executing the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        StringBuilder response = new StringBuilder();
        switch (type) {
        case WELCOME:
            return ui.showWelcome();
        case BYE:
            return ui.showGoodbye();
        case LIST:
            List<Task> taskList = tasks.getTasks();
            return ui.showTaskList(taskList);
        case TODO:
            //            ToDo todo = new ToDo(argument);
            //            tasks.addTask(todo);
            //            storage.saveTasks(tasks.getTasks());
            //            return ui.showTaskAdded(todo, tasks.getSize()); // Call showTaskAdded method
            return handleTodoCommand(argument, tasks, ui, storage);
        case DEADLINE:
            //String[] deadlineDetails = argument.split("/by", 2);
            //assert deadlineDetails.length == 2 : "Deadline argument should contain description "
            //+ "and date/time separated by '/by'";
            //if (deadlineDetails.length != 2) {
            //throw new DukeException("Invalid deadline format. Please use: deadline <description> /by <date/time>");
            //}
            //Deadline deadline = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
            //tasks.addTask(deadline);
            //storage.saveTasks(tasks.getTasks());
            //return ui.showTaskAdded(deadline, tasks.getSize());
            return handleDeadlineCommand(argument, tasks, ui, storage);
        case EVENT:
            //String[] eventDetails = argument.split("/from|/to", 3);
            //if (eventDetails.length != 3) {
            //throw new DukeException("Invalid event format. Please use: event <description> "
            //+ "/from <start> /to <end>");
            //}
            //Event event = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
            //tasks.addTask(event);
            //storage.saveTasks(tasks.getTasks());
            //return ui.showTaskAdded(event, tasks.getSize());
            return handleEventCommand(argument, tasks, ui, storage);
        case DELETE:
            //try {
            //int indexToDelete = Integer.parseInt(argument) - 1;
            //assert isValidIndex(indexToDelete, tasks.getSize()) : "Index out of bounds";
            //Task taskToDelete = tasks.deleteTask(indexToDelete);
            //storage.saveTasks(tasks.getTasks());
            //return ui.showTaskDeleted(taskToDelete, tasks.getSize());
            //} catch (NumberFormatException e) {
            //throw new DukeException("Please specify a valid task number to delete.");
            //}
            return handleDeleteCommand(argument, tasks, ui, storage);
        case MARK:
            //try {
            //int indexToMark = Integer.parseInt(argument) - 1;
            //Task markedTask = tasks.markTaskAsDone(indexToMark);
            //storage.saveTasks(tasks.getTasks());
            //return ui.showTaskMarked(markedTask);
            //} catch (NumberFormatException e) {
            //throw new DukeException("Please specify a valid task number to mark as done.");
            //} catch (IndexOutOfBoundsException e) {
            //throw new DukeException("Task not found. Please specify a valid task number.");
            //}
            return handleMarkCommand(argument, tasks, ui, storage);
        case UNMARK:
            //try {
            //int indexToUnmark = Integer.parseInt(argument) - 1;
            //Task unmarkedTask = tasks.unmarkTaskAsDone(indexToUnmark);
            //storage.saveTasks(tasks.getTasks());
            //return ui.showTaskUnmarked(unmarkedTask);
            //} catch (NumberFormatException e) {
            //throw new DukeException("Please specify a valid task number to unmark.");
            //} catch (IndexOutOfBoundsException e) {
            //throw new DukeException("Task not found. Please specify a valid task number.");
            //}
            return handleUnmarkCommand(argument, tasks, ui, storage);
        case FIND:
            List<Task> matchingTasks = tasks.findTasksByKeyword(argument);
            return ui.showMatchingTasks(matchingTasks);
            //            if (matchingTasks.isEmpty()) {
            //                response.append("No matching tasks found.");
            //            } else {
            //                response.append("Here are the matching tasks:\n");
            //                for (int i = 0; i < matchingTasks.size(); i++) {
            //                    response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            //                }
            //            }
            //            break;
        case INVALID:
            response.append("I'm sorry, but I don't know what that means :-(");
            break;
        default:
            throw new DukeException("Unexpected command type: " + type);
        }
        return response.toString();
    }
    /**
     * Handles the execution of the TODO command, adding a new ToDo task to the task list.
     *
     * @param argument The description of the ToDo task.
     * @param tasks    The TaskList containing the tasks.
     * @param ui       The Ui used to interact with the user.
     * @param storage  The Storage used to save tasks.
     * @return A message indicating that the task has been added.
     * @throws DukeException If there is an error handling the command.
     */
    public String handleTodoCommand(String argument, TaskList tasks, Ui ui, Storage storage) throws DukeException {
//        ToDo todo = new ToDo(argument);
//        tasks.addTask(todo);
//        storage.saveTasks(tasks.getTasks());
//        return ui.showTaskAdded(todo, tasks.getSize());
        ToDo todo = new ToDo(argument);
        // Check if the task already exists
        if (tasks.containsTask(todo)) {
            return ui.showDuplicateTask(todo);
        }
        tasks.addTask(todo);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(todo, tasks.getSize());
    }
    /**
     * Handles the execution of the DEADLINE command, adding a new Deadline task to the task list.
     *
     * @param argument The description and deadline of the task (format: description /by date/time).
     * @param tasks    The TaskList containing the tasks.
     * @param ui       The Ui used to interact with the user.
     * @param storage  The Storage used to save tasks.
     * @return A message indicating that the task has been added.
     * @throws DukeException If the deadline format is invalid or there is an error handling the command.
     */
    public String handleDeadlineCommand(String argument, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] deadlineDetails = argument.split("/by", 2);
        if (deadlineDetails.length != 2) {
            throw new DukeException("Invalid deadline format. Please use: deadline <description> /by <date/time>");
        }
        Deadline deadline = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
        if (tasks.containsTask(deadline)) {
            return ui.showDuplicateTask(deadline);
        }
        tasks.addTask(deadline);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(deadline, tasks.getSize());
    }
    /**
     * Handles the execution of the EVENT command, adding a new Event task to the task list.
     *
     * @param argument The description and event details (format: description /from start /to end).
     * @param tasks    The TaskList containing the tasks.
     * @param ui       The Ui used to interact with the user.
     * @param storage  The Storage used to save tasks.
     * @return A message indicating that the event task has been added.
     * @throws DukeException If the event format is invalid or there is an error handling the command.
     */
    public String handleEventCommand(String argument, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] eventDetails = argument.split("/from|/to", 3);
        if (eventDetails.length != 3) {
            throw new DukeException("Invalid event format. Please use: event <description> /from <start> /to <end>");
        }
        Event event = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
        if (tasks.containsTask(event)) {
            return ui.showDuplicateTask(event);
        }
        tasks.addTask(event);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(event, tasks.getSize());
    }
    /**
     * Handles the execution of the DELETE command, deleting a task from the task list.
     *
     * @param argument The index of the task to be deleted.
     * @param tasks    The TaskList containing the tasks.
     * @param ui       The Ui used to interact with the user.
     * @param storage  The Storage used to save tasks.
     * @return A message indicating that the task has been deleted.
     * @throws DukeException If the specified task index is invalid or there is an error handling the command.
     */
    public String handleDeleteCommand(String argument, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int indexToDelete = Integer.parseInt(argument) - 1;
            assert isValidIndex(indexToDelete, tasks.getSize()) : "Index out of bounds";
            Task taskToDelete = tasks.deleteTask(indexToDelete);
            storage.saveTasks(tasks.getTasks());
            return ui.showTaskDeleted(taskToDelete, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a valid task number to delete.");
        }
    }
    /**
     * Handles the "mark" command, which marks a task as done based on the provided task number.
     *
     * @param argument the argument provided with the "mark" command, which is expected to be the task number.
     * @param tasks the TaskList containing the tasks.
     * @param ui the Ui object for user interaction.
     * @param storage the Storage object for saving tasks.
     * @return a String representing the message to be displayed after marking the task as done.
     * @throws DukeException if the provided argument is not a valid task number or if the task is not found.
     */
    public String handleMarkCommand(String argument, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int indexToMark = Integer.parseInt(argument) - 1;
            Task markedTask = tasks.markTaskAsDone(indexToMark);
            storage.saveTasks(tasks.getTasks());
            return ui.showTaskMarked(markedTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a valid task number to mark as done.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found. Please specify a valid task number.");
        }
    }
    /**
     * Handles the "unmark" command, which marks a task as not done based on the provided task number.
     *
     * @param argument the argument provided with the "unmark" command, which is expected to be the task number.
     * @param tasks the TaskList containing the tasks.
     * @param ui the Ui object for user interaction.
     * @param storage the Storage object for saving tasks.
     * @return a String representing the message to be displayed after marking the task as not done.
     * @throws DukeException if the provided argument is not a valid task number or if the task is not found.
     */
    public String handleUnmarkCommand(String argument, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int indexToUnmark = Integer.parseInt(argument) - 1;
            Task unmarkedTask = tasks.unmarkTaskAsDone(indexToUnmark);
            storage.saveTasks(tasks.getTasks());
            return ui.showTaskUnmarked(unmarkedTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a valid task number to unmark.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found. Please specify a valid task number.");
        }
    }



    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}

