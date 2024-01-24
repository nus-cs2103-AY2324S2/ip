import java.util.List;

public class ChatSession {
    public List<Task> taskList;

    ChatSession() {
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
}
