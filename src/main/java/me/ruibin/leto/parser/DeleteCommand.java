package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Deletes task and returns <code>ResultTypes.OK</code>*/
public class DeleteCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return TaskList.deleteTask(s);
    }
}
