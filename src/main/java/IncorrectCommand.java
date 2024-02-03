public class IncorrectCommand extends Command {
    private Exception e;

    public IncorrectCommand (Exception e) {
        this.e = e;
    }

    @Override
    public void execute(Ui ui) {
        ui.printError(e);
    }
}
