import java.util.Objects;
import java.util.Scanner;

public class Detective {
    public static void main(String[] args) {
        String name = "Detective";
        String line = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        System.out.println(line + "\nHello! I'm [" + name + "]" + "\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = sc.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}