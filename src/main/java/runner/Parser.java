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
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for Parser.
     * @param duke a Duke chat-bot to work on.
     */
    public Parser(Duke duke) {
        this.taskList = duke.taskList;
        this.storage = duke.storage;
    }

    /**
     * @param info The information given by the user.
     */
    public String handle(String info) {
        if (!info.contains(" ")) {
            switch (info) {
                case "hello":
                    return Ui.start();
                case "bye":
                    return Ui.ending();
                case "list":
                    return Ui.showList(taskList, 1);
                default:
                    return "Not Smart to Understand -_-";
            }
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
     * Actions when finding keywords.
     * @param key Keyword input.
     */
    public String find(String key) {
        TaskList ans = new TaskList();
        for (Task tk : taskList.get_list()) {
            if (tk.getMSG().contains(key)) {
                ans.add(tk);
            }
        }
        return Ui.showList(ans, 0);
    }

    /**
     * Actions when marking.
     * @param s String format of the Task index.
     */
    public String mark(String s) {
        try {
            int index = Integer.parseInt(s) - 1;
            assert index < taskList.size(): "Index Invalid";
            taskList.get_task(index).complete();
            storage.saveList();
            return Ui.markMSG(taskList.get_task(index));
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
            int index = Integer.parseInt(s) - 1;
            assert index < taskList.size(): "Index Invalid";
            taskList.get_task(index).uncomplete();
            storage.saveList();
            return Ui.unmarkMSG(taskList.get_task(index));
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
            assert index < taskList.size() : "Index Invalid";
            Task temp = taskList.get_task(index);
            taskList.remove(index);
            storage.saveList();
            return Ui.deleteMSG(temp, taskList.size());
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
        taskList.add(t);
        storage.saveList();
        return Ui.addMSG(t, taskList.size());
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
            taskList.add(d);
            storage.saveList();
            return Ui.addMSG(d, taskList.size());
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
        taskList.add(e);
        storage.saveList();
        return Ui.addMSG(e, taskList.size());
    }
}