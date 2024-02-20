import java.util.Scanner; 

public class Cal {
    public static void greet() {
        String line = "____________________________________________________________";
        String name = "Cal";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(logo);
    }
    
    public static void exit() {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) { 
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.toLowerCase() == "bye") {
                break;
            } else {
                echo(input);
            }
        }
        sc.close();
        
        exit();
    }
}
