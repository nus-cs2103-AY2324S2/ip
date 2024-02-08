import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskList {

    private List<Task> myList;
    private UI ui;

    public TaskList() {
        this.myList = new ArrayList<>();
        this.ui = new UI();
    }
    public void addTask(StringTokenizer st, CommandTypes ct) throws ftException {
        Task task = null;
        switch (ct) {
            case TODO:
                StringBuilder sbTD = new StringBuilder();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    sbTD.append(" ").append(token);
                }
                String todo = sbTD.toString();
                if (!todo.isEmpty()) {
                    task = new ToDo(todo, false);
                } else {
                    throw new ftException("Error: Please tell me what you have TO DO");
                }
                break;

            case DEADLINE:
                StringBuilder sbDL = new StringBuilder();
                StringBuilder sbBy = new StringBuilder();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    if (token.equals("/by")) {
                        while (st.hasMoreTokens()) {
                            sbBy.append(" ").append(st.nextToken());
                        }
                        break;
                    } else {
                        sbDL.append(" ").append(token);
                    }
                }
                String dt = sbDL.toString();
                String by = sbBy.toString();
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
                StringBuilder sb = new StringBuilder();
                StringBuilder sbFrom = new StringBuilder();
                StringBuilder sbTo = new StringBuilder();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    if (token.equals("/from")) {
                        while (st.hasMoreTokens()) {
                            String curr = st.nextToken().trim();
                            if (curr.equals("/to")) {
                                while (st.hasMoreTokens()) {
                                    sbTo.append(" ").append(st.nextToken());
                                }
                            } else {
                                sbFrom.append(" ").append(curr);
                            }
                        }
                    } else {
                        sb.append(" ").append(token);
                    }
                }
                String name = sb.toString();
                String from = sbFrom.toString();
                String to = sbTo.toString();
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
                throw new ftException("Error: Invalid Task Type");
        }
        myList.add(task);
        UI.updateTaskMsg(task, myList.size());
    }


    public void mark(StringTokenizer st) throws ftException {
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        int i = Integer.parseInt(st.nextToken());
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.mark();
            UI.markMsg(task);
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }

    public void unmark(StringTokenizer st) throws ftException {
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        int i = Integer.parseInt(st.nextToken());
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.unmark();
            UI.unmarkMsg(task);
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }

    public void deleteTask(StringTokenizer st) throws ftException {
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        int i = Integer.parseInt(st.nextToken());
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
