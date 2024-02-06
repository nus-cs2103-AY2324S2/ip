import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Gandalf {
    /**
     * Writes the contents of an ArrayList of Task objects to a text file.
     * Creates the file if it does not exist in the filepath
     * @param list
     */
    public static void writeToFile(ArrayList<Task> list){
        File docsFolder = new File("docs");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }
        String filePath = "docs/gandalfData.txt";
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for(int i = 0; i < list.size(); i++){
                Task action = list.get(i);
                writer.write((i + 1) + ". " + action);
                writer.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks and validates a command based on the provided task information and the existing task data.
     * Throws a GandalfException with an appropriate message if the command is invalid.
     * @param taskInfo
     * @param data
     * @throws GandalfException
     */
    public static void checkCommand(String[] taskInfo, ArrayList<Task> data) throws GandalfException{
        if(!taskInfo[0].equals("todo") && !taskInfo[0].equals("deadline") && !taskInfo[0].equals("event") && !taskInfo[0].equals("delete") && !taskInfo[0].equals("mark")&& !taskInfo[0].equals("unmark")){
            throw new GandalfException("Please forgive me for I do not understand. They are spoken in a tongue lost in time.");
        }
        else if(taskInfo[0].equals("mark") || taskInfo[0].equals("unmark") || taskInfo[0].equals("delete")){
            try{
                data.get(Integer.parseInt(taskInfo[1]) - 1);
            }
            catch (IndexOutOfBoundsException e){
                throw new GandalfException("There are no such tasks my old friend");
            }
        }
        else if(taskInfo.length == 1){
            throw new GandalfException("I cannot add " + taskInfo[0] + " without a description");
        }
    }
    public static void main(String[] args) {
        ArrayList<Task> faster_list = new ArrayList<>(100);
        writeToFile(faster_list);
        int numOfActions = -1; //follows 0 indexing
        System.out.println("Through fire and shadow, I'm Gandalf");
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            StringBuilder taskType = new StringBuilder();
            StringBuilder taskName = new StringBuilder();
            StringBuilder date1 = new StringBuilder();
            StringBuilder date2 = new StringBuilder();
            StringBuilder[] parsedInput = {taskType, taskName, date1, date2};
            int curr_info = 0;
            if(input.length() == 0){ //ignore accidental new lines from user
                continue;
            }
            for(int i = 0; i < input.length(); i++){
                char curr_char = input.charAt(i);
                if(curr_char == ' '){
                    if(input.charAt(i - 1) == ' '){
                        curr_info++;
                        continue;
                    }
                }
                parsedInput[curr_info].append(curr_char);
            }
            if (taskType.toString().equals("bye")) {
                scanner.close();
                System.out.println("So here at last, comes the end of our fellowship. I will not say: Do not weep. For not all tears are an evil.");
                break;
            }
            if(taskType.toString().equals("list")){
                for(int i = 0; i < faster_list.size(); i++){
                    Task action = faster_list.get(i);
                    System.out.println((i + 1) + ". " + action);
                }
                System.out.println("Total number of tasks so far: " + (numOfActions + 1));
                continue;
            }
            if(taskType.toString().trim().equals("delete")){
                int deleteNumber = Integer.parseInt(taskName.toString());
                System.out.println("removed task: " + faster_list.get(deleteNumber - 1));
                faster_list.remove(deleteNumber - 1);
                numOfActions--;
                System.out.println("Total number of tasks so far: " + (numOfActions + 1));
                writeToFile(faster_list);
                continue;
            }
            if(taskType.toString().trim().equals("mark") || taskType.toString().trim().equals("unmark")){
                int taskNumber = Integer.parseInt(taskName.toString());
                Task correspondingTask = faster_list.get(taskNumber - 1);
                if(taskType.toString().trim().equals("mark")) {
                    correspondingTask.markStatus(true);
                    System.out.println("The task is done, humans truly are remarkable creatures");
                }
                else{
                    correspondingTask.markStatus(false);
                    System.out.println("The task is undone, fret not, for it is not about how much you've missed but about how much you've done.");
                }
                writeToFile(faster_list);
                System.out.println(correspondingTask);
                continue;
            }
            //if reach this point assume task is new (and recognized) and does not exist in current array and must further breakdown the array
            numOfActions++;
            if(taskType.toString().trim().equals("todo")){
                Task currentTask = new ToDos(taskName.toString().trim());
                faster_list.add(currentTask);
                writeToFile(faster_list);
                System.out.println("added new task: " + currentTask);
            }
            else if(taskType.toString().trim().equals("deadline")){
                System.out.println(date1.toString().trim());
                Task currentTask = new Deadlines(taskName.toString().trim(), date1.toString().trim());
                faster_list.add(currentTask);
                writeToFile(faster_list);
                System.out.println("added new task: " + currentTask);
            }
            else if(taskType.toString().trim().equals("event")){
                Task currentTask = new Events(taskName.toString().trim(), date1.toString().trim(), date2.toString().trim());
                faster_list.add(currentTask);
                writeToFile(faster_list);
                System.out.println("added new task: " + currentTask);
            }
            System.out.println("Total number of tasks so far: " + (numOfActions + 1));
        }
    }
}
