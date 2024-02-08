package eve;
import java.io.IOException;

import eve.exceptions.EveExceptions;
import eve.fileStorage.Storage;
import java.util.*;
import eve.tasks.Task;


public class Commands {

    public static void commandHello() {
        System.out.println(" Hello! I'm Eve");
        System.out.println(" What can I do for you?");
    }
    public static void commandBye(ArrayList<Task> tasks){
        System.out.println(" Bye. Hope to see you again soon !");
        try{
            Storage.writeToFile(tasks);
            } catch (IOException e){
                System.out.println("hi");
            }
    }

    public static void commandListener() {
        Scanner sc = new Scanner(System.in);

        String input = "";
        ArrayList<Task> list = new ArrayList<>();
        // Storage storeFile = new Storage();

        try{
            Storage.loadFileContents(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // refactor into cases
        while(!input.equals("bye")){
            input = sc.nextLine();

            String[] tempyArr = input.split(" ",2);
            String commandCheck = tempyArr[0];
            try {
                switch(commandCheck){

                case "bye":
                    Commands.commandBye(list);
                    break;
                case "list":
                    TaskList.commandList(list);
                    break;
                case "mark":
                    TaskList.commandMark(tempyArr, list);
                    break;
                case "unmark":
                    TaskList.commandUnMark(tempyArr, list);
                    break;
                case "delete":
                    TaskList.commandDelete(tempyArr, list);
                    break;
                case "todo":        
                    TaskList.commandTodo(tempyArr, list);
                    break;
                case "deadline":    
                    TaskList.commandDeadline(tempyArr, list);
                    break;
                case "event":
                    TaskList.commandEvent(tempyArr, list);
                    break;

                case "find":
                    TaskList.commandFind(tempyArr, list);
                    break;

                default:
                    throw new EveExceptions("OOPS!!! I'm sorry, but I don't know what that means, please try again");

                }
            } catch (EveExceptions e) {
                System.out.println(e.getMessage());
            }
                
        }

            
        
     

        sc.close();
    }

    
}
