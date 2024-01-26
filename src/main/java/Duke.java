import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> storedList = new ArrayList<>();

        System.out.println("Hello! I'm Duke101\n"
                + "What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (userInput.equals("list")) {
                displayList(storedList);
            } else {
                storedList.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
        scanner.close();
    }
    private static void displayList(List<String> storedList) {
        if (storedList.isEmpty()) {
            System.out.println("Nothing stored");
        } else {
            for (int i = 0; i < storedList.size(); i++) {
                System.out.println((i + 1 + "." + storedList.get(i)));
            }
        }
    }
}
