package anna;

import java.util.function.Consumer;

class CommandEnd implements Command {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        reply.accept(BYE_MESSAGE);
        return false;
    }

    public String toString() {
        return String.format("%s:[]", this.getClass().getSimpleName());
    }

}

class CommandList implements Command {

    private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        reply.accept(LIST_MESSAGE);
        for (int idx = 0; idx < tasks.numberOfTask(); idx++) {
            reply.accept(String.format("  %d.%s", idx + 1, tasks.peekTask(idx)));
        }
        return true;
    }

    public String toString() {
        return String.format("%s:[]", this.getClass().getSimpleName());
    }
}

class CommandMark implements Command {

    private static final String MARK_MESSAGE =
        "Nice! I've marked this task as done:";
    private Integer taskIdx;

    public CommandMark(Integer taskIdx) {
        this.taskIdx = taskIdx;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        String ferr2 = "%s command: no such task numbered %d.";
        if (!tasks.checkTaskIdx(taskIdx)) {
            throw new AnnaException(
                String.format(ferr2, "mark", taskIdx)
            );
        }
        tasks.setDone(taskIdx, true);
        reply.accept(MARK_MESSAGE);
        reply.accept(String.format("  %s", tasks.peekTask(taskIdx)));
        return true;
    }

    public String toString() {
        return String.format("%s:[%d]", this.getClass().getSimpleName(), taskIdx);
    }
}

class CommandUnmark implements Command {

    private static final String UNMARK_MESSAGE =
        "OK, I've marked this task as not done yet:";
    private Integer taskIdx;

    public CommandUnmark(Integer taskIdx) {
        this.taskIdx = taskIdx;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        String ferr2 = "%s command: no such task numbered %d.";
        if (!tasks.checkTaskIdx(taskIdx)) {
            throw new AnnaException(
                String.format(ferr2, "unmark", taskIdx)
            );
        }
        tasks.setDone(taskIdx, false);
        reply.accept(UNMARK_MESSAGE);
        reply.accept(String.format("  %s", tasks.peekTask(taskIdx)));
        return true;
    }

    public String toString() {
        return String.format("%s:[%d]", this.getClass().getSimpleName(), taskIdx);
    }
}

class CommandDelete implements Command {

    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String TASKS_SUMMARY_MESSAGE =
        "Now you have %s tasks in the list.";
    private Integer taskIdx;

    public CommandDelete(Integer taskIdx) {
        this.taskIdx = taskIdx;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        String ferr2 = "%s command: no such task numbered %d.";
        if (!tasks.checkTaskIdx(taskIdx)) {
            throw new AnnaException(
                String.format(ferr2, "delete", taskIdx)
            );
        }
        Task t = tasks.popTask(taskIdx);
        reply.accept(DELETE_MESSAGE);
        reply.accept(String.format("  %s", t));
        reply.accept(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
        return true;
    }

    public String toString() {
        return String.format("%s:[%d]", this.getClass().getSimpleName(), taskIdx);
    }
}

class CommandTodo implements Command {

    private static final String TASKS_SUMMARY_MESSAGE =
        "Now you have %s tasks in the list.";
    private static final String TODO_MESSAGE = "Got it. I've added this task:";
    private String taskDesc;

    public CommandTodo(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        Task task = TaskFactory.createTodo(taskDesc, false);
        tasks.addTask(task);
        reply.accept(TODO_MESSAGE);
        reply.accept(String.format("  %s", task));
        reply.accept(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
        return true;
    }

    public String toString() {
        return String.format("%s:[%s]", this.getClass().getSimpleName(), taskDesc);
    }
}

class CommandFind implements Command {

    private static final String FIND_MESSAGE =
        "Here are the matching tasks in your list:";
    private String query;

    public CommandFind(String query) {
        this.query = query;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        reply.accept(FIND_MESSAGE);
        int[] count = { 1 };
        tasks
            .getStoredTasks()
            .stream()
            .filter(t -> t.toString().contains(query))
            .forEach(t -> reply.accept(String.format("  %d.%s", count[0]++, t)));
        return true;
    }

    public String toString() {
        return String.format("%s:[%s]", this.getClass().getSimpleName(), query);
    }
}

class CommandDeadline implements Command {

    private static final String TASKS_SUMMARY_MESSAGE =
        "Now you have %s tasks in the list.";
    private static final String TODO_MESSAGE = "Got it. I've added this task:";
    private String taskDesc;
    private String deadline;

    public CommandDeadline(String taskDesc, String deadline) {
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        Task task = TaskFactory.createDeadline(taskDesc, deadline, false);
        tasks.addTask(task);
        reply.accept(TODO_MESSAGE);
        reply.accept(String.format("  %s", task));
        reply.accept(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
        return true;
    }

    public String toString() {
        return String.format("%s:[%s | %s]",
            this.getClass().getSimpleName(), taskDesc, deadline);
    }
}

class CommandEvent implements Command {

