public interface Command {
    public String getName();
    public int execute(ChatSession chatSession);
}
