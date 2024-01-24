public class Bye implements Command {
    public static String NAME = "bye";

    public String getName() {
        return Bye.NAME;
    }

    public void execute(ChatSession session, String commandArgs) {
        session.printMessage("Bye. Hope to see you again soon!");
        session.terminateChat();
    }
}
