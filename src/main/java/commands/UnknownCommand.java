package commands;

public class UnknownCommand extends Command {

    public UnknownCommand() {}

    @Override
    public String execute() {
        return "Sorry Old Man Fredricksen don't recognise this input! "
                + "Type \"help\" if you need a guide on input format!";
    }
}
