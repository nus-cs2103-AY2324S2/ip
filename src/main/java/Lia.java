import java.util.*;
public class Lia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Lia :)");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for(int j = 1; j < tasks.size() + 1; j++) {
                    System.out.println(j + ". " + tasks.get(j - 1));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
