import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private String name;
    private ArrayList<Task> list;

    public Duke(String name) {
        this.name = name;
        this.list = new ArrayList<Task>();
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?\n");
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void say(String message) {
        System.out.println(message);
    }

    public void addToList(String taskMessage) {
        Task task = new Task(taskMessage);
        this.list.add(task);
        System.out.println(String.format("added: %s", task.getDescription()));
    }

    public void displayList() {
        int index = 1;
        for (Task task : this.list){
            System.out.println(String.format("%d. [%s] %s", index, task.getStatusIcon(), task.getDescription()));
            index++;
        }
    }

    public void markTask(int index) {
        this.list.get(index - 1).markAsDone();
    }

    public void unmarkTask(int index) {
        this.list.get(index - 1).maskAsUndone();
    }
    public void readCommand(String command){
        if (Objects.equals(command, "list")) {
            this.displayList();
            return;
        }

        if (command.startsWith("mark ")) {
            String[] split = command.split(" ");
            String s = split[1];
            this.markTask(Integer.valueOf(s));
            return;
        }

        if (command.startsWith("unmark ")) {
            String[] split = command.split(" ");
            String s = split[1];
            this.unmarkTask(Integer.valueOf(s));
            return;
        }

        if (Objects.equals(command, "bye")) {
            this.exit();
            System.exit(0);
        }
        this.addToList(command);
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke("Bob");
        chatbot.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            chatbot.readCommand(command);
        }
    }
}

