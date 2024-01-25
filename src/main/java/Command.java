public abstract class Command {
    String text;

    Command(String text) {
        this.text = text;
    }

    abstract void execute(TaskList taskList, Ui ui) throws MikeException;

    abstract boolean isExit();

}
