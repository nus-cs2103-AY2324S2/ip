package helper;

import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

public class TaskEditor {
    private TaskEditor() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static List<Task> listEdit(List<List<Task>> taskList, String taskType, List<Task> lst) {
        switch (taskType) {
        case "DEADLINE":
            lst = taskList.get(0);
            break;
        case "EVENT":
            lst = taskList.get(1);
            break;
        case "TODO":
            lst = taskList.get(2);
            break;
        default:
            assert false : "Execution should never reach this point!";
        }
        return lst;
    }
}
