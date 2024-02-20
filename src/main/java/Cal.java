import java.util.ArrayList;
import java.util.Scanner; 

public class Cal {
    static String line = "____________________________________________________________";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void greet() {
        String name = "Cal";
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        //System.out.println(logo);
        System.out.println(line);
    }
    
    public static void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void echo(String input) { 
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public static void list() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        System.out.println(line);
    }

    public static String add(String input) {
        tasks.add(input);
        return String.format("added: %s", input);
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")){
                list();
            } else {
                input = add(input); 
                echo(input);
            }
        }
        sc.close();
        
        exit();
    }
}
