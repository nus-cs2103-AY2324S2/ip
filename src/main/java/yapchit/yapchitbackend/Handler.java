package yapchit.yapchitbackend;

import yapchit.yapchitbackend.tasks.Deadline;
import yapchit.yapchitbackend.tasks.Event;
import yapchit.yapchitbackend.tasks.Task;
import yapchit.yapchitbackend.tasks.ToDo;
import yapchit.yapchitexceptions.InvalidDetailException;
import yapchit.yapchitexceptions.InvalidKeywordException;
import yapchit.yapchitexceptions.YapchitException;

import java.time.LocalDate;

/**
 * Class that acts as the 'brains' of the Yapchit program and handles all operations.
 */
public class Handler {

    /**
     * creates new handler instance
     */
    public Handler() {
    }

    /**
     * Primary function is to accept an input and operation and redirect it to appropriate handler function
     * for handling.
     *
     * @param input user input
     * @param op parsed operation from parser
     * @param tasks the full tasklist
     * @param ui the ui to interact with user
     * @param parser the parser to perform additional parsing
     * @param isNewTask determines whether task is created for the first time or imported from file
     * @throws YapchitException if there is conflict in terms of user input and operation to perform
     */
    public String handleOperation(String input, YapchitBackend.Operations op, TaskList tasks,
                                  Ui ui, Parser parser, boolean isNewTask) throws YapchitException {
        String[] parts = parser.parseInputParts(input);
        String output = "";
        switch (op) {
        case LIST:
            output = handleList(parts, tasks, ui);
            break;

        case MARK:
            output = handleMark(parts, tasks, ui, true);
            break;

        case UNMARK:
            output = handleMark(parts, tasks, ui, false);
            break;

        case DELETE:
            output = handleDelete(parts, tasks, ui);
            break;

        case FIND:
            output = handleFind(parts, tasks, ui);
            break;

        case DEADLINE:
            output = handleDeadline(input, isNewTask, tasks, ui, parser);
            break;

        case EVENT:
            output = handleEvent(input, isNewTask, tasks, ui);
            break;

        case TODO:
            output = handleTodo(input, isNewTask, tasks, ui);
            break;

        case UPDATE:
            output = handleUpdate(parts, tasks, ui, parser);
            break;

        default:
            throw new InvalidKeywordException("You have entered an invalid keyword. " +
                    "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', " +
                    "'event', 'bye', 'list', 'delete', 'update']");
        }

        return output;
    }

    /**
     * Creates a new event object based on details in provided input.
     *
     * @param inputParam containing details of the event
     * @param isNewTask boolean which identifies if this is a task being added to list for the first time
     * @param tasks list of tasks
     * @param ui ui object to interact with the user
     * @throws InvalidDetailException in case of mismatch in input and task detail requirements.
     */
    public String handleEvent(
            String inputParam, boolean isNewTask, TaskList tasks, Ui ui) throws InvalidDetailException {
        String input = inputParam;
        char isDone = getIsDone(input,isNewTask);
        input = getUpdatedInput(input, isNewTask);


        Event newEventObj = new Event("*", null, null);
        Task t = interpretAndUpdateEvent(newEventObj, input);
        tasks.addTask(t);

        String output = getTaskOutput(t, tasks, ui, isDone, isNewTask);
        return output;
    }

    private Event interpretAndUpdateEvent(Event t, String input) throws InvalidDetailException {

        int fromStart = input.indexOf("/from");
        int toStart = input.indexOf("/to");
        if (fromStart == -1 || toStart == -1 || fromStart >= toStart) {
            throw new InvalidDetailException("invalid /from and /to parameters. Please retry");
        }

        if (6 == fromStart || fromStart + 6 == toStart || toStart + 4 >= input.length()) {
            throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
        }

        String desc = input.substring(6, fromStart).strip();
        if (!desc.equals("*")) {
            t.setName(desc);
        }

        String from = input.substring(fromStart + 6, toStart).strip();
        if (!from.equals("*")) {
            t.setFrom(from);
        }

        String to = input.substring(toStart + 4).strip();
        if (!to.equals("*")) {
            t.setTo(to);
        }

        if (desc.length() == 0 || from.length() == 0 || to.length() == 0) {
            throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
        }

        return t;
    }

