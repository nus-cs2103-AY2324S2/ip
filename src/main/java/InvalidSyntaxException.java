public class InvalidSyntaxException extends Throwable {
    private String command;
    private String syntax;

    public InvalidSyntaxException(String command) {
        this.command = command;
        switch (command) {
            case "mark": syntax = "mark [task number]";
            break;
            case "unmark": syntax = "unmark [task number]";
            break;
            case "todo": syntax = "todo [task description]";
            break;
            case "deadline": syntax = "deadline [task description] /by [deadline]";
            break;
            case "event": syntax = "event [task description] /from [start date] /to [end date]";
            break;
            case "delete": syntax = "delete [task number]";
        }
    }
    @Override
    public String toString() {
        return "\nSorry, the format for the command is invalid. The syntax for "
                + command + " is: \n" + syntax;
    }
}
