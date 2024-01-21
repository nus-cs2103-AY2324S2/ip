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
        Task[] taskList = new Task[100];
        int listIdx = 0;

        // user input
        while (!input.equals(exitCmd)) {
            if (input.equals("list")) {
                System.out.println(horizontalLine + "\nHere are the tasks in your list:\n");
                for (int i = 0; i < listIdx; i++) {
                    System.out.println(String.format("%d. %s", i+1, taskList[i].toString()));
                }
                System.out.println(horizontalLine);
            } else {
                String[] inputArr = input.split(" ");
                if (inputArr[0].equals("mark") || inputArr[0].equals("unmark")) {
                    int taskNumber = Integer.parseInt(inputArr[1]) - 1;

                    if (inputArr[0].equals("mark")) {
                        taskList[taskNumber].isCompleted();
                        System.out.println(String.format("%s\nGood job! I'll mark this task as done:\n%s\n%s", horizontalLine, taskList[taskNumber].toString(), horizontalLine));    
                    } else {
                        taskList[taskNumber].isNotCompleted();
                        System.out.println(String.format("%s\nAlright, I believe you'll get this done eventually:\n%s\n%s", horizontalLine, taskList[taskNumber].toString(), horizontalLine));
                    }
                } else {
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
                        int idxFrom = input.indexOf("/from");
                        int idxTo = input.indexOf("/to");
                        String taskName = input.substring(6, idxFrom - 1);
                        String from = input.substring(idxFrom + "/from ".length(), idxTo - 1);
                        String to = input.substring(idxTo + "/to ".length());
                        newTask = new Event(taskName, from, to);
                    }
                    // Task newTask = new Task(input);
                    taskList[listIdx] = newTask;
                    listIdx++;
                    System.out.println(String.format("%s\nDave added the task: \n  %s\nYou now have %d task(s).\n%s", horizontalLine, newTask.toString(), listIdx, horizontalLine));
                }
            }
            input = sc.nextLine();

        }

        // end program if user says bye
        System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s", horizontalLine, horizontalLine));

        sc.close();
    }
}
