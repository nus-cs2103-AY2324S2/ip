import java.util.List;

public class ChatSession {
    public List<Task> taskList;
    public List<Command> commandList;
    public boolean continueSession;

    ChatSession() {
        this.continueSession = true;
        this.taskList = List.of();
        this.initChat();
    }

    public void initChat() {
        String message = "____________________________________________________________\n" +
        "Hello! I'm GoldBot!\n" +
        "What can I do for you?\n" +
       "____________________________________________________________" +
        "Bye. Hope to see you again soon!" +
       "____________________________________________________________";
        System.out.println(message);
    }

    public void initCommands() {
        this.commandList = List.of();
    }

    public void printMessage(String message) {
        this.printHorizontalLine();
        System.out.println(message);
        this.printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void terminateChat() {
        this.continueSession = false;
    }

    public void handleMessage(String message) {
        String[] result = message.split(" ", 2);
        String command = result[0];
        String commandArgs = result[1];
        
    }
}
