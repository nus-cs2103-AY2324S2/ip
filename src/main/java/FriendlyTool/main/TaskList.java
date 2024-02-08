package FriendlyTool.main;

import FriendlyTool.command.CommandTypes;
import FriendlyTool.command.Parser;
import FriendlyTool.task.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class TaskList {

    private List<Task> myList;

    public TaskList() {
        this.myList = new ArrayList<>();
    }
    public void addTask(String s, CommandTypes ct) throws ftException {
        Task task = null;
        switch (ct) {
            case TODO:
                String todo = Parser.parseToDo(s);
                if (!todo.isEmpty()) {
                    task = new ToDo(todo, false);
                } else {
                    throw new ftException("Error: Please tell me what you have TO DO");
                }
                break;

            case DEADLINE:
                String[] parsedDL = Parser.parseDeadLine(s);
                String dt = parsedDL[0];
                String by = parsedDL[1];
                if (!dt.isEmpty() && !by.isEmpty()) {
                    try {
                        task = new Deadline(dt, false, new Date(by));
                    } catch (DateTimeParseException e) {
                        throw new ftException("Invalid date format. Please follow yyyy-mm-ddThh:mm format.");
                    }
                } else {
                    throw new ftException("Error: Please tell me your task and its deadline");
                }
                break;

            case EVENT:
                String[] parsedEvent = Parser.parseEvent(s);
                String name = parsedEvent[0];
                String from = parsedEvent[1];
                String to = parsedEvent[2];
                if (!name.isEmpty() && !from.isEmpty() && !to.isEmpty()) {
                    try {
                        task = new Event(name, false, new Date(from), new Date(to));
                    } catch (DateTimeParseException e) {
                        throw new ftException("Invalid date format. Please follow yyyy-mm-ddThh:mm format.");
                    }
                } else {
                    throw new ftException("Error: Please tell me your event and its from/to dates");
                }
                break;
            default:
                throw new ftException("Error: Invalid FriendlyTool.task.Task Type");
        }
        myList.add(task);
        UI.updateTaskMsg(task, myList.size());
    }


    public void mark(String s) throws ftException {
        int i = Parser.parseNumber(s);
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.mark();
            UI.markMsg(task);
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }

    public void unmark(String s) throws ftException {
        int i = Parser.parseNumber(s);
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.unmark();
            UI.unmarkMsg(task);
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }

    public void deleteTask(String s) throws ftException {
        int i = Parser.parseNumber(s);
        if ((0 < i) && (i <= myList.size())) {
            String task = myList.remove(i - 1).toString();
            UI.deleteMsg(task, myList.size());
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }


    public int size() {
        return myList.size();
    }

    public Task get(int i) {
        return myList.get(i);
    }

    public void add(Task task) {
        myList.add(task);
    }
}
