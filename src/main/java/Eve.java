import java.util.*;

public class Eve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Hello! I'm Eve");
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<Task> list = new ArrayList<>();

        while(!input.equals("bye")){
            input = sc.nextLine();

            String tempyArr[] = input.split(" ",2);
            String commandCheck = tempyArr[0];
    

            if (commandCheck.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon !");
            } else if (commandCheck.equals("list")) {
                for (int i = 0; i < list.size(); i++ ){
                    int j = i + 1;
                    Task temp = list.get(i);
                    System.out.println(j + "." + temp.toString());
                }
                
            } else if (commandCheck.equals("mark")) {
                int index = Integer.parseInt(tempyArr[1]) - 1 ;
                Task temp = list.get(index);
                temp.markAsDone();
                System.out.println(" Nice! I've marked this task as done: ");
                System.out.println(temp.toString());
                
            } else if (commandCheck.equals("unmark")) {
                 int index = Integer.parseInt(tempyArr[1]) - 1 ;
                 Task temp = list.get(index);
                temp.markAsNotDone();
                System.out.println(" Nice! I've marked this task as not done yet: ");
                 System.out.println(temp.toString());
            
            } else {

                String description = tempyArr[1];
                if (commandCheck.equals("todo")){
                    Task t = new Todo(description);
                    list.add(t);

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + list.size() +" tasks in the list.");
                } else if (commandCheck.equals("deadline")){
                    String arrTemp[] = description.split(" /by ");
                    description = arrTemp[0];
                    String by = arrTemp[1];
                    Task t = new Deadline(description, by);
                    list.add(t);

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + list.size() +" tasks in the list.");
                } else if (commandCheck.equals("event")) {
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
        }
            

        sc.close();
    }
}
