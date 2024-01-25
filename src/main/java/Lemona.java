import java.util.ArrayList;
import java.util.Scanner;

public class Lemona {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        //greeting
        System.out.println("\t______________________________________________________" +
                "\n\t" + " Hello! I'm Lemona" + "\n\t" + " What can I do for you?" +
                "\n\t______________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t______________________________________________________");
                System.out.println("\t" +" Bye. Hope to see you again soon!");
                System.out.println("\t______________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("\t______________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + (i + 1) + ". " + list.get(i));
                }
                System.out.println("\t______________________________________________________");
            } else {
                System.out.println("\t______________________________________________________");
                list.add(input);
                System.out.println("\t " + "added: " + input);
                System.out.println("\t______________________________________________________");
            }
        }
        scanner.close();
    }
}
