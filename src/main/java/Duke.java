import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private String name;
    private ArrayList<Task> list;

    private enum Command{
        todo,
        delete,
        bye,
        mark,
        unmark,
        deadline,
        event
    }
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

    public int getListSize() {
        return this.list.size();
    }

    public String getListUpdate() {
        if (this.getListSize() == 0) {
            return "Wow!! i didn't expect you to actually do work.... great job i guess..";
        }
        if (this.getListSize() <= 5) {
            return "A little bit more work u lazy fool";
        }
        return "HUMAN! stop WATCHING YOUTUBE and start DOING WORK!!!";
    }

    public void markTask(int index) {
        try {
            this.list.get(index - 1).markAsDone();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! you fool");
        }
    }

    public void unmarkTask(int index) {
        try {
            this.list.get(index - 1).maskAsUndone();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! you fool");
        }
    }

    public void deleteTask(int index) {
        try {
            Task t = this.list.remove(index - 1);
            System.out.println("Noted. The following task is removed, you careless human being!");
            System.out.println(String.format("[%s] [%s] %s\n", t.getTypeIcon(), t.getStatusIcon(), t.getDescription() ));
            System.out.println(String.format("Now you only have %d tasks left. %s", this.getListSize(), this.getListUpdate()));
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! you fool");
        }
    }

    public void readCommand(String command){

        String[] split_command = command.split(" ", 2);
        String method = split_command[0];
        try {
            switch(method) {
                case "list":
                    this.displayList();
                    break;

                case "bye":
                    this.exit();
                    System.exit(0);
                    break;

                case "mark":
                    if (split_command.length <= 1) {
                        throw new DukeException("There is no task at that index!");
                    }
                    String s = split_command[1];
                    if (Integer.valueOf(s) > this.getListSize()) {
                        throw new DukeException("Invalid Index, is that a typo??");
                    }
                    this.markTask(Integer.valueOf(s));
                    break;

                case "unmark":
                    if (split_command.length <= 1) {
                        throw new DukeException("There is no task at that index!");
                    }
                    String s2 = split_command[1];
                    if (Integer.valueOf(s2) > this.getListSize()) {
                        throw new DukeException("Invalid Index, is that a typo??");
                    }
                    this.unmarkTask(Integer.valueOf(s2));
                    break;

                case "todo":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description for your task!");
                    }
                    this.addToList(new Todo(split_command[1]));
                    break;

                case "deadline":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description and a deadline for your task!");
                    }
                    String[] info_split = split_command[1].split("/by ", 2);
                    if (info_split.length <= 1) {
                        throw new DukeException("Please include a deadline by using by keyword like '/by Thursday'");
                    }
                    String deadline_desc = info_split[0];
                    String date = info_split[1];
                    this.addToList(new Deadline(deadline_desc, date));
                    break;

                case "event":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description and the time period for your task!");
                    }
                    String[] info_split2 = split_command[1].split("/from ", 2);
                    if (info_split2.length <= 1) {
                        throw new DukeException("Please include a time period by using from and to keyword such as" +
                                    "'/from today /to tomorrow");
                    }
                    String[] info_split3 = info_split2[1].split("/to ", 2);
                    if (info_split3.length <= 1) {
                        throw new DukeException("Please include a time period by using from and to keyword such as" +
                                "'/from today /to tomorrow");
                    }
                    String event_desc = info_split2[0];
                    String from = info_split3[0];
                    String to = info_split3[1];
                    this.addToList((new Event(event_desc, from, to)));
                    break;

                case "delete":
                    if (split_command.length <= 1) {
                        throw new DukeException("Please write a description and the time period for your task!");
                    }
                    String s3 = split_command[1];
                    if (Integer.valueOf(s3) > this.getListSize()) {
                        throw new DukeException("Invalid Index, is that a typo??");
                    }
                    this.deleteTask(Integer.valueOf(s3));
                    break;
                default:
                    throw new DukeException("Unknown command");
            }
        }
        catch (DukeException e){
            System.out.println(e.getMessage());
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

