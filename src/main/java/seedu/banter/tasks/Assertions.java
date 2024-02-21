package seedu.banter.tasks;

import java.time.LocalDateTime;

class Assertions {
    // Assertion messages
    private static final String DATETIME_NOT_IN_THE_FUTURE = "Date and time must be in the future";
    private static final String TASK_MUST_BE_UNMARKED = "Task must be unmarked";

    // Assertion methods
    static void assertDateTimeIsInTheFuture(LocalDateTime dateTime) {
        assert dateTime.isAfter(LocalDateTime.now()) : DATETIME_NOT_IN_THE_FUTURE;
    }

    static void assertTaskIsUnmarked(Task task) {
        assert !task.isDone() : TASK_MUST_BE_UNMARKED;
    }
}
