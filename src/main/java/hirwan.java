import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class hirwan {
    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        System.out.println("Hello! " + logo);
        int count = 1;
        List<String> List = new ArrayList<>();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                break;
            } else if(text.equals("list")) {
                for (String element : List) {
                    System.out.println(element);
                }
            } else {
                System.out.println("added: " + text);
                List.add(count + ". " + text);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
