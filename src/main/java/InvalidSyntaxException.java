public class InvalidSyntaxException extends Throwable {
    private String command;
    private String syntax;

    public InvalidSyntaxException(String command) {
        this.command = command;
        switch (command) {
            case "mark": this.syntax = "mark [task number]";
            break;
            case "unmark": this.syntax = "unmark [task number]";
            break;
            case "todo": this.syntax = "todo [task description]";
            break;
            case "deadline": this.syntax = "deadline [task description] /by [deadline]";
            break;
            case "event": this.syntax = "event [task description] /from [start date] /to [end date]";
            break;
            case "delete": this.syntax = "delete [task number]";
        }
    }
    @Override
    public String toString() {
        return "\nSorry, the format for the command is invalid. The syntax for "
                + this.command + " is: \n" + this.syntax;
    }
}
