package seedu.banter.tasks;

import java.time.LocalDateTime;

class Assertions { // Default access modifier

    // Assertion messages
    private static final String DATETIME_NOT_IN_THE_FUTURE = "Date and time must be in the future"; // Default access modifier
    private static final String TASK_MUST_BE_UNMARKED = "Task must be unmarked"; // Default access modifier

    // Assertion methods
    static void assertDateTimeIsInTheFuture(LocalDateTime dateTime) { // Default access modifier
        assert dateTime.isAfter(LocalDateTime.now()) : DATETIME_NOT_IN_THE_FUTURE;
    }

    static void assertTaskIsUnmarked(Task task) { // Default access modifier
        assert !task.isDone() : TASK_MUST_BE_UNMARKED;
    }
}
