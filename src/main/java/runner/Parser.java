package runner;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.time.format.DateTimeParseException;

/**
 * Parser to parse the command process the necessary actions.
 */
public class Parser {
    private final Duke duke;

    /**
     * Constructor for Parser.
     * @param duke a Duke chat-bot to work on.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * @param info The information given by the user.
     */
    public String handle(String info) {
        if (info.equals("bye")) {
            return terminate();
        } else if (info.equals("list")) {
            return Ui.showList(duke.taskList);
        } else {
            return read(info);
        }
    }

    public String read(String info) {
        if (!info.contains(" ")) {
            return "Not Smart to Understand -_-";
        }
        try {
            String[] segments = info.split(" ", 2);
            String first = segments[0];
            String arg = segments[1];
            switch (first) {
                case "mark":
                    return mark(arg);
                case "unmark":
                    return unmark(arg);
                case "todo":
                    return add_todo(info);
                case "deadline":
                    return add_deadline(arg);
                case "event":
                    return add_event(arg);
                case "find":
                    return find(arg);
                case "delete":
                    return delete(arg);
                default:
                    return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! Arguments not enough.";
        }
    }

    /**
     * Terminate the Duke.
     */
    public String terminate() {
        return Ui.ending();
    }

    /**
     * Actions when finding keywords.
     * @param key Keyword input.
     */
    public String find(String key) {
        TaskList ans = new TaskList();
        for(Task tk: duke.taskList.get_list()) {
            if (tk.getMsg().contains(key)) {
                ans.add(tk);
            }
        }
        return Ui.findList(ans);
    }

    /**
     * Actions when marking.
     * @param s String format of the Task index.
     */
    public String mark(String s) {
        try {
            int n = Integer.parseInt(s) - 1;
            duke.taskList.get_task(n).complete();
            duke.store.saveList();
            return Ui.markMSG(duke.taskList.get_task(n));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }

    /**
     * Actions when unmarking.
     * @param s String format of the Task index.
     */
    public String unmark(String s) {
        try {
            int num = Integer.parseInt(s) - 1;
            duke.taskList.get_task(num).uncomplete();
            duke.store.saveList();
            return Ui.unmarkMSG(duke.taskList.get_task(num));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }

    /**
     * Actions when deleting.
     * @param s String format of the Task index.
     */
    public String delete(String s) {
        try {
            int index = Integer.parseInt(s) - 1;
            Task temp = duke.taskList.get_task(index);
            duke.taskList.remove(index);
            duke.store.saveList();
            return Ui.deleteMSG(temp, duke.taskList.size());
        } catch (IndexOutOfBoundsException a) {
            return "OOPS!!! You can not delete air~";
        } catch (NumberFormatException b) {
            return "OOPS!!! Number Format Wrong";
        }
    }

    /**
     * Actions when adding a Todo.
     * @param info Description of a Todo.
     */
    public String add_todo(String info) {
        String action = info.split(" ", 2)[1];
        Task t = new Todo(action);
        duke.taskList.add(t);
        duke.store.saveList();
        return Ui.addMSG(t, duke.taskList.size());
    }

    /**
     * Actions when adding a Deadline.
     * @param s Description of a Deadline.
     */
    public String add_deadline(String s) {
        try {
            String msg = s.split("/by ", 2)[0];
            String by = s.split("/by ", 2)[1];
            Task d = new Deadline(msg, by);
            duke.taskList.add(d);
            duke.store.saveList();
            return Ui.addMSG(d, duke.taskList.size());
        } catch (DateTimeParseException e) {
            return "Date Unacceptable (YYYY-MM-DD PLZ)";
        }
    }

    /**
     * Actions when adding an Event.
     * @param s Description of an Event.
     */
    public String add_event(String s) {
        String event = s.split("/from", 2)[0];
        String time = s.split("/from", 2)[1];
        String from = time.split("/to")[0];
        String to = time.split("/to")[1];
        Task e = new Event(event, from, to);
        duke.taskList.add(e);
        duke.store.saveList();
        return Ui.addMSG(e, duke.taskList.size());
    }
}
