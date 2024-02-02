public class PrintListCommand extends Command {

    public void execute(TaskList tlist) {
        return;
    }

    public void execute(TaskList tlist, Ui ui, Storage cacheFile) {
        ui.showList(tlist);
    }

    public boolean isExit() {
        return false;
    }
}
