public abstract class Command {
    enum CommandType {
        LIST,
        DELETE,
        ADD,
        MARK
    }
    private String text;
    private CommandType type;
    public Command(String text, CommandType type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public abstract void execute(State state);
}
