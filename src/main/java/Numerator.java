import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Numerator {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String logo = line +
                "    Hello! I'm Numerator\n" +
                "    What can I do for you?\n" + line;
        String bye = line +
                "     Bye. Hope to see you again soon!\n" +
                line;

        String added = line +
                "     added: %s\n" +
                line;

        Scanner scanner = new Scanner(System.in);
        System.out.println(logo);
        ArrayList<String> store = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(bye);
                break;
            } else if (input.equals("list")) {
                System.out.print(line);
                if (store.isEmpty()) {
                    System.out.println("Nothing in list");
                } else {
                    IntStream.iterate(1, x -> x + 1)
                            .limit(store.size())
                            .forEachOrdered(i -> {
                                String s = String.format("    %d. %s", i, store.get(i - 1));
                                System.out.println(s);
                            });
                }
                System.out.print(line);
            } else {
                store.add(input);
                String formatted = String.format(added, input);
                System.out.println(formatted);
            }


        }
    }
}
