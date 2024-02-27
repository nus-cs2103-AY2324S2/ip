package bytetalker.task;

import bytetalker.exception.*;

import bytetalker.parser.Parser;
import bytetalker.storage.Storage;
import bytetalker.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a list of task and operations to process the task.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Changes the status of the specified task as done.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into the hard disk.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution
     */
    public String markTask(String[] splitMessages, Storage storage, Ui ui) {
        assert tasks != null;
        assert splitMessages != null;
        assert ui != null;

        int index = 0;
        try {
            Parser.checkCommand(splitMessages);
            index = Integer.parseInt(splitMessages[1]) - 1;
        } catch (NumberFormatException e) {
            return ui.showMissingIndexMessage();
        } catch (CommandWrongFormatExcpetion e) {
            return e.getMessage();
        }

        if (index < 0 || index > this.tasks.size()) {
            return ui.showInvalidIndexMessage();
        }

        try {
            this.tasks.get(index).setStatus(true);
            storage.storeTasks(this.tasks);
            return ui.showMarkTaskMsg(this.tasks.get(index));
        } catch (IOException e) {
            this.tasks.get(index).setStatus(false);
            return ui.showStoreTaskErrorMessage();
        }
    }

    /**
     * Changes the status of the specified task as undone
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into the hard disk.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution
     */
    public String unmarkTask(String[] splitMessages, Storage storage, Ui ui) {
        assert tasks != null;
        assert splitMessages != null;
        assert ui != null;
        assert storage != null;

        int index = 0;
        try {
            Parser.checkCommand(splitMessages);
            index = Integer.parseInt(splitMessages[1]) - 1;
        } catch (NumberFormatException e) {
            return ui.showMissingIndexMessage();
        } catch (CommandWrongFormatExcpetion e) {
            return e.getMessage();
        }
        if (index < 0 || index > this.tasks.size()) {
            return ui.showInvalidIndexMessage();
        }

        try {
            this.tasks.get(index).setStatus(false);
            storage.storeTasks(this.tasks);
            return ui.showUnmarkTaskMsg(this.tasks.get(index));
        } catch (IOException e) {
            this.tasks.get(index).setStatus(true);
            return ui.showStoreTaskErrorMessage();
        }
    }

    /**
     * Adds a task to the list.
     * It is a general method that calls individual methods to add todo, deadline and event
     * to the list.
     *
     * @param messageContainer Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into the hard disk.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution
     */

    public String addTask(String[] messageContainer, Storage storage, Ui ui) {
        try {
            Task task = determineTaskToBeAdded(messageContainer);
            if (task != null) {
                this.tasks.add(task);
                storage.storeTasks(this.tasks);
                return ui.showAddTaskMsg(task, this.tasks.size());
            } else {
                return "Please follow the correct format.";
            }
        } catch (IOException e) {
            this.tasks.remove(this.tasks.size() - 1);
            return ui.showStoreTaskErrorMessage();
        } catch (UnsupportedCommandException ex) {
            String errorUnsupportedMessage = ex.getMessage() + ". Please only enter the supported types of task.";
            return errorUnsupportedMessage;
        } catch (UnsupportedDateTimeFormatException ex) {
            String errorUnsupportedDateTimeMessage = ex.getMessage() + ".";
            return errorUnsupportedDateTimeMessage;
        }
    }

    /**
     * Determines the type of the task and call the respective methods of that type of task to add the task into the
     * list.
     *
     * @param messageContainer Parsed messages of user input and processed by Parser.
     * @return Task object ready to be added to the list.
     * @throws UnsupportedCommandException
     */
    private Task determineTaskToBeAdded(String[] messageContainer)
            throws UnsupportedCommandException,
            UnsupportedDateTimeFormatException{
        boolean isTodo = messageContainer[0].equals("todo");
        boolean isDeadline = messageContainer[0].equals("deadline");
        boolean isEvent = messageContainer[0].equals("event");
        if (isTodo) {
            return addTodo(messageContainer);
        } else if (isDeadline) {
            return addDeadline(messageContainer);
        } else if (isEvent) {
            return addEvent(messageContainer);
        } else {
            throw new UnsupportedCommandException("This is unsupported task");
        }
    }

    /**
     * Creates a Todo object based on the user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return Todo object that contains the task content specified by user.
     */
    public Todo addTodo(String[] splitMessages) {
        assert tasks != null;
        assert splitMessages != null;

        Todo task = null;
        try {
            String[] parsedTodoInputs = Parser.parseTodoAddInput(splitMessages);
            task = new Todo(parsedTodoInputs[0]);
            return task;
        } catch (TodoUnsupportedFormatException e) {
            return null;
        }
    }

    /**
     * Creates a Deadline object based on the user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return Deadline object that contains the task content and deadline of the task specified by the user.
     */
    public Deadline addDeadline(String[] splitMessages)
            throws UnsupportedDateTimeFormatException {
        assert tasks != null;
        assert splitMessages != null;

        Deadline task = null;
        try {
            String[] parsedDeadlineInput = Parser.parseDeadlineAddInput(splitMessages);
            if (Parser.parseDateTime(parsedDeadlineInput[1]) == null) {
                throw new UnsupportedDateTimeFormatException("Please use the correct format of DateTime");
            }
            task = new Deadline(parsedDeadlineInput[0], Parser.parseDateTime(parsedDeadlineInput[1]));
            return task;
        } catch (DeadlineUnsupportedFormatException e) {
            return null;
        }
    }

    /**
     * Creates an Event object based on the user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return Event object that contains the event information, from when and until when specified by the user.
     */
    public Event addEvent(String[] splitMessages) throws UnsupportedDateTimeFormatException {
        assert tasks != null;
        assert splitMessages != null;

        Event task = null;
        try {
            String[] parsedEventInput = Parser.parseEventAddInput(splitMessages);
            if (Parser.parseDateTime(parsedEventInput[1]) == null
                    || Parser.parseDateTime(parsedEventInput[2]) == null) {
                throw new UnsupportedDateTimeFormatException("Please use the correct format of DateTime");
            }
            task = new Event(parsedEventInput[0], Parser.parseDateTime(parsedEventInput[1]),
                    Parser.parseDateTime(parsedEventInput[2]));
            return task;
        } catch (EventUnsupportedFormatException e) {
            return null;
        }
    }

    /**
     * Deletes the specified task from the list
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into hard disk.
     * @param ui Utility object to print out the message to user to result the process of the method.
     * @return Message for successful or unsuccessful execution
     */
    public String deleteTask(String[] splitMessages, Storage storage, Ui ui) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        int position = 0;
        try {
            Parser.checkCommand(splitMessages);
            position = Integer.parseInt(splitMessages[1]);
        } catch (NumberFormatException e) {
            return ui.showMissingIndexMessage();
        } catch (CommandWrongFormatExcpetion e) {
            return e.getMessage();
        }

        if (position - 1 < 0 || position - 1 > this.tasks.size()) {
            return ui.showInvalidIndexMessage();
        }

        Task task = this.tasks.get(position - 1);
        try {
            this.tasks.remove(position - 1);
            storage.storeTasks(this.tasks);
            return ui.showDeleteTaskMsg(task, this.tasks.size());
        } catch (IOException e) {
            this.tasks.add(position - 1, task);
            return ui.showStoreTaskErrorMessage();
        }
    }

    /**
     * Finds all the tasks with content that contains the user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution
     */
    public String findTask(String[] splitMessages, Ui ui) {
        assert tasks != null;
        assert splitMessages != null;
        assert ui != null;

        try {
            Parser.checkCommand(splitMessages);
        } catch (CommandWrongFormatExcpetion e) {
            return e.getMessage();
        }

        String content = Parser.parseFindInput(splitMessages);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.getTask().contains(content)) {
                foundTasks.add(currentTask);
            }
        }
        return ui.displayFoundTasks(foundTasks);
    }

    /**
     * Updates a parameter of the task based on user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into hard disk.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution
     */
    public String editTask(String[] splitMessages, Storage storage, Ui ui) {
        int index = 0;
        try {
            Parser.checkCommand(splitMessages);
            index = Integer.parseInt(splitMessages[1]) - 1;
        } catch (NumberFormatException e) {
            return ui.showMissingIndexMessage();
        } catch (CommandWrongFormatExcpetion e) {
            return e.getMessage();
        }
        if (index < 0 || index > this.tasks.size()) {
            return ui.showInvalidIndexMessage();
        }

        Task task = this.tasks.get(index);
        try {
            String message = "";
            if (splitMessages[2].equals("/content")) {
                message = editContent(splitMessages, task, ui);
            } else if (splitMessages[2].equals("/by")) {
                message = editDeadline(splitMessages, task, ui);
            } else if (splitMessages[2].equals("/from")) {
                message = editFrom(splitMessages, task, ui);
            } else if (splitMessages[2].equals("/to")) {
                message = editTo(splitMessages, task, ui);
            } else {
                message = "Unsupported Task";
            }
            storage.storeTasks(this.tasks);
            return message;
        } catch (IOException e) {
            return "Failed saving changes into the file. Please restart the program";
        }
    }

    /**
     * Updates content parameter of the task.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param task Task object to be updated.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution.
     */
    public String editContent(String[] splitMessages, Task task, Ui ui) {
        String content = Parser.parseContentUpdateInput(splitMessages);
        task.updateTask(content);
        return ui.showUpdatedTaskMessage(task);
    }

    /**
     * Updates deadline parameter of the task.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param task Task object to be updated.
     * @param ui Utility objet to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution
     */
    public String editDeadline(String[] splitMessages, Task task, Ui ui) {
        LocalDateTime deadline = Parser.parseDateTimeUpdateInput(splitMessages);
        if (task.getTaskType() == TaskType.DEADLINE) {
            ((Deadline) task).updateDeadline(deadline);
            return ui.showUpdatedTaskMessage(task);
        }
        return ui.showWrongFormatUpdateMessage();
    }

    /**
     * Updates from parameter of the task.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param task Task object to be updated.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution.
     */
    public String editFrom(String[] splitMessages, Task task, Ui ui) {
        LocalDateTime time = Parser.parseDateTimeUpdateInput(splitMessages);
        if (task.getTaskType() == TaskType.EVENT) {
            ((Event) task).updateFrom(time);
            return ui.showUpdatedTaskMessage(task);
        }
        return ui.showWrongFormatUpdateMessage();
    }

    /**
     * Updates to parameter of the task.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @param task Task object to be updated.
     * @param ui Utility object to print out the message to user to inform the result of the method.
     * @return Message for successful or unsuccessful execution.
     */
    public String editTo(String[] splitMessages, Task task, Ui ui) {
        LocalDateTime time = Parser.parseDateTimeUpdateInput(splitMessages);
        if (task.getTaskType() == TaskType.EVENT) {
            ((Event) task).updateTo(time);
            return ui.showUpdatedTaskMessage(task);
        }
        return ui.showWrongFormatUpdateMessage();
    }
}
