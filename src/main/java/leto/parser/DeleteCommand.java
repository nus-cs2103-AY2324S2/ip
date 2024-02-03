package leto.parser;

import leto.tasklist.TaskList;

import java.util.function.Function;

public class DeleteCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.deleteTask(s);
        return Results.OK;
    }
}
