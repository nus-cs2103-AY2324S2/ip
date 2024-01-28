import java.util.ArrayList;
import java.util.Scanner;

public class Gandalf {

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
        int numOfActions = -1; //follows 0 indexing
        System.out.println("Through fire and shadow, I'm Gandalf");
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.length() == 0){ //ignore accidental new lines from user
                continue;
            }
            //need to parse before checking for different commands
            //first split by "/"
            String[] splitInput = input.split("/"); //to separate dates for deadline and event
            //further parse index 0 into task type and task name
            String[] taskInfo = splitInput[0].trim().split(" ", 2); //by right is only size 2, index 0 is task type, 1 is task name
            //check for bye or list or mark or unmarked
            if (taskInfo[0].equals("bye")) {
                scanner.close();
                System.out.println("So here at last, comes the end of our fellowship. I will not say: Do not weep. For not all tears are an evil.");
                break;
            }
            if(taskInfo[0].equals("list")){
                for(int i = 0; i < faster_list.size(); i++){
                    Task action = faster_list.get(i);
                    System.out.println((i + 1) + ". " + action);
                }
                System.out.println("Total number of tasks so far: " + (numOfActions + 1));
                continue;
            }
            try{
                checkCommand(taskInfo, faster_list);
            }
            catch(GandalfException e){
                System.out.println(e.getMessage());
                continue;
            }
            if(taskInfo[0].equals("delete")){
                int deleteNumber = Integer.parseInt(taskInfo[1]);
                System.out.println("removed task: " + faster_list.get(deleteNumber - 1));
                faster_list.remove(deleteNumber - 1);
                numOfActions--;
                System.out.println("Total number of tasks so far: " + (numOfActions + 1));
                continue;
            }
            if(taskInfo[0].equals("mark") || taskInfo[0].equals("unmark")){
                int taskNumber = Integer.parseInt(taskInfo[1]);
                Task correspondingTask = faster_list.get(taskNumber - 1);
                if(taskInfo[0].equals("mark")) {
                    correspondingTask.markStatus(true);
                    System.out.println("The task is done, humans truly are remarkable creatures");
                }
                else{
                    correspondingTask.markStatus(false);
                    System.out.println("The task is undone, fret not, for it is not about how much you've missed but about how much you've done.");
                }
                System.out.println(correspondingTask);
                continue;
            }
            //if reach this point assume task is new (and recognized) and does not exist in current array and must further breakdown the array
            numOfActions++;
            if(taskInfo[0].equals("todo")){
                Task currentTask = new ToDos(taskInfo[1]);
                faster_list.add(currentTask);
                System.out.println("added new task: " + currentTask);
            }
            else if(taskInfo[0].equals("deadline")){
                Task currentTask = new Deadlines(taskInfo[1], splitInput[1]);
                faster_list.add(currentTask);
                System.out.println("added new task: " + currentTask);
            }
            else if(taskInfo[0].equals("event")){
                String startDate = splitInput[1].split(" ", 2)[1];
                String endDate = splitInput[2].split(" ",2)[1];
                Task currentTask = new Events(taskInfo[1], startDate, endDate);
                faster_list.add(currentTask);
                System.out.println("added new task: " + currentTask);
            }
            System.out.println("Total number of tasks so far: " + (numOfActions + 1));
        }
    }
}
