import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Blawg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>(100);
        String line = "____________________________________________________________\n";
        String intro = line +
                " Paws what you're doing! I'm Blawg\n" +
                " What can I do for you?\n" +
                line;
        System.out.print(intro);
        String outro = line +
                " Alright, this kitty's got to go chase some shadows. See you later!\n" +
                line;
        while (true) {
            try {
                String userInput = sc.next();
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.print(outro);
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    StringBuilder output = new StringBuilder();
                    output.append(line);
                    for (int i = 0; i < taskList.size(); i++) {
                        output.append(i + 1).append(".");
                        output.append(taskList.get(i));
                    }
                    output.append(line);
                    System.out.print(output);
                } else if (userInput.equalsIgnoreCase("mark")) {
                    while (true) {
                        int num = sc.nextInt();
                        if (num > taskList.size() || num <= 0) {
                            throw new InvalidInputException("Please enter a valid number\n");
                        } else {
                            taskList.get(num - 1).markDone();
                            String output = line + "Nice! I've marked this task as done:\n" +
                                    taskList.get(num - 1) + line;
                            System.out.print(output);
                            break;
                        }
                    }
                } else if (userInput.equalsIgnoreCase("unmark")) {
                    while (true) {
                        int num = sc.nextInt();
                        if (num > taskList.size() + 1 || num < 0) {
                            throw new InvalidInputException("Please enter a valid number\n");
                        } else {
                            taskList.get(num - 1).unmarkDone();
                            String output = line + "OK, I've marked this task as not done yet:\n" +
                                    taskList.get(num - 1) + line;
                            System.out.print(output);
                            break;
                        }
                    }
                } else if (userInput.equalsIgnoreCase("todo")) {
                    String description = sc.nextLine();
                    if (description.isEmpty()) {
                        throw new InvalidInputException("Invalid input! Proper usage is \n" +
                                "todo {task description}\n");
                    }
                    ToDos task = new ToDos(description);
                    taskList.add(task);
                    System.out.print(line +
                            "Got it. I've added this task:\n" + task + "\n" +
                            String.format("Now you have %d tasks in the list\n", taskList.size()) +
                            line);
                } else if (userInput.equalsIgnoreCase("deadline")) {
                    String input = sc.nextLine();
                    String lowerCaseInput = input.toLowerCase();

                    int byIndex = lowerCaseInput.indexOf(" /by ");
                    int byAltIndex = lowerCaseInput.indexOf(" by ");

                    int indexToUse = (byIndex != -1) ? byIndex : byAltIndex;
                    int lengthToSkip = (byIndex != -1) ? 5 : 4;
                    String description = "";
                    String date = "";
                    if (indexToUse == -1) {
                        throw new InvalidInputException("Invalid input! Proper usage is \n" +
                                "deadline {task description} by {date}\n");
                    }
                    if (indexToUse != -1) {
                        description = input.substring(0, indexToUse).trim();
                        date = input.substring(indexToUse + lengthToSkip).trim();
                    }
                    Deadlines task = new Deadlines(description, date);
                    taskList.add(task);
                    System.out.print(line +
                            "Got it. I've added this task:\n" + task + "\n" +
                            String.format("Now you have %d tasks in the list\n", taskList.size()) +
                            line);
                } else if (userInput.equalsIgnoreCase("event")) {
                    String input = sc.nextLine();
                    String lowerCaseInput = input.toLowerCase();

                    int fromIndex = lowerCaseInput.indexOf(" /from ");
                    int fromAltIndex = lowerCaseInput.indexOf(" from ");
                    int fromIndexToUse = (fromIndex != -1) ? fromIndex : fromAltIndex;
                    int fromLengthToSkip = (fromIndex != -1) ? 7 : 6;

                    int toIndex = lowerCaseInput.indexOf(" /to ");
                    int toAltIndex = lowerCaseInput.indexOf(" to ");
                    int toIndexToUse = (toIndex != -1) ? toIndex : toAltIndex;
                    int toLengthToSkip = (toIndex != -1) ? 5 : 4;
                    String description = "";
                    String startDate = "";
                    String endDate = "";
                    if (fromIndexToUse == -1 || toIndexToUse == -1) {
                        throw new InvalidInputException("Invalid input! Proper usage is \n" +
                                "event {task description} from {start date} to {end date}\n");
                    }
                    if (fromIndexToUse != -1 && toIndexToUse != -1) {
                        description = input.substring(0, fromIndexToUse).trim();
                        startDate = input.substring(fromIndexToUse + fromLengthToSkip, toIndexToUse);
                        endDate = input.substring(toIndexToUse + toLengthToSkip).trim();
                    }
                    Events task = new Events(description, startDate, endDate);
                    taskList.add(task);
                    System.out.print(line +
                            "Got it. I've added this task:\n" + task + "\n" +
                            String.format("Now you have %d tasks in the list\n", taskList.size()) +
                            line);
                } else {
                    throw new EmptyException("I don't know what that means :( Valid commands are: \n" +
                            "list, todo, deadline, event, mark, unmark, bye\n");
                }
            } catch (EmptyException e) {
                System.out.print(line + e.getMessage() + line);
            } catch (InvalidInputException e) {
                System.out.print(line + e.getMessage()+ line);
            }
        }
        sc.close();
    }
}
