import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Dave";
        String horizontalLine = "____________________________________________________________\n";
        String exitCmd = "bye";

        // greeting
        System.out.println(String.format("%s\n%s: Nice to meet you, I'm the ever-helpful %s!\nHow may I be of service today?\n%s", horizontalLine, name, name, horizontalLine));

        // read input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputList = new String[100];
        int listIdx = 0;

        // user input
        while (!input.equals(exitCmd)) {
            if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < listIdx; i++) {
                    System.out.println(String.format("%d. %s", i+1, inputList[i]));
                }
                System.out.println(horizontalLine);
                
            } else {
                inputList[listIdx] = input;
                listIdx++;
                System.out.println(String.format("%s\nDave added: %s\n%s", horizontalLine, input, horizontalLine));
            }
            input = sc.nextLine();

        }

        // end program if user says bye
        System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s", horizontalLine, horizontalLine));

        sc.close();
    }
}
