import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> todo = new ArrayList<>();
        String divider = "---------------------------------------------------------------";
        String name = "Dwight Schrute";
        System.out.printf("%s\nHello! I'm %s\nWhat Can I do for you?\n%s\n"
                , divider, name, divider);
        String input;
        int idx;
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            System.out.printf("\t%s\n", divider);
            switch (input.split(" ")[0]) {
                case ("bye"):
                    System.out.println("\tBye. Hope to see you again soon!");
                    scanner.close();
                    return;
                case ("list"):
                    for (int i = 0; i < todo.size(); i++) {
                        System.out.printf("\t%d. %s\n", i+1, todo.get(i));
                    }
                    break;
                case("mark"):
                    idx = Integer.parseInt(input.split(" ")[1]);
                    todo.get(idx - 1).mark();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.printf("\t\t%s\n", todo.get(idx - 1));
                    break;
                case("unmark"):
                    idx = Integer.parseInt(input.split(" ")[1]);
                    todo.get(idx - 1).unmark();
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.printf("\t\t%s\n", todo.get(idx - 1));
                    break;
                default:
                    todo.add(new Task(input));
                    System.out.printf("\tadded: %s\n", input);
            }
            System.out.printf("\t%s\n", divider);
        }

    }
}
