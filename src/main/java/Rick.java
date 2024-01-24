import java.util.Scanner;
public class Rick {
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else {
                echo(input);
            }
        }
    }
    public static void hello() {
        String hello = "Hello! I'm Rick\n"+
                "What can I do for you ?\n";
        reply(hello);
    }

    public static void reply(String arg) {
        String divider = "\n____________________________________________________________\n";
        System.out.println(divider + arg + divider);
    }

    public static void exit(){
        String exit = "Bye. Hope to see you again soon !";
        reply(exit);
    }
    public static void echo(String args){
        reply(args);
    }
}
