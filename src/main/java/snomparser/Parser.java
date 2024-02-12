package snomparser;


import SnomStorage.TaskStorage;
import inputcommands.Command;
import snomexceptions.InvalidCommandException;
import snomtask.Deadline;
import snomtask.Event;
import snomtask.Todo;
import snomtasklist.TaskList;

/**
 * Parser implements the task processor which
 * the user inputs into SnomBot.
 */
public class Parser {

    /**
     * Processes the command, and returns a boolean value
     *            depending on the user's command.
     * @param command is the command the user enters.
     * @param lst is the tasklist storing
     *            the tasks enterd by the user.
     * @param storage is the file management system
     *                for tasks entered by the user.
     * @return a boolean value for whether the SnomBot should
     *                continue its execution.
     */
    public boolean processCommand(Command command, TaskList lst, TaskStorage storage) {
        boolean status = true;
        try {
            String cmd = command.execute(lst);

            switch (command.getType()) {
            case DEADLINE:
                this.addDeadline(lst, storage, cmd);
                break;
            case EVENT:
                this.addEvent(lst, storage, cmd);
                break;
            case TODO:
                this.addTodo(lst, storage, cmd);
                break;
            case MARK:
                this.doTask(lst, storage, Integer.parseInt(cmd));
                break;
            case UNMARK:
                this.undoTask(lst, storage, Integer.parseInt(cmd));
                break;
            case DELETE:
                this.deleteTask(lst, storage, Integer.parseInt(cmd));
                break;
            case LIST:
                this.listTask(lst, storage);
                break;
            case FIND:
                this.findTask(lst, cmd);
                break;
            case BYE:
                status = false;
                break;
            default:
                status = true;

            }

            storage.saveTask(lst);
            return status;

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return status;
        }


    }

    private void addTodo(TaskList lst, TaskStorage storage, String cmd) {
        lst.addTask(new Todo(cmd));
    }

    private void addDeadline(TaskList lst, TaskStorage storage, String cmd) {
        String name = cmd.split("/", 2)[0];
        String deadline = cmd.split("/", 2)[1];
        lst.addTask(new Deadline(name, deadline));
    }

    private void addEvent(TaskList lst, TaskStorage storage, String cmd) {
        String name = cmd.split("/", 3)[0];
        String start = cmd.split("/", 3)[1];
        String end = cmd.split("/", 3)[2];
        lst.addTask(new Event(name, start, end));
    }

    private void doTask(TaskList lst, TaskStorage storage, int pos) {
        try {
            lst.markTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("this should not happen1");
        }
    }

    private void undoTask(TaskList lst, TaskStorage storage, int pos) {
        try {
            lst.unmarkTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("this should not happen2");
        }
    }

    private void deleteTask(TaskList lst, TaskStorage storage, int pos) {
        try {
            lst.deleteTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("this should not happen3");
        }
    }

    private void listTask(TaskList lst, TaskStorage storage) {
        lst.displayTaskList();
    }

    private void findTask(TaskList lst, String cmd) {
        lst.printMatchingTasks(cmd);
    }





}
