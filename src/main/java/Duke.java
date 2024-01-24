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

    public void addToList(Task t) {
        this.list.add(t);
        System.out.println(String.format("added: %s", t.getDescription()));
    }

    public void displayList() {
        int index = 1;
        for (Task task : this.list){
            System.out.println(String.format("%d. [%s] [%s] %s", index, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
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

        String[] split_command = command.split(" ", 2);
        String method = split_command[0];
        System.out.println(method);
        switch(method) {
            case "list":
                this.displayList();
                break;
            case "bye":
                this.exit();
                System.exit(0);
                break;
            case "mark":
                String s = split_command[1];
                this.markTask(Integer.valueOf(s));
                break;
            case "unmark":
                String s2 = split_command[1];
                this.unmarkTask(Integer.valueOf(s2));
                break;
            case "todo":
                this.addToList(new Todo(split_command[1]));
                break;
            case "deadline":
                String deadline_desc = split_command[1].split("/by ", 2)[0];
                String date = split_command[1].split("/by ", 2)[1];
                this.addToList(new Deadline(deadline_desc, date));
                break;
            case "event":
                String event_desc = split_command[1].split("/from ", 2)[0];
                String dates = split_command[1].split("/from ", 2)[1];
                String from = dates.split("/to ",2)[0];
                String to = dates.split("/to ", 2)[1];
                this.addToList((new Event(event_desc, from, to)));
                break;
            default:
                this.addToList(command);
                break;
        }
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

