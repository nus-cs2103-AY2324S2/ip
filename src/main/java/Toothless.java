import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Toothless {
    private static List<Task> listOfTasks = new ArrayList<>();
    private static String splitLine = "____________________________________________________________";
    private static String chatBotName = "Toothless";
    private static String greetingString = "Hello! I'm " + chatBotName + "!\n"
                            + "What can I do for you?\n" + splitLine;
    private static String exitString = "Bye. Hope to see you again soon!\n" + splitLine;

    public static void printTaskState(Task task, int index){
        System.out.format("%d. ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task + "\n", index + 1);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println(splitLine + "\n" + greetingString);

        while(true){
            command = sc.nextLine();
            System.out.println(splitLine);
            if (command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    Task t = listOfTasks.get(i);
                    printTaskState(t, i);
                }
            }
            else if(command.startsWith("unmark")){
                int taskIndex = command.charAt(7) - (int)'0' - 1;
                Task t = listOfTasks.get(taskIndex);
                t.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                printTaskState(t, taskIndex);
            }
            else if(command.startsWith("mark")){
                int taskIndex = command.charAt(5) - (int)'0' - 1;
                Task t = listOfTasks.get(taskIndex);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                printTaskState(t, taskIndex);
            }
            else if (command.startsWith("todo")){
                String description = command.substring(5);
                Task newTask = new Todo(description);
                listOfTasks.add(newTask);
                System.out.println("Got it. I've added this task:");
                printTaskState(newTask, listOfTasks.size() - 1);
                System.out.format("Now you have %d tasks in the list.\n", listOfTasks.size());
            }
            else if (command.startsWith("deadline")){
                String temp = command.substring(9);
                int dateIndex = temp.indexOf("/by");
                String description = temp.substring(0, dateIndex - 1);
                String date = temp.substring(dateIndex + 4);
                Task newTask = new Deadline(description, date);
                listOfTasks.add(newTask);
                System.out.println("Got it. I've added this task:");
                printTaskState(newTask, listOfTasks.size() - 1);
                System.out.format("Now you have %d tasks in the list.\n", listOfTasks.size());
            }
            else if (command.startsWith("event")){
                String temp = command.substring(6);
                int date1Index = temp.indexOf("/from");
                String description = temp.substring(0, date1Index - 1);
                temp = temp.substring(date1Index + 6);
                int date2Index = temp.indexOf("/to");
                String startDate = temp.substring(0, date2Index - 1);
                String endDate = temp.substring(date2Index + 4);
                Task newTask = new Event(description, startDate, endDate);
                listOfTasks.add(newTask);
                System.out.println("Got it. I've added this task:");
                printTaskState(newTask, listOfTasks.size() - 1);
                System.out.format("Now you have %d tasks in the list.\n", listOfTasks.size());
            }
            else if(command.equals("bye")){
                break;
            }

            System.out.println(splitLine);
        }

        System.out.println(exitString);
    }
}
