import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n";
        System.out.println(line);
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu");
        System.out.println(line + "\n");
        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<String> tasks = new ArrayList<String>();
        while (true) {
            input = sc.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i, tasks.get(i - 1));
                }
            } else {
                tasks.add(input);
                System.out.printf("Added: %s%n", input);
            }
            System.out.println(line);
        }
        System.out.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        System.out.println(line);
    }
}
