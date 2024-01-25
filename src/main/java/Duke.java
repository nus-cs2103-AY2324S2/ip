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
    static String[] listItems = new String[100];
    static int listSize = 0;

    public static void command() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if(input.equals("list")) {
                showList();
            }else{
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
        listItems[listSize] = content;
        listSize++;
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + content);
        System.out.println("\t____________________________________________________________\n");
    }

    public static void showList(){
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < listSize; i++){
            int index = i + 1;
            System.out.println("\t" + index + ". " + listItems[i]);
        }
        System.out.println("\t____________________________________________________________\n" );
    }

    }
