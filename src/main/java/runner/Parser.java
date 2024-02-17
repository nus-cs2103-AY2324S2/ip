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
     * Specifically handle user command.
     * @param info The information given by the user.
     * @returns A response to the user command.
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
                    return addTodo(info);
                case "deadline":
                    return addDeadline(arg);
                case "event":
                    return addEvent(arg);
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
     * @returns All tasks containing the keyword in a list.
     */
    public String find(String key) {
        TaskList ans = new TaskList();
        for (Task tk : taskList.getList()) {
            if (tk.getMSG().contains(key)) {
                ans.add(tk);
            }
        }
        return Ui.showList(ans, 0);
    }

    /**
     * Actions when marking.
     * @param s String format of the Task index.
     * @returns Mark message.
     */
    public String mark(String s) {
        try {
            int index = Integer.parseInt(s) - 1;
            assert index < taskList.size(): "Index Invalid";
            taskList.getTask(index).complete();
            storage.saveList();
            return Ui.markMSG(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }

    /**
     * Actions when unmarking.
     * @param s String format of the Task index.
     * @returns Unmark message.
     */
    public String unmark(String s) {
        try {
            int index = Integer.parseInt(s) - 1;
            assert index < taskList.size(): "Index Invalid";
            taskList.getTask(index).uncomplete();
            storage.saveList();
            return Ui.unmarkMSG(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }

    /**
     * Actions when deleting.
     * @param s String format of the Task index.
     * @returns Delete message.
     */
    public String delete(String s) {
        try {
            int index = Integer.parseInt(s) - 1;
            assert index < taskList.size() : "Index Invalid";

            Task temp = taskList.getTask(index);

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
     * @returns Add message.
     */
    public String addTodo(String info) {
        String action = info.split(" ", 2)[1];
        Task t = new Todo(action);
        taskList.add(t);
        storage.saveList();
        return Ui.addMSG(t, taskList.size());
    }

    /**
     * Actions when adding a Deadline.
     * @param s Description of a Deadline.
     * @returns Add message.
     */
    public String addDeadline(String s) {
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
     * @returns Add message.
     */
    public String addEvent(String s) {
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