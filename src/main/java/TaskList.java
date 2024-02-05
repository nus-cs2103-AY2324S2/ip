import java.util.ArrayList;

import Exceptions.EveExceptions;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class TaskList {
    public static void commandList(ArrayList<Task> list){
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++ ){
            int j = i + 1;
            Task temp = list.get(i);
            System.out.println(j + "." + temp.toString());
        }
    }

    
    public static void commandMark(String[] tempyArr, ArrayList<Task> list){
        int index = Integer.parseInt(tempyArr[1]) - 1 ;
        Task temp = list.get(index);
        temp.markAsDone();
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println(temp.toString());
                
    }

    public static void commandUnMark(String[] tempyArr, ArrayList<Task> list){
        int index = Integer.parseInt(tempyArr[1]) - 1 ;
        Task temp = list.get(index);
        temp.markAsNotDone();
        System.out.println(" Nice! I've marked this task as not done yet: ");
        System.out.println(temp.toString());
                
    }

    public static void commandDelete(String[] tempyArr, ArrayList<Task> list){
        int index = Integer.parseInt(tempyArr[1]) - 1 ;
        Task temp = list.get(index);
        list.remove(temp);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(temp.toString());
        System.out.println("Now you have " + list.size() +" tasks in the list.");       
    }

    public static void commandTodo (String[] tempyArr, ArrayList<Task> list) throws EveExceptions {
        
        if (tempyArr.length < 2) {
            throw new EveExceptions("This todo can't be empty");
        }
        String description = tempyArr[1];
        Task t = new Todo(description);
        list.add(t);

        System.out.println("Got it. I've added this task: ");
        System.out.println(t.toString());
        System.out.println("Now you have " + list.size() +" tasks in the list.");
    }

    public static void commandDeadline(String[] tempyArr, ArrayList<Task> list){
        String description = tempyArr[1];
        String arrTemp[] = description.split(" /by ");
        description = arrTemp[0];
        String by = arrTemp[1];
        Task t = new Deadline(description, by);
        list.add(t);

        System.out.println("Got it. I've added this task: ");
        System.out.println(t.toString());
        System.out.println("Now you have " + list.size() +" tasks in the list.");
    }

    public static void commandEvent(String[] tempyArr, ArrayList<Task> list){
        String description = tempyArr[1];
        String arrTemp[] = description.split(" /from ");
        description = arrTemp[0];
        String dateArr[] = arrTemp[1].split(" /to ");
        String startDate = dateArr[0];
        String endDate = dateArr[1];
        Task t = new Event(description, startDate, endDate);
        list.add(t);
        
        System.out.println("Got it. I've added this task: ");
        System.out.println(t.toString());
        System.out.println("Now you have " + list.size() +" tasks in the list.");
    }
}
