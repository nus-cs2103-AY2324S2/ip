public interface Command {
    public String getName();
    public void execute(ChatSession chatSession, String commandArgs);
}
