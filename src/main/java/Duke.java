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
                default:
                    addList(input);
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
    public static void addList(String content){
        listItems.add(new Task(content));
        System.out.println("\t____________________________________________________________");
        System.out.println("\tAdded: " + content);
        System.out.println("\t____________________________________________________________\n");
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
