public interface Command {
    public void execute(ChatSession chatSession, String commandArgs) throws InvalidParametersException;
}
