import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        //Greetings
        String logo = "Tommy";
        String divider = "____________________________";
        System.out.println(divider);
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider);


        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                // listing
                System.out.println(divider);

                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + "." + hashMap.get(index) + " " + list.get(i));
                }

                System.out.println(divider);
            } else if (userInput.contains("unmark")) {
                // Unmarking
                System.out.println(divider);

                int index = Integer.parseInt(userInput.substring(7));
                hashMap.put(index, "[ ]");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(hashMap.get(index) + " " + list.get(index - 1));

                System.out.println(divider);

            } else if (userInput.contains("mark")) {
                // Marking done
                System.out.println(divider);

                int index = Integer.parseInt(userInput.substring(5));
                hashMap.put(index, "[X]");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(hashMap.get(index) + " " + list.get(index - 1));

                System.out.println(divider);

            } else {
                // Adding tasks
                System.out.println(divider);

                list.add(userInput);
                hashMap.put(list.size(), "[ ]");
                System.out.println("added: " + userInput);

                System.out.println(divider);

            }

            userInput = scanner.nextLine();
        }

        //Farewell
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);

        scanner.close();
    }
}
