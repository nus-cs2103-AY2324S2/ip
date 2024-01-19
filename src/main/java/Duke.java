import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String botName = "Wind";
        System.out.println("Hello I'm " + botName + "\n"
                + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<String> items = new ArrayList<>(100);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
               System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if(input.equals("list")) {
                for(int i = 0; i < items.size(); i++) {
                    int number = i + 1;
                    System.out.println(number + ". " + items.get(i));
                }
                continue;
            }
            items.add(input);
            System.out.println("added: " + input);

        }
    }
}
