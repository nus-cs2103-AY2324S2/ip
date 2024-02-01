package Yapchit;

import Yapchit.Tasks.Deadline;
import Yapchit.Tasks.Event;
import Yapchit.Tasks.Task;
import Yapchit.Tasks.ToDo;
import Yapchit.YapchitExceptions.InvalidDetailException;
import Yapchit.YapchitExceptions.InvalidKeywordException;
import Yapchit.YapchitExceptions.YapchitException;

import java.time.LocalDate;

/**
 * Class that acts as the 'brains' of the Yapchit program and handles all operations.
 */
public class Handler {

    /**
     * creates new handler instance
     */
    public Handler(){}

    /**
     * Primary function is to accept an input and operation and redirect it to appropriate handler function
     * for handling.
     *
     * @param input user input
     * @param op parsed operation from parser
     * @param tasks the full tasklist
     * @param ui the ui to interact with user
     * @param parser the parser to perform additional parsing
     * @throws YapchitException if there is conflict in terms of user input and operation to perform
     */
    public void handleOperation(
            String input,
            Yapchit.Operations op,
            TaskList tasks,
            Ui ui,
            Parser parser) throws YapchitException {
        String[] parts = parser.parseInputParts(input);

        switch(op){
            case LIST:
                handleList(parts, tasks, ui);
                break;

            case MARK:
                handleMark(parts, tasks, ui);
                break;

            case UNMARK:
                handleUnmark(parts, tasks, ui);
                break;

            case DELETE:
                handleDelete(parts, tasks, ui);
                break;

            case FIND:
                handleFind(parts, tasks, ui);
                break;

            case DEADLINE:
                handleDeadline(input, true, tasks, ui, parser);
                break;

            case EVENT:
                handleEvent(input, true, tasks, ui);
                break;

            case TODO:
                handleTodo(input, true, tasks, ui);
                break;

            default:
                throw new InvalidKeywordException("You have entered an invalid keyword. " +
                        "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', 'event', 'bye', 'list', 'delete']");
        }
    }

    /**
     * Primary function is to accept an input and operation and redirect it to appropriate handler function
     * for handling. Only this time the input is from the storage file and not the user.
     *
     * @param input file stored input
     * @param op parsed operation from parser
     * @param tasks the full tasklist
     * @param ui the ui to interact with user
     * @param parser the parser to perform additional parsing
     * @throws YapchitException if there is conflict in terms of input and operation to perform
     */
    public void handleUpdateListFromFile(String input, Yapchit.Operations op, TaskList tasks, Ui ui, Parser parser) throws InvalidDetailException{
        switch (op) {
            case EVENT:
                this.handleEvent(input, false, tasks, ui);
                break;
            case DEADLINE:
                this.handleDeadline(input, false, tasks, ui, parser);
                break;
            case TODO:
                this.handleTodo(input, false, tasks, ui);
                break;
        }
    }

