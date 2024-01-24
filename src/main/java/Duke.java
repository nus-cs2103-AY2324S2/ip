import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> todo = new ArrayList<>();
        String divider = "---------------------------------------------------------------";
        String name = "Dwight Schrute";
        System.out.printf("%s\nHello! I'm %s\nWhat Can I do for you?\n%s\n"
                , divider, name, divider);
        String input;
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            switch (input) {
                case ("bye"):
                    System.out.printf("\t%s\n\tBye. Hope to see you again soon!\n\t%s\n", divider,divider);
                    scanner.close();
                    return;
                case ("list"):
                    System.out.printf("\t%s\n", divider);
                    for (int i = 0; i < todo.size(); i++) {
                        System.out.printf("\t%d. %s\n", i+1, todo.get(i));
                    }
                    System.out.printf("\t%s\n", divider);
                    break;
                default:
                    System.out.printf("\t%s\n", divider);
                    todo.add(input);
                    System.out.printf("\tadded: %s\n", input);
                    System.out.printf("\t%s\n", divider);
            }

        }

    }
}
