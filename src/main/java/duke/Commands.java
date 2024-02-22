package duke;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class ClearCommand extends Command {
    public ClearCommand() {}

    @Override
    public String execute(TaskList tasks) {
        tasks.clear();
        assert tasks.getTaskCount() == 0;
        return "Cleared all tasks!";
    }
}

abstract class AddTaskCommand extends Command {
    private Task task;

    public abstract Task parseTask(String argument) throws InvalidCommandException;

    public AddTaskCommand(String argument) throws InvalidCommandException {
        argument = argument.trim();
        this.task = parseTask(argument);
        assert task != null : "Task should not be null";
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        assert task != null : "execute() can only be called once";
        int oldTaskCount = tasks.getTaskCount();
        tasks.addTask(task);
        assert oldTaskCount + 1 == tasks.getTaskCount();
        String result = "Got it. I've added this task:\n" +
            task + "\n" +
            tasks.getDisplayTaskCount();
        task = null;
        return result;
    }
}

class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String argument) throws InvalidCommandException {
        super(argument);
    }

    @Override
    public Task parseTask(String argument) throws InvalidCommandException {
        if (argument.isEmpty()) {
            throw new InvalidCommandException("The description of a todo cannot be empty.");
        }
        return new Todo(argument);
    }
}

class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand(String argument) throws InvalidCommandException {
        super(argument);
    }

    @Override
    public Task parseTask(String argument) throws InvalidCommandException {
        String[] parts = argument.split(" /by ", 2);
        if (parts.length != 2) {
            throw new InvalidCommandException("The deadline command should be in the format: deadline <description> /by <date>");
        }
        if (parts[0].isEmpty()) {
            throw new InvalidCommandException("The description of a deadline cannot be empty.");
        }
        if (parts[1].isEmpty()) {
            throw new InvalidCommandException("The date of a deadline cannot be empty.");
        }
        return new Deadline(parts[0], parts[1]);
    }
}

class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String argument) throws InvalidCommandException {
        super(argument);
    }

    private static final Pattern EVENT_PATTERN = Pattern.compile("(.*) /from (.*) /to (.*)");

    @Override
    public Task parseTask(String argument) throws InvalidCommandException {
        Matcher matcher = EVENT_PATTERN.matcher(argument);
        if (!matcher.matches()) {
            throw new InvalidCommandException("The event command should be in the format: event <description> /from <start date> /to <end date>");
        }
        String description = matcher.group(1);
        String start = matcher.group(2);
        String end = matcher.group(3);
        if (description.isEmpty()) {
            throw new InvalidCommandException("The description of an event cannot be empty.");
        }
        if (start.isEmpty()) {
            throw new InvalidCommandException("The start date of an event cannot be empty.");
        }
        if (end.isEmpty()) {
            throw new InvalidCommandException("The end date of an event cannot be empty.");
        }
        return new Event(description, start, end);
    }
}

class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) throws DukeException {
        return tasks.getDisplayListTasks();
    }
}

abstract class CommandTakingTaskIndex extends Command {
    /**
     * The 0-indexed index of the task to be operated on.
     */
    protected int index;

    /**
     * @param index 1-based index of the task.
     * @throws InvalidCommandException if the index is invalid.
     */
    public CommandTakingTaskIndex(String index) throws InvalidCommandException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The task number must be an integer.");
        }
        if (this.index <= 0) {
            throw new InvalidCommandException("The task number must be positive.");
        }
        this.index--;
    }
}

class MarkDoneCommand extends CommandTakingTaskIndex {
    public MarkDoneCommand(String index) throws InvalidCommandException {
        super(index);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.markTaskAsDone(index);
        return "Nice! I've marked this task as done:\n" +
            tasks.getTaskDescription(index);
    }
}

class UnmarkDoneCommand extends CommandTakingTaskIndex {
    public UnmarkDoneCommand(String index) throws InvalidCommandException {
        super(index);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.unmarkTaskAsDone(index);
        return "OK, I've unmarked this task as not done yet:\n" +
            tasks.getTaskDescription(index);
    }
}

class DeleteCommand extends CommandTakingTaskIndex {
    public DeleteCommand(String index) throws InvalidCommandException {
        super(index);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String taskToDelete = tasks.getTaskDescription(index);
        int oldTaskCount = tasks.getTaskCount();
        tasks.deleteTask(index);
        assert oldTaskCount - 1 == tasks.getTaskCount();
        return "Noted. I've removed this task:\n" +
            taskToDelete + "\n" +
            tasks.getDisplayTaskCount();
    }
}

class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.findTasks(query).getDisplayListTasks();
    }
}

/**
 * A helper class that registers commands with {@link Parser}.
 */
public class Commands {
    public static void registerCommands() {
        Parser.registerCommand("clear", s -> new ClearCommand());
        Parser.registerCommand("todo", s -> new AddTodoCommand(s));
        Parser.registerCommand("deadline", s -> new AddDeadlineCommand(s));
        Parser.registerCommand("event", s -> new AddEventCommand(s));
        Parser.registerCommand("list", s -> new ListCommand());
        Parser.registerCommand("bye", s -> new ByeCommand());
        Parser.registerCommand("mark", s -> new MarkDoneCommand(s));
        Parser.registerCommand("unmark", s -> new UnmarkDoneCommand(s));
        Parser.registerCommand("delete", s -> new DeleteCommand(s));
        Parser.registerCommand("find", s -> new FindCommand(s));
    }
}
