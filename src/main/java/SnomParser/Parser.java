package SnomParser;

import InputCommands.Command;
import SnomExceptions.InvalidCommandException;
import SnomStorage.TaskStorage;
import SnomTask.Deadline;
import SnomTask.Event;
import SnomTask.Todo;
import SnomTaskList.TaskList;

/**
 * The Parser Class processes the commands entered by the user
 * and adds the tasks to TaskList, and ensures that the tasks
 * are saved into a file to be accessed by the user.
 */
public class Parser {

    /**
     * Procceses the commands entered by the user, creating
     * the necessary tasks to be added into TaskList.
     *
     * @param command is the command entered by the user.
     * @param lst is the TaskList that stores the tasks generated by the commands.
     * @param storage ensures that the tasks are saved to a .txt file.
     * @return
     */
    public boolean processCommand(Command command, TaskList lst, TaskStorage storage) {
        boolean status = true;
        try {
            String cmd = command.execute(lst);

            switch (command.getType()) {
            case DEADLINE:
                this.addDeadline(lst, cmd);
                break;
            case EVENT:
                this.addEvent(lst, cmd);
                break;
            case TODO:
                this.addTodo(lst, cmd);
                break;
            case MARK:
                this.doTask(lst, Integer.parseInt(cmd));
                break;
            case UNMARK:
                this.undoTask(lst, Integer.parseInt(cmd));
                break;
            case DELETE:
                this.deleteTask(lst, Integer.parseInt(cmd));
                break;
            case LIST:
                this.listTask(lst);
                break;
            case BYE:
                status = false;
            }
            storage.saveTask(lst);
            return status;

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return status;
        }


    }

    /**
     * Adds an instance of Todo task to the TaskList.
     * @param lst is the TaskList holding all the tasks.
     * @param cmd is the command inputted by the user.
     */
    private void addTodo(TaskList lst, String cmd) {
        lst.AddTask(new Todo(cmd));
    }

    /**
     * Adds an instance of Deadline task to the TaskList.
     * @param lst is the TaskList holding all the tasks.
     * @param cmd is the command inputted by the user.
     */
    private void addDeadline(TaskList lst, String cmd) {
        String name = cmd.split("/", 2)[0];
        String due_date = cmd.split("/", 2)[1];
        lst.AddTask(new Deadline(name, due_date));
    }

    /**
     * Adds an instance of Event task to the TaskList.
     * @param lst is the TaskList holding all the tasks.
     * @param cmd is the command inputted by the user.
     */
    private void addEvent(TaskList lst, String cmd) {
        String name = cmd.split("/", 3)[0];
        String start = cmd.split("/", 3)[1];
        String end = cmd.split("/", 3)[2];
        lst.AddTask(new Event(name, start, end));
    }

    /**
     * Marks the task's status as complete.
     * @param lst is the TaskList holding all the tasks.
     * @param pos is the index of the task within the TaskList.
     */
    private void doTask(TaskList lst, int pos) {
        try {
            lst.markTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("this should not happen1");
        }
    }

    /**
     * Marks the task's status as incomplete.
     * @param lst is the TaskList holding all the tasks.
     * @param pos is the index of the task within the TaskList.
     */
    private void undoTask(TaskList lst, int pos) {
        try {
            lst.unmarkTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("this should not happen2");
        }
    }

    /**
     * Deletes the task from the TaskList.
     * @param lst is the TaskList holding all the tasks.
     * @param pos is the index of the task within the TaskList.
     */
    private void deleteTask(TaskList lst, int pos) {
        try {
            lst.deleteTask(pos);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("this should not happen3");
        }
    }

    /**
     * Executes the command to print all the tasks
     * from the TaskList.
     * @param lst is the TaskList holding all the tasks.
     */
    private void listTask(TaskList lst) {
        lst.displayTaskList();
    }







}
