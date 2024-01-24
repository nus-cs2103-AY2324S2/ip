public interface Command {
    public String name();
    public int execute(ChatSession chatSession);
}
