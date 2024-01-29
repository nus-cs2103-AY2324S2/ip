public abstract class Command {
    protected final String keyword;
    protected final String parameters;

    protected Command(String keyword, String parameters) {
        this.keyword = keyword;
        this.parameters = parameters;
    }

    boolean isExit() {
        return false;
    }

    abstract public void execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException;
}
