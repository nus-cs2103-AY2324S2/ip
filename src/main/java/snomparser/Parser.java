package snomparser;


import inputcommands.Command;
import snomexceptions.*;
import snomstorage.TaskStorage;
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
     * @param lst is the TaskList storing
     *            the tasks entered by the user.
     * @param storage is the file management system
     *                for tasks entered by the user.
     * @return a String value for the resulting output
     *                of the command.
     */

    public String processCommand(Command command, TaskList lst, TaskStorage storage) {
        if (lst.isEmpty()) {
            storage.readTasks(lst);
        }
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
            storage.saveTasks(lst);
            return result;

        } catch (InvalidCommandException e) {
            return e.getMessage();
        }


    }


    private String addTodoToTaskList(TaskList lst, String cmd) throws InvalidCommandDuplicateTaskException {
        Todo todo = new Todo(cmd);
        if (lst.checkDuplicateTask(todo)) {
            throw new InvalidCommandDuplicateTaskException();
        } else {
            return lst.addTask(todo);
        }
    }

    private String addDeadlineToTaskList(TaskList lst, String cmd) throws InvalidCommandDuplicateTaskException {
        String name = cmd.split("/", DEADLINE_TASK_LENGTH)[0];
        String due_date = cmd.split("/", DEADLINE_TASK_LENGTH)[1];
        Deadline deadline = new Deadline(name, due_date);
        if (lst.checkDuplicateTask(deadline)) {
            throw new InvalidCommandDuplicateTaskException();
        } else {
            return lst.addTask(deadline);
        }

    }

    private String addEventToTaskList(TaskList lst, String cmd) throws InvalidCommandDuplicateTaskException {
        String name = cmd.split("/", EVENT_TASK_LENGTH)[0];
        String start = cmd.split("/", EVENT_TASK_LENGTH)[1];
        String end = cmd.split("/", EVENT_TASK_LENGTH)[2];
        Event event = new Event(name, start, end);
        if (lst.checkDuplicateTask(event)) {
            throw new InvalidCommandDuplicateTaskException();
        } else {
            return lst.addTask(event);
        }

    }

    private String doTaskInTaskList(TaskList lst, int pos) throws InvalidCommandException {
        try {
            return lst.markTaskAtIndex(pos);
        } catch (InvalidCommandTaskDoneException e) {
            throw e;
        } catch (InvalidCommandIndexException e) {
            throw e;
        }
    }

    private String undoTaskInTaskList(TaskList lst, int pos) throws InvalidCommandException {
        try {
            return lst.unmarkTaskAtIndex(pos);
        } catch (InvalidCommandTaskNotDoneException e) {
            throw e;
        } catch (InvalidCommandIndexException e) {
            throw e;
        }
    }

    private String deleteTaskFromTaskList(TaskList lst, int pos) throws InvalidCommandIndexException {
        try {
            return lst.deleteTaskAtIndex(pos);
        } catch (InvalidCommandException e) {
            throw e;
        }
    }

    private String listTaskInTaskList(TaskList lst) {
        return lst.getTasks();
    }

    private String findTaskInTaskList(TaskList lst, String cmd) {
        return lst.getMatchingTasks(cmd);
    }





}
