import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Dave";
        String horizontalLine = "____________________________________________________________\n";
        String exitCmd = "bye";

        System.out.println(horizontalLine + "\nDave: Nice to meet you, I'm the ever-helpful " + name + "!\nHow may I be of service today?\n" + horizontalLine);
        
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals(exitCmd)) {
            System.out.println(String.format("%s\nDave: %s\n%s", horizontalLine, input, horizontalLine));
            input = sc.nextLine();
        }

        System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s", horizontalLine, horizontalLine));

        sc.close();
    }
}
