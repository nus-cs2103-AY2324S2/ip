import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Actions.Action;
import Exceptions.DukeException;

public class SearchTask {
    /**
     * No constructor needed
     */
    private SearchTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static void exec(String input, List<Action> actionList) {
        try {
            LocalDateTime searchDate = InsertTask.dateConversion(input);
            List<Action> output = new ArrayList<>();

            for (Action act : actionList) {
                char whichTask = act.toString().charAt(1);

                if (whichTask == 'D') {
                    Deadline task = (Deadline) act;
                    if (task.getBy().isEqual(searchDate)) {
                        output.add(act);
                    }

                } else if (whichTask == 'E') {
                    Event task = (Event) act;
                    if (task.getFrom().isEqual(searchDate) || task.getTo().isEqual(searchDate)) {
                        output.add(act);
                    }
                }
             }
            if (output.isEmpty()) {
                System.out.println("No event on this date");
            } else {
                for (Action act : output) {
                    System.out.println(act);
                }
            }
        } catch (DukeException err) {
            throw new DukeException(err.getMessage());
        }
    }
}