    /**
     * Creates a new deadline object based on details in provided input.
     *
     * @param inputParam containing details of the deadline
     * @param isNewTask boolean which identifies if this is a task being added to list for the first time
     * @param tasks list of tasks
     * @param ui ui object to interact with the user
     * @param parser parser object to parse input
     * @throws InvalidDetailException in case of mismatch in input and task detail requirements.
     */
    public String handleDeadline(
            String inputParam, boolean isNewTask, TaskList tasks, Ui ui, Parser parser) throws InvalidDetailException {
        String input = inputParam;
        char isDone = getIsDone(input,isNewTask);
        input = getUpdatedInput(input, isNewTask);


        Deadline newDeadlineObj = new Deadline("*", null);
        Task t = interpretAndUpdateDeadline(newDeadlineObj, input, parser);
        tasks.addTask(t);
        String output = getTaskOutput(t, tasks, ui, isDone, isNewTask);

        return output;
    }

    private Deadline interpretAndUpdateDeadline(Deadline t, String input, Parser parser) throws InvalidDetailException {

        int byStart = input.indexOf("/by");
        if (byStart == -1) {
            throw new InvalidDetailException("Missing 'by' parameter in deadline detail");
        }

        if (9 == byStart || byStart + 4 >= input.length()) {
            throw new InvalidDetailException("Deadline description and/or by parameter cannot be empty");
        }
        String desc = input.substring(9, byStart).strip();
        if (!desc.equals("*")) {
            t.setName(desc);
        }

        String dateString = input.substring(byStart + 4).strip();

        LocalDate by = t.getBy();
        if (!dateString.equals("*")) {
            by = parser.parseTimestamp(dateString);
            t.setBy(by);
        }

        if (desc.length() == 0 || by == null) {
            throw new InvalidDetailException("Invalid or empty deadline description and/or by parameter");
        }

        return t;
    }

    /**
     * Creates a new todo object based on details in provided input.
     *
     * @param inputParam containing details of the todo
     * @param isNewTask boolean which identifies if this is a task being added to list for the first time
     * @param tasks list of tasks
     * @param ui ui object to interact with the user
     * @throws InvalidDetailException in case of mismatch in input and task detail requirements.
     */
    public String handleTodo(String inputParam, boolean isNewTask, TaskList tasks, Ui ui) throws InvalidDetailException {

        String input = inputParam;
        char isDone = getIsDone(input,isNewTask);
        input = getUpdatedInput(input, isNewTask);

        Task t = interpretAndUpdateTodo(new ToDo("*"), input);
        tasks.addTask(t);

        String output = getTaskOutput(t, tasks, ui, isDone, isNewTask);
        return output;
    }

    private ToDo interpretAndUpdateTodo(ToDo task, String input) throws InvalidDetailException {

        if (5 >= input.length()) {
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        }

        String desc = input.substring(5).strip();
        if (desc.length() == 0) {
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        }

        if (!desc.equals("*")) {
            task.setName(desc);
        }

        return task;
    }

    public String handleFind(String[] parts, TaskList tasks, Ui ui) throws  InvalidDetailException {
        if(parts.length != 2){
            throw new InvalidDetailException("Invalid detail after keyword. Please retry");
        }

        String output = "";
        assert parts[1] != null : "search term cannot be null";
        output = ui.printList(tasks.findSublist(parts[1]), "Here are the matching tasks in your list:");
        return output;

    }

