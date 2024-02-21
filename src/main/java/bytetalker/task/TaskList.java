package bytetalker.task;

import bytetalker.exception.ByteTalkerException;
import bytetalker.parser.Parser;
import bytetalker.storage.Storage;
import bytetalker.ui.Ui;

import java.io.IOException;
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
     * @param splitMessage Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into the hard disk.
     * @param ui Utility object to print out the message to user to inform the process of the method.
     */
    public String markTask(String[] splitMessage, Storage storage, Ui ui) {
        assert tasks != null;
        assert splitMessage != null;
        assert ui != null;

        int index = Integer.parseInt(splitMessage[1]) - 1;
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
     * @param splitMessage Parsed messages of user input and processed by Parser.
     * @param storage Utility object to store the changed list of tasks into the hard disk.
     * @param ui Utility object to print out the message to user to inform the process of the method.
     */
    public String unmarkTask(String[] splitMessage, Storage storage, Ui ui) {
        assert tasks != null;
        assert splitMessage != null;
        assert ui != null;
        assert storage != null;

        int index = Integer.parseInt(splitMessage[1]) - 1;
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
     * @param ui Utility object to print out the message to user to inform the process of the method.
     */

    public String addTask(String[] messageContainer, Storage storage, Ui ui) {
        try {
            Task task = determineTask(messageContainer);
            try {
                if (task != null) {
                    this.tasks.add(task);
                    storage.storeTasks(this.tasks);
                    return ui.showAddTaskMsg(task, this.tasks.size());
                } else {
                    return "No task added";
                }
            } catch (IOException e) {
                this.tasks.remove(this.tasks.size() - 1);
                return ui.showStoreTaskErrorMessage();
            }
        } catch (ByteTalkerException.UnsupportedTaskException ex) {
            String errorUnsupportedMessage = ex.getMessage() + ". Please only enter the supported types of task.";
            return errorUnsupportedMessage;
        }
    }

    private Task determineTask(String[] messageContainer) throws ByteTalkerException.UnsupportedTaskException {
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
            throw new ByteTalkerException.UnsupportedTaskException("This is unsupported task");
        }
    }



    /**
     * Creates a Todo object based on the user input.
     *
     * @param splitMessage Parsed messages of user input and processed by Parser.
     * @return Todo object that contains the task content specified by user.
     */
    public Todo addTodo(String[] splitMessage) {
        assert tasks != null;
        assert splitMessage != null;

        Todo task = null;
        try {
            ArrayList<String> parsedTodoInputs = Parser.parseTodoInput(splitMessage);
            task = new Todo(parsedTodoInputs.get(0));
        } catch (ByteTalkerException.TodoNoTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please enter the task, too.");
        } catch (ByteTalkerException.TodoUnsupportedFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task.");
        }
        return task;
    }

    /**
     * Creates a Deadline object based on the user input.
     *
     * @param splitMessage Parsed messages of user input and processed by Parser.
     * @return Deadline object that contains the task content and deadline of the task specified by the user.
     */
    public Deadline addDeadline(String[] splitMessage) {
        assert tasks != null;
        assert splitMessage != null;

        Deadline task = null;
        try {
            ArrayList<String> parsedDeadlineInput = Parser.parseDeadlineInput(splitMessage);
            task = new Deadline(parsedDeadlineInput.get(0), Parser.parseDateTime(parsedDeadlineInput.get(1)));
        } catch (ByteTalkerException.DeadlineUnsupportedFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task");
        } catch (ByteTalkerException.DeadlineWrongFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task");
        }
        return task;
    }

    /**
     * Creates an Event object based on the user input.
     *
     * @param splitMessage Parsed messages of user input and processed by Parser.
     * @return Event object that contains the event information, from when and until when specified by the user.
     */
    public Event addEvent(String[] splitMessage) {
        assert tasks != null;
        assert splitMessage != null;

        Event task = null;
        try {
            ArrayList<String> parsedEventInput = Parser.parseEventInput(splitMessage);
            task = new Event(parsedEventInput.get(0), Parser.parseDateTime(parsedEventInput.get(1)),
                    Parser.parseDateTime(parsedEventInput.get(2)));
        } catch (ByteTalkerException.EventWrongFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task");
        }
        return task;
    }

    /**
     * Deletes the specified task from the list
     *
     * @param position Position of the task in the list
     * @param storage Utility object to store the changed list of tasks into hard disk.
     * @param ui Utility object to print out the message to user to inform the process of the method.
     */
    public String deleteTask(int position, Storage storage, Ui ui) {
        assert tasks != null;
        assert position >= 0;
        assert ui != null;
        assert storage != null;

        Task task = this.tasks.get(position - 1);
        try {
            this.tasks.remove(position - 1);
            storage.storeTasks(this.tasks);
            return ui.showDeleteTaskMsg(task, this.tasks.size());
        } catch (IOException e) {
            this.tasks.add(task);
            return ui.showStoreTaskErrorMessage();
        }
    }

    /**
     * Finds all the tasks with content that contains the user input.
     *
     * @param splitMessage Parsed messages of user input and processed by Parser.
     * @param ui Utility object to print out the message to user to inform the process of the method.
     */
    public String findTask(String[] splitMessage, Ui ui) {
        assert tasks != null;
        assert splitMessage != null;
        assert ui != null;

        if (splitMessage.length != 2) {
            return "Please use the correct format";
        }
        String content = splitMessage[1];
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.getTask().contains(content)) {
                foundTasks.add(currentTask);
            }
        }
        return ui.displayFoundTasks(foundTasks);
    }
}
