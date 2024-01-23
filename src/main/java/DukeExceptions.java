import java.util.ArrayList;

public class DukeExceptions extends Exception {
    public DukeExceptions(String msg) {
        super(msg);
    }

    public static void checkListNotEmpty (ArrayList<Task> lst) throws DukeExceptions {
        if (lst.size() == 0) {
            throw new DukeExceptions("OOPS!!! The list is empty. There is nothing to delete.");
        }
    }

    public static void validateInput(String action, String parameters) throws DukeExceptions{
        switch(action) {
            case "todo":
                if (parameters.equals(" ")) {
                    throw new DukeExceptions("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                if (parameters.equals(" ")) {
                    throw new DukeExceptions("OOPS!!! The description of a deadline cannot be empty.");
                }

                String[] deadlineInputs = parameters.split("/by ");
                String dueDate;
                if (deadlineInputs.length != 2) {
                    throw new DukeExceptions("OOPS!!! The due date of a deadline cannot be empty.");
                }
                break;
            case "event":
                if (parameters.equals(" ")) {
                    throw new DukeExceptions("OOPS!!! The description of an event cannot be empty.");
                }

                String[] eventInputs = parameters.split("/from ", 2);
                if (eventInputs.length == 2) {
                    String[] splitFromAndTo = eventInputs[1].split("/to ");
                    if (splitFromAndTo.length != 2) {
                        throw new DukeExceptions("OOPS!!! Please include when does the event start and ends.");
                    }
                } else {
                    throw new DukeExceptions("OOPS!!! The period of an event cannot be empty.");
                }
                break;
            case "mark":
                if (parameters.equals(" ")) {
                    throw new DukeExceptions("Please include the index to mark.");
                }
                break;
            case "unmark":
                if (parameters.equals(" ")) {
                    throw new DukeExceptions("Please include the index to unmark.");
                }
                break;
            case "list":
                if (!parameters.equals(" ")) {
                    throw new DukeExceptions("OOPS!!! You have included extra information, which I cannot read");
                }
                break;
            case "bye":
                if (!parameters.equals(" ")) {
                    throw new DukeExceptions("OOPS!!! You have included extra information, which I cannot read");
                }
                break;
            case "delete":
                if (parameters.equals(" ")) {
                    throw new DukeExceptions("OOPS!!! You have to include which number to delete.");
                }
                break;
            default:
                throw new DukeExceptions("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
