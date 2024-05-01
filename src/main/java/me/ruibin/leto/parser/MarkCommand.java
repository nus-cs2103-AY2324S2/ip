package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Marks task as completed and returns <code>Result</code>*/
public class MarkCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return TaskList.markTaskCompleted(s);
    }
}
