public class ExitCommand extends Command {

    @Override
    public void execute() throws MeanDukeException {
        Ui.printOutro();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
