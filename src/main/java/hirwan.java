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
            boolean mark = text.startsWith("mark");
            boolean unmark = text.startsWith("unmark");
            if (text.equals("bye")) {
                break;
            } else if(text.equals("list")) {
                for (String element : List) {
                    System.out.println(element);
                }
            } else if(mark) {
                String number = text.substring(5);
                int numberint = Integer.parseInt(number);
                if (numberint >= List.size()) {
                    System.out.println("Sorry please enter a valid index!");
                }
                String temp = List.get(numberint - 1).substring(7);
                List.set(numberint - 1, numberint + ". [X] " + temp);
                System.out.println("Nice! I've marked this task as done: \n" + "[X] " + temp);
            } else if(unmark) {
                String number = text.substring(7);
                int numberint = Integer.parseInt(number);
                if (numberint >= List.size()) {
                    System.out.println("Sorry please enter a valid index!");
                }
                String temp = List.get(numberint - 1).substring(9);
                List.set(numberint - 1, numberint + ". [ ] " + temp);
                System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
            } else {
                System.out.println("added: " + text);
                List.add(count + ". [ ] " + text);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
