import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        System.out.println("\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n");

        while (true) {
            String str = scanner.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println("\t\t" + i +". " + list.get(i-1));
                }
            } else {
                list.add(str);
                System.out.println("\t\tadded: " + str);
            }
        }
        System.out.println("\t\tBye bye, see you next time !!!");
    }
}
