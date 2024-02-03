package leto.parser;

import leto.tasklist.TaskList;

import java.util.function.Function;

public class ByeCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.saveTasks();
        return Results.EXIT;
    }
}
