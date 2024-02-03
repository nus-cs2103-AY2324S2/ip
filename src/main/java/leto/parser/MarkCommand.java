package leto.parser;

import leto.tasklist.TaskList;

import java.util.function.Function;

public class MarkCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.markTaskCompleted(s);
        return Results.OK;
    }
}
