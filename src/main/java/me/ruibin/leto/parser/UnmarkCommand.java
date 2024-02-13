package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Marks task as uncompleted and returns <code>Result</code>*/
public class UnmarkCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return TaskList.markTaskUncompleted(s);
    }
}
