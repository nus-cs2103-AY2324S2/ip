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
 * Creates objects that implements the Command interface
 */
public class CommandFactory {

    public static Command createEnd() {
        return new CommandEnd();
    }

    public static Command createList() {
        return new CommandList();
    }

    public static Command createMark(Integer taskIdx) {
        return new CommandMark(taskIdx);
    }

    public static Command createUnmark(Integer taskIdx) {
        return new CommandUnmark(taskIdx);
    }

    public static Command createDelete(Integer taskIdx) {
        return new CommandDelete(taskIdx);
    }

    public static Command createTodo(String taskDesc) {
        return new CommandTodo(taskDesc);
    }

    public static Command createFind(String query) {
        return new CommandFind(query);
    }

    public static Command createDeadline(String taskDesc, String deadline) {
        return new CommandDeadline(taskDesc, deadline);
    }

    public static Command createEvent(String taskDesc, String fromTime, String toTime) {
        return new CommandEvent(taskDesc, fromTime, toTime);
    }

    public static Command createFixedDuration(String taskDesc, String duration) {
        return new CommandFixedDuration(taskDesc, duration);
    }
}
