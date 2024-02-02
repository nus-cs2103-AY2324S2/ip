import java.util.Scanner;
public class OedipusRex {
    public static void main(String[] args) {
        String line = "------------------------------------------";
        String[] listOfInputs = new String[100];
        int current = 0;

        Scanner input = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! I'm OedipusRex\n" + "What can I do for you?");
        System.out.println(line);

        while(true) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if(command.equals("list")) {
                StringBuilder listOutput = new StringBuilder();
                for(int i = 0; i < listOfInputs.length; i++) {
                    if(listOfInputs[i] == null) {
                        break;
                    } else {
                        listOutput.append(i + 1).append(". ").append(listOfInputs[i]).append("\n");
                    }
                }
                System.out.println(line);
                System.out.println(listOutput);
                System.out.println(line);
            }
            else {
                listOfInputs[current] = command;
                current++;
                System.out.println(line);
                System.out.println("Added: " + command);
                System.out.println(line);
            }
        }
    }
}