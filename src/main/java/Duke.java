import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greet();
        command();
    }
    static String name = "Alfred";
    static ArrayList<Task> listItems = new ArrayList<>();
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void mark(){
            this.isDone = true;
        }
        public void unmark(){
            this.isDone = false;
        }
        @Override
        public String toString(){
            String output = "[" + this.getStatusIcon() + "] " + this.description;
            return output;
        }
    }
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    public static class Event extends Task {

        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to +")";
        }
    }
    public static void command() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] cmds = input.split(" ");
            switch(cmds[0]) {
                case "list":
                    showList();
                    break;
                case "mark":
                    markList(Integer.parseInt(cmds[1]));
                    break;
                case "unmark":
                    unmarkList(Integer.parseInt(cmds[1]));
                    break;
                case "todo":
                    addTodo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                default:
                    break;
            }
            input = scanner.nextLine();
        }
        exit();
    }
    public static void greet(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGreetings! I am " + name +".");
        System.out.println("\tHow may I be of service to you today?");
        System.out.println("\t____________________________________________________________\n");
    }
    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tFarewell. Wishing for the opportunity to meet you again soon.");
        System.out.println("\t____________________________________________________________\n");
    }
    public static void addList(Task task){
        listItems.add(task);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tI have added the following task: ");
        System.out.println("\t   "+task);
        System.out.println("\tNow you have " + listItems.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________\n");
    }
    public static void addTodo(String cmd){
        String[] statement = cmd.split(" ", 2);
        ToDo todo = new ToDo(statement[1]);
        addList(todo);
    }
    public static void addDeadline(String cmd){
        String[] statement1 = cmd.split(" ", 2);
        String[] statement2 = statement1[1].split(" /by ", 2);
        Deadline ddl = new Deadline(statement2[0], statement2[1]);
        addList(ddl);
    }
    public static void addEvent(String cmd){
        String[] statement1 = cmd.split(" ", 2);
        String[] statement2 = statement1[1].split(" /from ", 2);
        String[] statement3 = statement2[1].split(" /to ", 2);
        Event event = new Event(statement2[0], statement3[0], statement3[1]);
        addList(event);
    }
    public static void showList(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tPer your request, I am outlining the tasks from your designated list:");
        int index = 1;
        for (Task item : listItems){
            System.out.println("\t" + index + ". " + item);
            index++;
        }
        System.out.println("\t____________________________________________________________\n" );
    }
    public static void markList(int index){
        listItems.get(index-1).mark();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tIt is my pleasure to inform you that I have officially marked this particular task as 'completed':");
        System.out.println("\t   " + listItems.get(index-1));
        System.out.println("\t____________________________________________________________\n" );
    }
    public static void unmarkList(int index){
        listItems.get(index-1).unmark();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tI wish to communicate that I have marked this particular task as 'incomplete' at this juncture:");
        System.out.println("\t   " + listItems.get(index-1));
        System.out.println("\t____________________________________________________________\n" );
    }
}
