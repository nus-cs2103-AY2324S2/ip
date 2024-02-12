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
     * Processes the command, and returns a String output
     *            depending on the user's command.
     * @param command is the command the user enters.
     * @param lst is the tasklist storing
     *            the tasks enterd by the user.
     * @param storage is the file management system
     *                for tasks entered by the user.
     * @return a String value for the resulting output
     *                of the command.
     */

    public String processCommand(Command command, TaskList lst, TaskStorage storage) {

        boolean status = true;
        try {
            String cmd = command.execute(lst);
            assert cmd != null : "Command cannot be null";
            String result = "";
            switch (command.getType()) {
            case DEADLINE:
                result = this.addDeadline(lst, storage, cmd);
                break;
            case EVENT:
                result = this.addEvent(lst, storage, cmd);
                break;
            case TODO:
                result = this.addTodo(lst, storage, cmd);
                break;
            case MARK:
                result = this.doTask(lst, storage, Integer.parseInt(cmd));
                break;
            case UNMARK:
                result = this.undoTask(lst, storage, Integer.parseInt(cmd));
                break;
            case DELETE:
                result = this.deleteTask(lst, storage, Integer.parseInt(cmd));
                break;
            case LIST:
                result = this.listTask(lst, storage);
                break;
            case FIND:
                result = this.findTask(lst, cmd);
                break;
            case BYE:
                result = "bye";
                break;
            default:
                result = "None";



            }
            assert result.equals("None") : "Invalid task added";

            storage.saveTask(lst);
            return result;

        } catch (InvalidCommandException e) {
            return e.getMessage();
        }


    }


    private String addTodo(TaskList lst, TaskStorage storage, String cmd) {
        return lst.addTask(new Todo(cmd));

    }

    private String addDeadline(TaskList lst, TaskStorage storage, String cmd) {
        String name = cmd.split("/", 2)[0];

        String due_date = cmd.split("/", 2)[1];
        return lst.addTask(new Deadline(name, due_date));

    }

    private String addEvent(TaskList lst, TaskStorage storage, String cmd) {
        String name = cmd.split("/", 3)[0];
        String start = cmd.split("/", 3)[1];
        String end = cmd.split("/", 3)[2];

        return lst.addTask(new Event(name, start, end));
    }

    private String doTask(TaskList lst, TaskStorage storage, int pos) {
        try {
            return lst.markTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return "this should not happen1";
        }
    }

    private String undoTask(TaskList lst, TaskStorage storage, int pos) {
        try {
            return lst.unmarkTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return "this should not happen2";
        }
    }

    private String deleteTask(TaskList lst, TaskStorage storage, int pos) {
        try {
            return lst.deleteTask(pos);
        } catch (InvalidCommandException e) {

            return "this should not happen3";
        }
    }

    private String listTask(TaskList lst, TaskStorage storage) {
        return lst.displayTaskList();
    }

    private String findTask(TaskList lst, String cmd) {
        return lst.printMatchingTasks(cmd);
    }





}
