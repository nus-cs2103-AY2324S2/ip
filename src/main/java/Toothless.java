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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println(splitLine + "\n" + greetingString);

        while(true){
            command = sc.nextLine();
            System.out.println(splitLine);
            if (command.equals("list")){
                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.format("%d. " + listOfTasks.get(i) + "\n", i + 1);
                }
            } else if(command.equals("bye")){
                break;
            } else {
                Task newTask = new Task(command);
                listOfTasks.add(newTask);
                System.out.println("added: " + command);
            }
            System.out.println(splitLine);
        }

        System.out.println(exitString);
    }
}