    /**
     * Handles the printing of all the tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to print
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public String handleList(String[] parts, TaskList tasks, Ui ui) throws InvalidDetailException{
        if(parts.length != 1){
            throw new InvalidDetailException("Invalid detail after keyword. Please retry");
        }

        String output = "";
        output = ui.printList(tasks, "Here are the tasks in your list:");

        return output;
    }

    /**
     * Handles the deleting of the tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to delete from
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public String handleDelete(String[] parts, TaskList tasks, Ui ui) throws InvalidDetailException {
        String output = "";
        if (parts.length != 2) {
            throw new InvalidDetailException("Invalid detail after delete. Please retry");
        }

        try {
            assert parts[1] != null : "delete index cannot be null";
            int num = Integer.parseInt(parts[1]);
            Task t = tasks.getItem(num - 1);
            tasks.delete(num - 1);
            output = ui.printTaskDelete(t, tasks.getListSize());
        } catch (Exception e) {
            throw new InvalidDetailException("Invalid detail after delete. Please retry");
        }

        return output;
    }

    /**
     * Handles the marking of the tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to mark from
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public String handleMark(String[] parts, TaskList tasks, Ui ui, boolean isDone) throws InvalidDetailException {

        if (parts.length != 2) {
            throw new InvalidDetailException("Invalid detail after mark. Please retry");
        }

        String output = "";
        try {
            assert parts[1] != null : "mark index cannot be null";
            int idx = Integer.parseInt(parts[1]);
            tasks.mark(idx - 1, isDone);
            output = ui.printTaskMark(tasks.getItem(idx - 1), isDone);
        } catch (Exception e) {
            throw new InvalidDetailException("Invalid detail after mark. Please retry");
        }

        return output;
    }

    /**
     * Handles update of tasks. To update task name use the '/name' tag
     *
     * update 1 * /from * /to hello
     *
     * @param parts
     * @param tasks
     * @param ui
     * @return
     * @throws InvalidDetailException
     */
    public String handleUpdate(String[] parts, TaskList tasks, Ui ui, Parser parser) throws InvalidDetailException {
        if (parts.length < 3) {
            throw new InvalidDetailException("Invalid detail after update. Please retry");
        }

        int taskIdx = Integer.parseInt(parts[1]);
        Task task = tasks.getItem(taskIdx - 1);
        String input = partsToString(parts, 2, parts.length);

        if (task instanceof ToDo) {
            ToDo temp = (ToDo) task;
            interpretAndUpdateTodo(temp, "todo " + input);
        }

        if (task instanceof Event) {
            Event temp = (Event) task;
            interpretAndUpdateEvent(temp, "event " + input);
        }

        if (task instanceof Deadline) {
            Deadline temp = (Deadline) task;
            interpretAndUpdateDeadline(temp, "deadline " + input, parser);
        }

        String output = ui.printTaskUpdate(task);
        return output;
    }

    /**
     * checks if the user input is equivalent to 'bye'
     *
     * @param input user input
     * @return boolean indicating if input is 'bye' or not
     */
    public boolean checkIsBye(String input) {
        return input.toLowerCase().equals("bye");
    }

    private String getTaskOutput(Task t, TaskList tasks, Ui ui, char isDone, boolean isNewTask) {
        String output = "";
        if (isNewTask) {
            output = ui.printTaskAdd(t, tasks.getListSize());
        } else {
            t.setDone(isDone == '1' ? true : false);
        }

        return output;
    }

    private char getIsDone(String input, boolean isNewTask) {
        char isDone = '0';
        if (!isNewTask) {
            isDone = input.charAt(input.length() - 1);
        }
        assert isDone == '0' || isDone == '1' : "isDone must be '0' or '1'";
        return isDone;
    }

    private String getUpdatedInput(String input, boolean isNewTask) {
        assert input != "" : "input cannot be empty";
        return isNewTask ? input : input.substring(0, input.length() - 1);
    }

    private String partsToString(String[] parts, int start, int end) {
        String temp = "";

        for (int i = start; i < end; i++) {
            temp = temp + " " + parts[i];
        }

        return temp;
    }
}