    /**
     * Creates a new event object based on details in provided input.
     *
     * @param input containing details of the event
     * @param newTask boolean which identifies if this is a task being added to list for the first time
     * @param tasks list of tasks
     * @param ui ui object to interact with the user
     * @throws InvalidDetailException in case of mismatch in input and task detail requirements.
     */
    public void handleEvent(String input, boolean newTask, TaskList tasks, Ui ui) throws InvalidDetailException {
        int fromStart = input.indexOf("/from");
        int toStart = input.indexOf("/to");

        char done = '0';

        if(!newTask){
            done = input.charAt(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

        if(fromStart == -1 || toStart == -1 || fromStart >= toStart){
            throw new InvalidDetailException("invalid /from and /to parameters. Please retry");
        } else {
            if(6 == fromStart || fromStart + 6 == toStart || toStart + 4 >= input.length()){
                throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
            }
            String desc = input.substring(6, fromStart).strip();
            String from = input.substring(fromStart + 6, toStart).strip();
            String to = input.substring(toStart + 4).strip();

            if(desc.length() == 0 || from.length() == 0 || to.length() == 0){
                throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
            } else {
                Task t = new Event(desc, from, to);
                tasks.addTask(t);
                if(newTask){
                    ui.printTaskAdd(t, tasks.getListSize());
                } else {
                    t.updateTag(done == '1' ? true : false);
                }
            }
        }
    }

    /**
     * Creates a new deadline object based on details in provided input.
     *
     * @param input containing details of the deadline
     * @param newTask boolean which identifies if this is a task being added to list for the first time
     * @param tasks list of tasks
     * @param ui ui object to interact with the user
     * @param parser parser object to parse input
     * @throws InvalidDetailException in case of mismatch in input and task detail requirements.
     */
    public void handleDeadline(String input, boolean newTask, TaskList tasks, Ui ui, Parser parser) throws InvalidDetailException{

        int byStart = input.indexOf("/by");

        char done = '0';

        if(!newTask){
            done = input.charAt(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

        if(byStart == -1){
            throw new InvalidDetailException("Missing 'by' parameter in deadline detail");
        } else {
            if(9 == byStart || byStart + 4 >= input.length()){
                throw new InvalidDetailException("Deadline description and/or by parameter cannot be empty");
            }
            String desc = input.substring(9, byStart).strip();
            LocalDate by = parser.parseTimestamp(input.substring(byStart + 4).strip());

            if(desc.length() == 0 || by == null){
                throw new InvalidDetailException("Invalid or empty deadline description and/or by parameter");
            } else {
                Task t = new Deadline(desc, by);
                tasks.addTask(t);
                if(newTask){
                    ui.printTaskAdd(t, tasks.getListSize());
                } else {
                    t.updateTag(done == '1' ? true : false);
                }
            }
        }
    }

    /**
     * Creates a new todo object based on details in provided input.
     *
     * @param input containing details of the todo
     * @param newTask boolean which identifies if this is a task being added to list for the first time
     * @param tasks list of tasks
     * @param ui ui object to interact with the user
     * @throws InvalidDetailException in case of mismatch in input and task detail requirements.
     */
    public void handleTodo(String input, boolean newTask, TaskList tasks, Ui ui) throws  InvalidDetailException{

        char done = '0';

        if(!newTask){
            done = input.charAt(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

        if(5 >= input.length()){
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        }
        String desc = input.substring(5).strip();

        if(desc.length() == 0){
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        } else {
            Task t = new ToDo(desc);
            tasks.addTask(t);

            if(newTask){
                ui.printTaskAdd(t, tasks.getListSize());
            } else {
                t.updateTag(done == '1' ? true : false);
            }
        }
    }

    public void handleFind(String[] parts, TaskList tasks, Ui ui) throws  InvalidDetailException {
        if(parts.length != 2){
            throw new InvalidDetailException("Invalid detail after keyword. Please retry");
        } else {
            ui.printList(tasks.findSublist(parts[1]), "Here are the matching tasks in your list:");
        }
    }

    /**
     * Handles the printing of all the tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to print
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public void handleList(String[] parts, TaskList tasks, Ui ui) throws InvalidDetailException{
            if(parts.length != 1){
                throw new InvalidDetailException("Invalid detail after keyword. Please retry");
            } else {
                ui.printList(tasks, "Here are the tasks in your list:");
            }
    }

    /**
     * Handles the deleting of the tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to delete from
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public void handleDelete(String[] parts, TaskList tasks, Ui ui) throws InvalidDetailException{
        if(parts.length != 2){
            throw new InvalidDetailException("Invalid detail after delete. Please retry");
        } else {
            try {
                int num = Integer.parseInt(parts[1]);
                Task t = tasks.getItem(num - 1);
                tasks.delete(num - 1);
                ui.printTaskDelete(t, tasks.getListSize());
            } catch (Exception e){
                throw new InvalidDetailException("Invalid detail after delete. Please retry");
            }
        }
    }

    /**
     * Handles the marking of the tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to mark from
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public void handleMark(String[] parts, TaskList tasks, Ui ui) throws InvalidDetailException{
        if(parts.length != 2){
            throw new InvalidDetailException("Invalid detail after mark. Please retry");
        } else {
            try {
                int idx = Integer.parseInt(parts[1]);
                tasks.mark(idx - 1, true);
                ui.printTaskMark(tasks.getItem(idx - 1), true);
            } catch (Exception e){
                throw new InvalidDetailException("Invalid detail after mark. Please retry");
            }
        }
    }

    /**
     * Handles the unmarking of tasks in the tasks list.
     *
     * @param parts The user input split into parts
     * @param tasks the list of tasks to unmark from
     * @param ui the ui object to interact with the user
     * @throws InvalidDetailException if the input does not provide the necessary details
     */
    public void handleUnmark(String[] parts, TaskList tasks, Ui ui) throws InvalidDetailException{
        if(parts.length != 2){
            throw new InvalidDetailException("Invalid detail after unmark. Please retry");
        } else {
            try {
                int idx = Integer.parseInt(parts[1]);
                tasks.mark(idx - 1, false);
                ui.printTaskMark(tasks.getItem(idx - 1), false);
            } catch (Exception e){
                throw new InvalidDetailException("Invalid detail after unmark. Please retry");
            }
        }
    }

    /**
     * checks if the user input is equivalent to 'bye'
     *
     * @param input user input
     * @return boolean indicating if input is 'bye' or not
     */
    public boolean checkIsBye(String input){
        return input.toLowerCase().equals("bye");
    }
}
