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

    private static final int DEADLINE_TASK_LENGTH = 2;
    private static final int EVENT_TASK_LENGTH = 3;


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


        try {
            String cmd = command.execute(lst);
            assert cmd != null : "Command cannot be null";
            String result;
            switch (command.getType()) {
            case DEADLINE:
                result = this.addDeadlineToTaskList(lst, cmd);
                break;
            case EVENT:
                result = this.addEventToTaskList(lst, cmd);
                break;
            case TODO:
                result = this.addTodoToTaskList(lst, cmd);
                break;
            case MARK:
                result = this.doTaskInTaskList(lst, Integer.parseInt(cmd));
                break;
            case UNMARK:
                result = this.undoTaskInTaskList(lst, Integer.parseInt(cmd));
                break;
            case DELETE:
                result = this.deleteTaskFromTaskList(lst, Integer.parseInt(cmd));
                break;
            case LIST:
                result = this.listTaskInTaskList(lst);
                break;
            case FIND:
                result = this.findTaskInTaskList(lst, cmd);
                break;
            case BYE:
                result = "bye";
                break;
            default:
                result = "Invalid Task";



            }
            assert result.equals("Invalid Task") : "Invalid task added";

            storage.saveTask(lst);
            return result;

        } catch (InvalidCommandException e) {
            return e.getMessage();
        }


    }


    private String addTodoToTaskList(TaskList lst, String cmd) {
        return lst.addTask(new Todo(cmd));

    }

    private String addDeadlineToTaskList(TaskList lst, String cmd) {
        String name = cmd.split("/", DEADLINE_TASK_LENGTH)[0];

        String due_date = cmd.split("/", DEADLINE_TASK_LENGTH)[1];
        return lst.addTask(new Deadline(name, due_date));

    }

    private String addEventToTaskList(TaskList lst, String cmd) {
        String name = cmd.split("/", EVENT_TASK_LENGTH)[0];
        String start = cmd.split("/", EVENT_TASK_LENGTH)[1];
        String end = cmd.split("/", EVENT_TASK_LENGTH)[2];

        return lst.addTask(new Event(name, start, end));
    }

    private String doTaskInTaskList(TaskList lst, int pos) {
        try {
            return lst.markTaskAtIndex(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return "this should not happen1";
        }
    }

    private String undoTaskInTaskList(TaskList lst, int pos) {
        try {
            return lst.unmarkTaskAtIndex(pos);
        } catch (InvalidCommandException e) {
            return "Invalid Task";
        }
    }

    private String deleteTaskFromTaskList(TaskList lst, int pos) {
        try {
            return lst.deleteTaskAtIndex(pos);
        } catch (InvalidCommandException e) {
            return "Invalid Task";
        }
    }

    private String listTaskInTaskList(TaskList lst) {
        return lst.displayTaskList();
    }

    private String findTaskInTaskList(TaskList lst, String cmd) {
        return lst.printMatchingTasks(cmd);
    }





}
