public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public String handle() {
        switch (getMessage()) {
            case "bye": {
                return "Syntax of bye: bye";
            }
            case "list": {
                return "Syntax of list: list";
            }
            case "mark": {
                return "Syntax of mark: mark {index of task (integer)}\n"
                        + "E.g. mark 1";
            }
            case "unmark": {
                return "Syntax of unmark: unmark {index of task (integer)}\n"
                        + "E.g. unmark 1";
            }
            case "todo": {
                return "Syntax of todo: todo {task description}\n"
                        + "E.g. todo say hi to neighbour";
            }
            case "deadline": {
                return "Syntax of deadline: deadline {task description} /by {when}\n"
                        + "E.g. deadline breakfast /by 10am";
            }
            case "event": {
                return "Syntax of event: deadline {task description} /from {when start} /to {when end}\n"
                        + "E.g. event exam /from 5pm /to 7pm";
            }
            default: {
                return "Syntax error, unknown command.";
            }
        }

    }
}
