import java.util.Scanner;

public class Klee {
    public static void main(String[] args) {
        String divider = "____________________________________________________________________________";
        System.out.println(divider);
        System.out.println("Hello! My name is Klee.");
        System.out.println("Are you here to break Klee out of solitary confinement?");
        System.out.println(divider);
        String[] tasks = new String[100];
        int currentIndex = 0;
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < currentIndex; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                System.out.println(divider);
            } else {
                if (input.equals("")) {
                    System.out.println(divider);
                    System.out.println("Did you say something? Klee could not hear you over my bombs... [Empty string ignored]");
                    System.out.println(divider);
                } else {
                    System.out.println(divider);
                    System.out.println("Klee will help you write that down! : " + input);
                    tasks[currentIndex] = input;
                    currentIndex++;
                    System.out.println(divider);
                }
            }
        };
        System.out.println(divider);
        System.out.println("Goodbye. Klee will go back to solitary confinement now...");
        System.out.println(divider);
    }
}
