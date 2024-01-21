import java.util.Objects;
import java.util.Scanner;

public class Detective {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Detective";
        String line = "____________________________________________________________";
        String[] myList = new String[100];
        int count = 0;

        System.out.println(line + "\nHello! I'm [" + name + "]" + "\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                System.out.println(line);
                for (int i = 0; i < 100; i++) {
                    if (myList[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + myList[i]);
                }
                System.out.println(line);
                input = sc.nextLine();
            } else {
                if (count >= 100) {
                    System.out.println(line + "\n" + "The list is full, add failed");
                    input = sc.nextLine();
                } else {
                    myList[count++] = input;
                    System.out.println(line + "\n" + "added: " + input + "\n" + line);
                    input = sc.nextLine();
                }
            }
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}