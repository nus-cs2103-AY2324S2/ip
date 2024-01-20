import java.util.Scanner;

//Handles the main chat listening and parsing of messages
public class ChatListener {
    private Scanner sc;
    private Storage taskStorage;

    public ChatListener() {
        this.sc = new Scanner(System.in);
    }

    public int parseCommand(String task) {
        String[] taskSplit = task.split(" ");
        if (task.equals("bye")) {
            MessagePrinter.bye();
            return 1;
        } else if (task.equals("list")) {
            taskStorage.printList();
        } else if (taskSplit[0].equals("mark") || taskSplit[0].equals("unmark")) {
            //All items to be 0-index referenced other than user input.
            taskStorage.changeStatusOfItem(taskSplit[0], Integer.parseInt(taskSplit[1])-1);
        } else {
            taskStorage.add(task);
        }
        return 0;
    }
    public void chatListener() {
        Storage taskStorage = new Storage();
        this.taskStorage = taskStorage;
        while (true) {
            String task = sc.nextLine();
            int status = this.parseCommand(task);
            if (status == 1) {
                return;
            }
        }

    }
}
