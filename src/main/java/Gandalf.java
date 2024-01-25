import java.util.Scanner;

public class Gandalf {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int numOfActions = 0;
        System.out.println("Through fire and shadow, I'm Gandalf");
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
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
                for(int i = 1; i <= numOfActions; i++){
                    Task action = list[i];
                    System.out.println(i + ". " + action);
                }
                continue;
            }
            if(taskInfo[0].equals("mark") || taskInfo[0].equals("unmark")){
                int taskNumber = Integer.parseInt(taskInfo[1]);
                try{
                    list[taskNumber].getStatus();
                }
                catch(NullPointerException error){
                    System.out.println("There are no such tasks my old friend");
                    continue;
                }
                Task correspondingTask = list[taskNumber];
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
            //if reach this point assume task is new and does not exist in current array and must further breakdown the array
            numOfActions++;
            if(taskInfo[0].equals("todo")){
                Task currentTask = new ToDos(taskInfo[1]);
                list[numOfActions] = currentTask;
                System.out.println("added new task: " + currentTask);
            }
            else if(taskInfo[0].equals("deadline")){
                Task currentTask = new Deadlines(taskInfo[1], splitInput[1]);
                list[numOfActions] = currentTask;
                System.out.println("added new task: " + currentTask);
            }
            else if(taskInfo[0].equals("event")){
                String startDate = splitInput[1].split(" ", 2)[1];
                String endDate = splitInput[2].split(" ",2)[1];
                Task currentTask = new Events(taskInfo[1], startDate, endDate);
                list[numOfActions] = currentTask;
                System.out.println("added new task: " + currentTask);
            }
            else{
                System.out.println("Apologies command not recognized");
            }
            System.out.println("Total number of tasks so far: " + numOfActions);
        }
    }
}
