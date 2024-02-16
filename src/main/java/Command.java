public abstract class Command {
    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ReacherException {
    }
}
