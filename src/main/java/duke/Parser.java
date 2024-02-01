package duke;
import command.*;

public class Parser {
    private static TaskList taskList;
    private static Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public static Command parse(String input)  {
        String temp = input.split(" ")[0];
        Actions action = Actions.valueOf(temp.toUpperCase());
        switch(action) {
        case BYE:
            return new byeCommand(taskList, ui);
        case LIST:
            return new showListCommand(taskList, ui);
        case MARK:
            return new markCommand(taskList, ui);
        case UNMARK:
            return new unmarkCommand(taskList, ui);
        case TODO:
            return new todoCommand(taskList, ui);
        case DEADLINE:
            return new deadlineCommand(taskList, ui);
        case EVENT:
            return new eventCommand(taskList, ui);
        case DELETE:
            return new deleteCommand(taskList, ui);
        default:
            return new InvalidCommand(taskList, ui);
        }
    }
}
