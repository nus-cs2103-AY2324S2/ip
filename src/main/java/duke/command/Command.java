package duke.command;

import java.util.List;

import duke.task.TaskList;

public abstract class Command {
    protected String command;
    protected List<String> arguments;

    public Command(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract TaskList execute(TaskList tasks);

    public String getCommand() {
        return this.command;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Command) {
            Command other = (Command) obj;
            return command.equals(other.command)
                && (arguments == null ? other.arguments == null : arguments.equals(other.arguments));
        } else if (obj instanceof String) {
            String otherString = (String) obj;
            return command.equals(otherString);
        }
        return false;
    }
}
