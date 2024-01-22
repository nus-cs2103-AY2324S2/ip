import java.util.Scanner;

public class Duke {
    static String botName = "Corgi";
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
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true){
            input = sc.nextLine();
            if(input.toLowerCase().equals("bye")){
                break;
            }
            echo(input);
        }
        goodbye();
    }
}
