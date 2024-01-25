import java.util.Scanner;

public class Lemona {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
            } else {
                System.out.println("\t______________________________________________________");
                System.out.println("\t " + input);
                System.out.println("\t______________________________________________________");
            }
        }
        scanner.close();
    }
}
