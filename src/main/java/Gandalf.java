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
            String[] splitInput = input.split(" "); //assume the number is at index 1, all other responses is at index 0
            if (splitInput[0].equals("bye")) {
                scanner.close();
                System.out.println("So here at last, comes the end of our fellowship. I will not say: Do not weep. For not all tears are an evil.");
                break;
            }
            //check for mark and unmarked
            if(splitInput[0].equals("mark") || splitInput[0].equals("unmark")){
                int taskNumber = Integer.parseInt(splitInput[1]);
                try{
                    list[taskNumber].getStatus();
                }
                catch(NullPointerException error){
                    System.out.println("There are no such tasks my old friend");
                    continue;
                }
                Task correspondingTask = list[taskNumber];
                if(splitInput[0].equals("mark")) {
                    correspondingTask.markStatus(true);
                }
                else{
                    correspondingTask.markStatus(false);
                }
                System.out.println("The task is done, humans truly are remarkable creatures");
                System.out.println(correspondingTask);
                continue;
            }
            if(splitInput[0].equals("list")){
                for(int i = 1; i <= numOfActions; i++){
                    Task action = list[i];
                    System.out.println(i + ". " + action);
                }
                continue;
            }
            //if reach this point assume task is new and does not exist in current array
            numOfActions++;
            Task currentTask = new Task(input);
            list[numOfActions] = currentTask;
            System.out.println("added new task: " + currentTask);
        }
    }
}
