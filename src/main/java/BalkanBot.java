import java.util.Scanner;
public class BalkanBot {
    public static void main(String[] args) {
        String line = "------------------------------------------";
        Task[] listOfInputs = new Task[100];
        int current = 0;

        Scanner input = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! I'm Balkan Bot\n" + "What can I do for you?");
        System.out.println(line);

        while(true) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
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
                if(brokenCommand[0].equals("mark")) {
                    int index = Integer.parseInt(brokenCommand[1]) - 1;
                    listOfInputs[index].mark();
                    System.out.println("Nice! I've marked this task as done:" + "\n" +
                            listOfInputs[index].toString());
                }
                else if(brokenCommand[0].equals("unmark")) {
                    int index = Integer.parseInt(brokenCommand[1]) - 1;
                    listOfInputs[index].unmark();
                    System.out.println("OK, I've marked this task as not done yet:" + "\n" +
                            listOfInputs[index].toString());
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