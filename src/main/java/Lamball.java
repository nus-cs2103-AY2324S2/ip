
import java.util.ArrayList;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String welcomeMessage = "    Lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    ArrayList<Task> tasks;
    public Lamball() {
        tasks = new ArrayList<>();
    }

    public void greetingMessage() {
        System.out.println(this.welcomeMessage);
    }
    public void goodbyeMessage() {
        System.out.println(this.goodbyeMessage);
    }

    public void printList() {
        System.out.println(indent);
        System.out.println("    Here aaaaare the taaaasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString() + "");
        }
        System.out.println(indent);
    }

    public void mark(String[] parts) throws LamballParseException {
        // If not numeric
        if (!parts[1].matches("-?\\d+")) {
            throw new LamballParseException(indent + "    Invalid number, baa.\n" + indent);
        }
        int idx = Integer.valueOf(parts[1]) - 1;
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        System.out.println(indent + "    I have maaarked the task as done:\n");
        Task temp = tasks.get(idx);
        temp.mark();
        System.out.println("        " + temp.toString() + "\n" + indent);
    }

    public void unMark(String[] parts) throws LamballParseException {
        // If not numeric
        if (!parts[1].matches("-?\\d+")) {
            throw new LamballParseException(indent + "    Invalid number, baa.\n" + indent);
        }
        int idx = Integer.valueOf(parts[1]) - 1;
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        System.out.println(indent + "    I have maaarked the task as undone:\n" + indent);
        Task temp = tasks.get(idx);
        temp.unMark();
        System.out.println("        " + temp.toString() + "\n" + indent);
    }

    public void toDo(String[] parts) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your todo field is empty, baaaaka.\n" + indent);
        }
        Task temp = new ToDo(parts[1]);
        tasks.add(temp);
        System.out.println(indent + "    Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
    }
    public void deadline(String[] parts) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your deadline field is empty, baaaaka.\n" + indent);
        }
        String[] furtherSplit = parts[1].split(" /", 2);
        if (furtherSplit.length < 2 || !furtherSplit[1].substring(0,3).equals("by ")) {
            throw new LamballParseException(indent + "    Deadline is in the wrong formaaaaaaat, baa. :(\n    Correct formaaat is: deadline <name> /by <time>, baa.\n" + indent);
        }
        Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
        tasks.add(temp);
        System.out.println(indent + "    Added Deadline:\n        " + temp.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
    }

    public void event(String[] parts) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your event field is empty, baaaaka.\n" + indent);
        }
        String[] furtherSplit = parts[1].split(" /", 3);
        if (furtherSplit.length < 3 || !furtherSplit[1].substring(0,5).equals("from ") || !furtherSplit[2].substring(0,3).equals("to ")) {
            throw new LamballParseException(indent + "    Event is in the wrong formaaaaaaat, baa. :(\n    Correct formaaat is: event <name> /from <time> /to <time>, baa.\n" + indent);
        }
        Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""), furtherSplit[2].replaceFirst("to ", ""));
        tasks.add(temp);
        System.out.println(indent + "    Added Event:\n        " + temp.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
    }

    public boolean parse(String msg) throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        System.out.println("\n    Lamball");
        switch(parts[0]) {
            case "mark": {
                mark(parts);
                return true;
            }
            case "unmark": {
                unMark(parts);
                return true;
            }
            case "bye":
                goodbyeMessage();
                return false;
            case "list":
                printList();
                return true;
            case "todo": {
                toDo(parts);
                return true;
            }
            case "event": {
                event(parts);
                return true;
            }
            case "deadline": {
                deadline(parts);
                return true;
            }
            default:
                throw new LamballParseException(indent + "    Sorry, I don't understaaaaaand your commaaaaand, baa. :(\n" + indent);
        }
    }
}