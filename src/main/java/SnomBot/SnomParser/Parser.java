package SnomBot.SnomParser;

import SnomBot.InputCommands.Command;
import SnomBot.SnomExceptions.InvalidCommandException;
import SnomBot.SnomStorage.TaskStorage;
import SnomBot.SnomTask.Deadline;
import SnomBot.SnomTask.Event;
import SnomBot.SnomTask.Todo;
import SnomBot.SnomTaskList.TaskList;

public class Parser {

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

    private void addTodo(TaskList lst, TaskStorage storage, String cmd) {
        lst.AddTask(new Todo(cmd));
    }

    private void addDeadline(TaskList lst, TaskStorage storage, String cmd) {
        String name = cmd.split("/", 2)[0];
        String due_date = cmd.split("/", 2)[1];
        lst.AddTask(new Deadline(name, due_date));
    }

    private void addEvent(TaskList lst, TaskStorage storage, String cmd) {
        String name = cmd.split("/", 3)[0];
        String start = cmd.split("/", 3)[1];
        String end = cmd.split("/", 3)[2];
        lst.AddTask(new Event(name, start, end));
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

    private void exit(TaskList lst) {
        return;
    }





}
