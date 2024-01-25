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
    public static void greet(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGreetings! I am " + name +".");
        System.out.println("\tHow may I be of service to you today?");
        System.out.println("\t____________________________________________________________\n");
    }

    public static void exit(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tFarewell. Wishing for the opportunity to meet you again soon.");
        System.out.println("\t____________________________________________________________");
    }

    public static void command() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = scanner.nextLine();
        }
        exit();
    }

    public static void echo(String content){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t"+content);
        System.out.println("\t____________________________________________________________\n" );
    }

    }
