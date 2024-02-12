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

    public static void printDeletion(Task deletedTask, int current) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + current + " task(s) in the list.");
        System.out.println(line);
    }

    public static void main(String[] args) {
        String line = "------------------------------------------";
        Task[] listOfTasks = new Task[100];
        SaveAndLoad.load(listOfTasks);

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
                SaveAndLoad.save(listOfTasks);
                break;
            } else if (command.equals("list")) {
                StringBuilder listOutput = new StringBuilder();
                for (int i = 0; i < listOfTasks.length; i++) {
                    if (listOfTasks[i] == null) {
                        break;
                    } else {
                        listOutput.append(i + 1).append(". ").append(listOfTasks[i].toString())
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
                                listOfTasks[index].mark();
                                System.out.println("Dje si pizda materina! I've marked this task as done:" + "\n" +
                                        listOfTasks[index].toString());
                            } catch (NumberFormatException e) {
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
                                listOfTasks[index].unmark();
                                System.out.println("Baga-mi-as pula, it's been undone" + "\n" +
                                        listOfTasks[index].toString());
                            } catch (NumberFormatException e) {
                                System.out.println("OOPS!!! The input after the unmark command has to be an integer.");
                            }
                        }
                        break;
                    }
                    case "todo": {
                        String taskDescription = String.join(" ", details);
                        try {
                            listOfTasks[current] = new ToDo(taskDescription);
                            current++;
                            printComplexTask(listOfTasks, current);
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
                            listOfTasks[current] = new Deadline(taskDescription.toString(), deadline.toString());
                            current++;
                            printComplexTask(listOfTasks, current);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println(e);
                            break;
                        } catch (InvalidDateException e) {
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
                            listOfTasks[current] = new Event(taskDescription.toString(), from.toString(), to.toString());
                            current++;
                            printComplexTask(listOfTasks, current);
                            break;
                        } catch (InvalidInputException | InvalidDateException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                    case "delete": {
                        if (brokenCommand.length < 2) {
                            System.out.println("OOPS!!! The number for the delete command cannot be empty.");
                        } else {
                            try {
                                Task deletedTask = null;
                                int index = Integer.parseInt(brokenCommand[1]) - 1;
                                for (int i = 0; i < listOfTasks.length; i++) {
                                    Task currentTask = listOfTasks[i];
                                    if (currentTask == null) {
                                        if (i <= index) {
                                            throw new ArrayIndexOutOfBoundsException();
                                        }
                                        break;
                                    } else if (i >= index) {
                                        if (i == index) {
                                            deletedTask = currentTask;
                                        }
                                        listOfTasks[i] = listOfTasks[i + 1];
                                    }
                                }
                                current--;
                                printDeletion(deletedTask, current);
                            } catch (NumberFormatException e) {
                                System.out.println("OOPS!!! The input after the delete command has to be an integer.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("OOPS!!! The input for delete is out of bounds.");
                            }
                        }
                        break;
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