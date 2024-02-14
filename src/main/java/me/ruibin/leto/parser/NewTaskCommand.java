package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.Deadline;
import me.ruibin.leto.tasklist.Event;
import me.ruibin.leto.tasklist.InvalidTaskException;
import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.tasklist.Todo;

/** Read input for new task to add to list and returns <code>ResultTypes.OK</code>*/
public class NewTaskCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        Result r = Result.makeResultOk("");
        try {
            Task t = null;
            String typeOfTask = s.split(" ")[0];
            switch (typeOfTask.toLowerCase()) {
            case "event":
                t = Event.eventFromCommand(s);
                break;
            case "deadline":
                t = Deadline.deadlineFromCommand(s);
                break;
            case "todo":
                t = Todo.todoFromCmd(s);
                break;
            default:
                assert false : "Should never reach here, means parser failed";
            } // end switch for type of task
            assert t != null : "t should be a created task by now";
            r.updateMessage(TaskList.addToList(t).getMessage());
        } catch (InvalidTaskException e) {
            r.updateWithException(e.printException(), e);
        }
        return r;
    }
}
