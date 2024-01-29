import java.util.ArrayList;
import java.util.Arrays;

public class DukeException extends Exception{
    public DukeException(String msg) {
        super(msg);
    }
    static void validateToDo(String str) throws DukeException {
        if (str.split(" ").length < 2) {
            throw new DukeException("ToDo Task Missing!");
        }
    }

    static void validateInstn(String str) throws DukeException {
        String[] instnArr = {"todo", "deadline", "event", "mark", "unmark", "list", "delete"};
        String instn = str.split(" ")[0].toLowerCase();
        if (!Arrays.asList(instnArr).contains(instn)) {
            throw new DukeException("Invalid instruction for PeWPeWPeW:(((");
        }
    }

    static void validateArrIndex(int index, ArrayList<Task> task_arr) throws DukeException {
        if (index >= task_arr.size()) {
            throw new DukeException("Your task number input is invalid, please try again");
        } else if (task_arr.get(index) == null) {
            throw new DukeException("Your task number input is invalid, please try again");
        }
    }
}
