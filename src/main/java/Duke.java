import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("\n WHat can i do for you?");

        Scanner sc = new Scanner(System.in);
        System.out.print(" ");
        String in = sc.nextLine();

        while(!in.equals("bye")) {
            System.out.println(in);
            System.out.print(" ");
            in = sc.nextLine();
        }

        System.out.println("THEN? hope to see you again!!");
    }
}
