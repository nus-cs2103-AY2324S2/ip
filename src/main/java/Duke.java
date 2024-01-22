import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(100);
        String message = "____________________________________________________________\n" +
                "Hello! I'm Jux\n" +
                "What can I do for you?\n";
        String end = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________"
                ;
        System.out.println(message);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if(input.equals("list")) {
                for (int i = 0; i < list.size();i++) {
                    int j = i+1;
                    String listMessage = j + "." + list.get(i);
                    System.out.println(listMessage);
                }
            } else {
                list.add(input);
                System.out.println("You entered: " + input);
            }

        }
        System.out.println(end);


    }
}

