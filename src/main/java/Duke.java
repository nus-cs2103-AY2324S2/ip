import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n");

        while (true) {
            String str = scanner.nextLine();
            if (str.equals("bye")) {
                break;
            }
            System.out.println("\t\t"+str);
        }
        System.out.println("\t\tBye bye, see you next time !!!");
    }
}
