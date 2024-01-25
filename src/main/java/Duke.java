import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws ChatbotException {
        String name = "Dave";
        String horizontalLine = "____________________________________________________________\n";
        String exitCmd = "bye";

        // greeting
        System.out.println(String.format("%s\n%s: Nice to meet you, I'm the ever-helpful %s!\nHow may I be of service today?\n%s", horizontalLine, name, name, horizontalLine));

        // read input
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        int listIdx = 0;

        // user input
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputArr = input.split(" ");
                
                if (input.isEmpty()) {
                    throw new InvalidInputException();
                }
                if (inputArr.length == 1 && (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event"))) {
                    throw new EmptyTaskException(inputArr[0]);
                } else if (inputArr[0].equals("bye")) {
                    System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s", horizontalLine, horizontalLine));
                    break;
                } else if (inputArr[0].equals("list")) {
                    System.out.println(horizontalLine + "\nHere are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.format("%d. %s", i+1, taskList.get(i).toString()));
                    }
                    System.out.println(horizontalLine);
                } else if (inputArr[0].equals("mark") || inputArr[0].equals("unmark") || inputArr[0].equals("delete")) {
                    int taskNumber = Integer.parseInt(inputArr[1]) - 1;

                    if (inputArr[0].equals("mark")) {
                        taskList.get(taskNumber).isCompleted();
                        System.out.println(String.format("%s\nGood job! Dave will mark this task as done:\n\t%s\n%s", horizontalLine, taskList.get(taskNumber).toString(), horizontalLine));    
                    }
                    if (inputArr[0].equals("unmark")) {
                        taskList.get(taskNumber).isNotCompleted();
                        System.out.println(String.format("%s\nAlright, Dave believes you'll get this done eventually:\n%s\n%s", horizontalLine, taskList.get(taskNumber).toString(), horizontalLine));
                    }
                    if (inputArr[0].equals("delete")) {
                        Task toDelete = taskList.get(taskNumber);
                        taskList.remove(taskNumber);
                        System.out.println(String.format("%s\nDave has removed the task:\n%s", horizontalLine, toDelete.toString()));
                        System.out.println(String.format("\nYou now have %d task(s).\n%s", taskList.size(), horizontalLine));
                    }
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                    Task newTask = new Task(input);
                    if (inputArr[0].equals("todo")) {
                        String taskName = input.substring(5);
                        newTask = new Todo(taskName);
                    }
                    if (inputArr[0].equals("deadline")) {
                        int idxDeadline = input.indexOf("/by");
                        String taskName = input.substring(9, idxDeadline - 1);
                        String deadline = input.substring(idxDeadline + "/by ".length());
                        newTask = new Deadline(taskName, deadline);
                    }
                    if (inputArr[0].equals("event")) {
                        // System.out.println(input);
                        int idxFrom = input.indexOf("/from");
                        int idxTo = input.indexOf("/to");
                        String taskName = input.substring(6, idxFrom - 1);
                        String from = input.substring(idxFrom + "/from ".length(), idxTo - 1);
                        String to = input.substring(idxTo + "/to ".length());
                        newTask = new Event(taskName, from, to);
                    }
                    taskList.add(newTask);
                    System.out.println(String.format("%s\nDave added the task:\n  %s\nYou now have %d task(s).\n%s", horizontalLine, newTask.toString(), taskList.size(), horizontalLine));
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException exc) {
                System.out.println(String.format("%s\n%s\n%s", horizontalLine, exc.getMessage(), horizontalLine));
            } catch (EmptyTaskException exc) {
                System.out.println(String.format("%s\n%s\n%s", horizontalLine, exc.getMessage(), horizontalLine));
            }
        }

        sc.close();
    }
}
