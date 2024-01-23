import java.util.Scanner;

public class Klee {
    public static void main(String[] args) {
        String divider = "____________________________________________________________________________";
        System.out.println(divider);
        System.out.println("Hello! My name is Klee.");
        System.out.println("Are you here to break Klee out of solitary confinement?");
        System.out.println(divider);
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(divider);
                System.out.println(input);
                System.out.println(divider);
            }
        };
        System.out.println(divider);
        System.out.println("Goodbye. Klee will go back to solitary confinement now...");
        System.out.println(divider);
    }
}
