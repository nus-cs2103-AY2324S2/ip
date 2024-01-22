import java.util.*;
public class Riz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dotted = "-----------------------------------";
        //greetings
        String greetings = "Hello... I'm Riz...\n"
                + "What can I help you with today?\n"
                + dotted;
        System.out.println(greetings);
        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                running = false;
                System.out.println("Bye... Hope to see you again...\n" + dotted);
            } else {
                System.out.println(input + "...");
                System.out.println(dotted);
            }
        }
    }
}
