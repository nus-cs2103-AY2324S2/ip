import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    String indent = "    ____________________________________________________________\n";
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void printList() {
        System.out.println(indent);
        System.out.println("    Here aaaaare the taaaasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString() + "");
        }
        System.out.println(indent);
    }
    public String mark(boolean isInit, int idx) throws LamballParseException {
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        Task temp = tasks.get(idx);
        temp.mark();
        if (!isInit) {
            System.out.println(indent + "    I have maaarked the task as done:\n");
            System.out.println("        " + temp.toString() + "\n" + indent);
        }
        return "1 | " + temp.command();
    }

    public String unMark(int idx) throws LamballParseException {
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        System.out.println(indent + "    I have maaarked the task as undone:\n" + indent);
        Task temp = tasks.get(idx);
        temp.unMark();
        System.out.println("        " + temp.toString() + "\n" + indent);
        return "0 | " + temp.command();
    }

    public String toDo(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your todo field is empty, baaaaka.\n" + indent);
        }
        Task temp = new ToDo(parts[1]);
        tasks.add(temp);
        if (!isInit) {
            System.out.println(indent + "    Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size()
                    + " tasks in the list.\n" + indent);
        }
        return "0 | " + temp.command();
    }
    public String deadline(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your deadline field is empty, baaaaka.\n" + indent);
        }
        String[] furtherSplit = parts[1].split(" /", 2);
        if (furtherSplit.length < 2 || !furtherSplit[1].substring(0,3).equals("by ")) {
            throw new LamballParseException(indent + "    Deadline is in the wrong formaaaaaaat, baa. :(\n    Correct fo" +
                    "rmaaat is: deadline <name> /by <time>, baa.\n" + indent);
        }
        try {
            Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
            tasks.add(temp);
            if (!isInit) {
                System.out.println(indent + "    Added Deadline:\n        " + temp.toString() + "\n    Now you have " +
                        tasks.size() + " tasks in the list.\n" + indent);
            }
            return "0 | " + temp.command();
        } catch (DateTimeParseException e) {
            throw new LamballParseException(indent + "    Date is in the wrong formaaaaaaat, baa. :(\n    Correct fo" +
                    "rmaaat is: yyyy-mm-dd (e.g 2001-01-20) \n" + indent);
        }
    }

    public String event(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your event field is empty, baaaaka.\n" + indent);
        }
        String[] furtherSplit = parts[1].split(" /", 3);
        if (furtherSplit.length < 3 || !furtherSplit[1].substring(0,5).equals("from ") ||
                !furtherSplit[2].substring(0,3).equals("to ")) {
            throw new LamballParseException(indent + "    Event is in the wrong formaaaaaaat, baa. :(\n    Correct " +
                    "formaaat is: event <name> /from <time> /to <time>, baa.\n" + indent);
        }
        try {
            Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""),
                    furtherSplit[2].replaceFirst("to ", ""));
            tasks.add(temp);
            if (!isInit) {
                System.out.println(indent + "    Added Event:\n        " + temp.toString() + "\n    Now you have "
                        + tasks.size() + " tasks in the list.\n" + indent);
            }
            return "0 | " + temp.command();
        } catch (DateTimeParseException e) {
            throw new LamballParseException(indent + "    Dates are in the wrong formaaaaaaat, baa. :(\n    Correct fo" +
                    "rmaaat is: yyyy-mm-dd (e.g 2001-01-20) \n" + indent);
        }
    }

    public void deleteFromList(String[] parts, int idx) throws LamballParseException {
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        System.out.println(indent + "    I have removed this taaask:");
        Task temp = tasks.remove(idx);
        System.out.println("        " + temp.toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
    }
}
