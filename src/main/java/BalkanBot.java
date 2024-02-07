import java.util.Arrays;
import java.util.Scanner;

public class BalkanBot {
    private static final String line = "------------------------------------------";

    public static void printComplexTask(Task[] arr, int current) {
        System.out.println(line);
        System.out.println("Got it I've now added this task:");
        System.out.println(arr[current - 1].toString());
        System.out.println("Now you have " + current + " task(s) in the list.");
        System.out.println(line);
    }

    public static void main(String[] args) {
        String line = "------------------------------------------";
        Task[] listOfInputs = new Task[100];
        int current = 0;
        String state = "Now you have " + current + 1 + " tasks in the list.";

        Scanner input = new Scanner(System.in);

        System.out.println(line);
        System.out.println("I'm Balkan Bot\n" + "Jebem ti mat");
        System.out.println(line);


        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("Јебаћу ти бабицу");
                System.out.println(line);
                break;
            } else if (command.equals("list")) {
                StringBuilder listOutput = new StringBuilder();
                for (int i = 0; i < listOfInputs.length; i++) {
                    if (listOfInputs[i] == null) {
                        break;
                    } else {
                        listOutput.append(i + 1).append(". ").append(listOfInputs[i].toString())
                                .append("\n");
                    }
                }
                System.out.println(line);
                System.out.println("Here is your list of tasks:");
                System.out.println(listOutput);
                System.out.println(line);
            } else {
                String[] brokenCommand = command.split("\\s+");
                String advancedCommand = brokenCommand[0];
                String[] details = Arrays.copyOfRange(brokenCommand, 1, brokenCommand.length);
                switch (advancedCommand) {
                    case "mark": {
                        if (brokenCommand.length < 2) {
                            System.out.println("OOPS!!! The number for the mark command cannot be empty.");
                        } else {
                            try {
                                int index = Integer.parseInt(brokenCommand[1]) - 1;
                                listOfInputs[index].mark();
                                System.out.println("Dje si pizda materina! I've marked this task as done:" + "\n" +
                                        listOfInputs[index].toString());
                            } catch (NumberFormatException e){
                                System.out.println("OOPS!!! The input after the mark command has to be an integer.");
                            }
                        }
                        break;
                    }
                    case "unmark": {
                        if (brokenCommand.length < 2) {
                            System.out.println("OOPS!!! The number for the unmark command cannot be empty.");
                        } else {
                            try {
                                int index = Integer.parseInt(brokenCommand[1]) - 1;
                                listOfInputs[index].unmark();
                                System.out.println("Baga-mi-as pula, it's been undone" + "\n" +
                                        listOfInputs[index].toString());
                            } catch (NumberFormatException e) {
                                System.out.println("OOPS!!! The input after the unmark command has to be an integer.");
                            }
                        }
                        break;
                    }
                    case "todo": {
                        String taskDescription = String.join(" ", details);
                        try {
                            listOfInputs[current] = new ToDo(taskDescription);
                            current++;
                            printComplexTask(listOfInputs, current);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                    case "deadline": {
                        StringBuilder taskDescription = new StringBuilder();
                        StringBuilder deadline = new StringBuilder();
                        boolean foundDeadline = false;
                        for (String currentString : details) {
                            if (foundDeadline) {
                                deadline.append(currentString).append(" ");
                            } else if (currentString.equals("/by")) {
                                foundDeadline = true;
                            } else {
                                taskDescription.append(currentString).append(" ");
                            }
                        }
                        try {
                            listOfInputs[current] = new Deadline(taskDescription.toString(), deadline.toString());
                            current++;
                            printComplexTask(listOfInputs, current);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                    case "event": {
                        StringBuilder taskDescription = new StringBuilder();
                        StringBuilder from = new StringBuilder();
                        StringBuilder to = new StringBuilder();
                        boolean foundFrom = false;
                        boolean foundTo = false;
                        for (String currentString : details) {
                            if (foundTo) {
                                to.append(currentString).append(" ");
                            } else if (foundFrom) {
                                if (currentString.equals("/to")) {
                                    foundTo = true;
                                } else {
                                    from.append(currentString).append(" ");
                                }
                            } else if (currentString.equals("/from")) {
                                foundFrom = true;
                            } else {
                                taskDescription.append(currentString).append(" ");
                            }
                        }
                        try {
                            listOfInputs[current] = new Event(taskDescription.toString(), from.toString(), to.toString());
                            current++;
                            printComplexTask(listOfInputs, current);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                    case "delete": {

                    }
                    default: {
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        break;
                    }
                }
            }
        }
    }
}