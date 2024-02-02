import java.util.Arrays;
import java.util.Scanner;
public class BalkanBot {
    public static void main(String[] args) {
        String line = "------------------------------------------";
        Task[] listOfInputs = new Task[100];
        int current = 0;
        String state = "Now you have " + current + 1 + " tasks in the list.";

        Scanner input = new Scanner(System.in);

        System.out.println(line);
        System.out.println("I'm Balkan Bot\n" + "Jebem ti mat");
        System.out.println(line);

        while(true) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println(line);
                System.out.println("Јебаћу ти бабицу");
                System.out.println(line);
                break;
            }
            else if(command.equals("list")) {
                StringBuilder listOutput = new StringBuilder();
                for(int i = 0; i < listOfInputs.length; i++) {
                    if(listOfInputs[i] == null) {
                        break;
                    } else {
                        listOutput.append(i + 1).append(". ").append(listOfInputs[i].toString())
                                .append("\n");
                    }
                }
                System.out.println(line);
                System.out.println(listOutput);
                System.out.println(line);
            }
            else {
                String[] brokenCommand = command.split("\\s+");
                String advancedCommand = brokenCommand[0];
                if(advancedCommand.equals("mark")) {
                    int index = Integer.parseInt(brokenCommand[1]) - 1;
                    listOfInputs[index].mark();
                    System.out.println("Dje si pizda materina! I've marked this task as done:" + "\n" +
                            listOfInputs[index].toString());
                }
                else if(advancedCommand.equals("unmark")) {
                    int index = Integer.parseInt(brokenCommand[1]) - 1;
                    listOfInputs[index].unmark();
                    System.out.println("Baga-mi-as pula, it's been undone" + "\n" +
                            listOfInputs[index].toString());
                }
                else if(advancedCommand.equals("todo")) {
                    String taskDescription = Arrays.copyOfRange(brokenCommand, 1, brokenCommand.length - 1)
                    listOfInputs[current] = new Task(command);
                    current++;
                    System.out.println(line);
                    System.out.println("Added: " + command);
                    System.out.println(line);
                }
                else if(advancedCommand.equals("deadline")) {

                }
                else if(advancedCommand.equals("event")) {

                }
                else {
                    listOfInputs[current] = new Task(command);
                    current++;
                    System.out.println(line);
                    System.out.println("Added: " + command);
                    System.out.println(line);
                }
            }
        }
    }
}