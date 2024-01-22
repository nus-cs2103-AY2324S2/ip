import java.util.Scanner;

public class Duke {
    static String botName = "Corgi";
    static String[] tasks = new String[100];
    static int numOfTasks = 0;
    public static void greet(){
        String greetMessage = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm %s\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n", botName);
        System.out.println(greetMessage);
    }
    public static void goodbye(){
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exitMessage);
    }
    public static void echo(String input){
        System.out.println("____________________________________________________________\n" +
                input +
                "\n____________________________________________________________\n");
    }
    public static void addToList(String input){
        tasks[numOfTasks++] = input;
        String message = String.format("____________________________________________________________\n" +
                " added: %s\n" +
                "____________________________________________________________\n", input);
        System.out.println(message);
    }
    public static void printList(){
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < numOfTasks; i++) {
            String currentTask = String.format("%d: %s", i + 1, tasks[i]);
            System.out.println(currentTask);
        }
        System.out.println("____________________________________________________________\n");
    }
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true){
            input = sc.nextLine();
            if(input.toLowerCase().equals("bye")){
                break;
            } else if (input.toLowerCase().equals("list")) {
                printList();
            } else{
                addToList(input);
            }
        }
        goodbye();
    }
}
