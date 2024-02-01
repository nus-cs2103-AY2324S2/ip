import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Cro {

    static TaskList taskList;
    static String welcomeMessage = "-----------------------------------\n"
                            + "Hello! I'm Cro!\n"
                            + "What can I do for you?\n"
                            + "-----------------------------------\n";
    public static void main(String[] args){

        try {
            File saveData = new File("saveFile.txt");
            if (saveData.createNewFile()) {
                System.out.println("New save file created");
            } else {
                System.out.println("Save file exists. Loading...");
            }
            taskList = new TaskList(saveData);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String inText = sc.nextLine();
            List<String> splitStr = Arrays.asList(inText.trim().split("\\s+"));
            String command = splitStr.get(0);
            try {
                if (command.equals("bye")) {
                    System.out.println("-----------------------------------");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("-----------------------------------");
                    break;
                } else if (command.equals("list")) {
                    taskList.displayTasks();
                } else if (command.equals("mark")) {
                    taskList.markTaskAsDone(splitStr);
                } else if (command.equals("unmark")) {
                    taskList.markTaskAsUndone(splitStr);
                } else if (command.equals("todo")) {
                    taskList.addToDo(splitStr, 0);
                } else if (command.equals("deadline")) {
                    taskList.addDeadline(splitStr, 0);
                } else if (command.equals("event")) {
                    taskList.addEvent(splitStr, 0);
                } else if (command.equals("delete")) {
                    taskList.deleteEvent(splitStr);
                } else {
                    throw new CroException("Unknown command. Please try again.");
                }
                taskList.updateSave();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