    private static final String TASKS_SUMMARY_MESSAGE =
        "Now you have %s tasks in the list.";
    private static final String TODO_MESSAGE = "Got it. I've added this task:";
    private String taskDesc;
    private String fromTime;
    private String toTime;

    public CommandEvent(String taskDesc, String fromTime, String toTime) {
        this.taskDesc = taskDesc;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        Task task = TaskFactory.createEvent(taskDesc, fromTime, toTime, false);
        tasks.addTask(task);
        reply.accept(TODO_MESSAGE);
        reply.accept(String.format("  %s", task));
        reply.accept(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
        return true;
    }

    public String toString() {
        return String.format("%s:[%s | %s | %s]",
            this.getClass().getSimpleName(), taskDesc, fromTime, toTime);
    }
}

class CommandFixedDuration implements Command {

    private static final String TASKS_SUMMARY_MESSAGE =
        "Now you have %s tasks in the list.";
    private static final String TODO_MESSAGE = "Got it. I've added this task:";
    private String taskDesc;
    private String duration;

    public CommandFixedDuration(String taskDesc, String duration) {
        this.taskDesc = taskDesc;
        this.duration = duration;
    }

    public boolean execute(Consumer<String> reply, TaskList tasks) throws AnnaException {
        Task task = TaskFactory.createFixedDuration(taskDesc, duration, false);
        tasks.addTask(task);
        reply.accept(TODO_MESSAGE);
        reply.accept(String.format("  %s", task));
        reply.accept(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
        return true;
    }

    public String toString() {
        return String.format("%s:[%s | %s]",
            this.getClass().getSimpleName(), taskDesc, duration);
    }
}

/**
 * The CommandFactory class provides static methods
 * to create various command objects.
 */
public class CommandFactory {

    /**
     * Creates a CommandEnd object.
     * CommandEnd signals the termination
     * of a session.
     *
     * @return a CommandEnd object
     */
    public static Command createEnd() {
        return new CommandEnd();
    }

    /**
     * Creates a CommandList object.
     * CommandList lists the tasks.
     *
     * @return a CommandList object
     */
    public static Command createList() {
        return new CommandList();
    }

    /**
     * Creates a CommandMark object with the specified task index.
     * CommandMark marks a task as done.
     *
     * @param taskIdx the index of the task to mark
     * @return a CommandMark object with the specified task index
     */
    public static Command createMark(Integer taskIdx) {
        return new CommandMark(taskIdx);
    }

    /**
     * Creates a CommandUnmark object with the specified task index.
     * CommandUnmark marks a task as not done.
     *
     * @param taskIdx the index of the task to unmark
     * @return a CommandUnmark object with the specified task index
     */
    public static Command createUnmark(Integer taskIdx) {
        return new CommandUnmark(taskIdx);
    }

    /**
     * Creates a CommandDelete object with the specified task index.
     * CommandDelete deletes a task.
     *
     * @param taskIdx the index of the task to delete
     * @return a CommandDelete object with the specified task index
     */
    public static Command createDelete(Integer taskIdx) {
        return new CommandDelete(taskIdx);
    }

    /**
     * Creates a CommandTodo object with the specified task description.
     * CommandTodo creates a todo task.
     *
     * @param taskDesc the description of the todo task
     * @return a CommandTodo object with the specified task description
     */
    public static Command createTodo(String taskDesc) {
        return new CommandTodo(taskDesc);
    }

    /**
     * Creates a CommandFind object with the specified query.
     * CommandFind finds the task satisfying the query.
     *
     * @param query the query to search for tasks
     * @return a CommandFind object with the specified query
     */
    public static Command createFind(String query) {
        return new CommandFind(query);
    }

    /**
     * Creates a CommandDeadline object with the specified task description and deadline.
     * CommandDeadline creates a deadline task.
     *
     * @param taskDesc the description of the deadline task
     * @param deadline the deadline of the task
     * @return a CommandDeadline object with the specified task description and deadline
     */
    public static Command createDeadline(String taskDesc, String deadline) {
        return new CommandDeadline(taskDesc, deadline);
    }

    /**
     * Creates a CommandEvent object with the specified task description, start time, and end time.
     * CommandEvent creates and event task.
     *
     * @param taskDesc the description of the event task
     * @param fromTime the start time of the event
     * @param toTime the end time of the event
     * @return a CommandEvent object with the specified task description, start time, and end time
     */
    public static Command createEvent(String taskDesc, String fromTime, String toTime) {
        return new CommandEvent(taskDesc, fromTime, toTime);
    }

    /**
     * Creates a CommandFixedDuration object with the specified task description and duration.
     * CommandFixedDuration creates a fixed-duration task.
     *
     * @param taskDesc the description of the fixed duration task
     * @param duration the duration of the task
     * @return a CommandFixedDuration object with the specified task description and duration
     */
    public static Command createFixedDuration(String taskDesc, String duration) {
        return new CommandFixedDuration(taskDesc, duration);
    }
}
