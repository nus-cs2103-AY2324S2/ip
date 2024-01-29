import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Area.\n" +
                "What can I do for you?\n"
        );

        Scanner sc = new Scanner(System.in);
        Task[] storage = new Task[100];
        int count = 0;
        while(true){
            String instruction = sc.nextLine();
            if(instruction.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if(instruction.equals("list")){
                for(int i=0;i<count;i++){
                        System.out.println(i + 1 + "." + storage[i].toString());
                }
                System.out.println();
            }else if(instruction.contains("unmark")){
                String[] arr = instruction.split(" ");
                int num = Integer.parseInt(arr[1]) - 1;
                storage[num].taskUndone();
                System.out.println("OK, I've marked this task as not done yet:\n " +
                        storage[num].toString());
            }else if(instruction.contains("mark")){
                String[] arr = instruction.split(" ");
                int num = Integer.parseInt(arr[1]) - 1;
                storage[num].taskDone();
                System.out.println("Nice! I've marked this task as done:\n " +
                        storage[num].toString());
            }else if(instruction.contains("deadline")){
                String[] arr = instruction.split(" /by ");
                storage[count] = new Deadline(arr[0].substring(9),arr[1]);
                count++;
                System.out.println("Got it. I've added this task:\n" + storage[count - 1].toString()
                        + "\n" + "Now you have " + count + " tasks to the list" + "\n");
            }else if(instruction.contains("todo")){
                storage[count] = new Todo(instruction.substring(5));
                count++;
                System.out.println("Got it. I've added this task:\n" + storage[count - 1].toString()
                        + "\n" + "Now you have " + count + " tasks to the list" + "\n");
            }else if(instruction.contains("event")){
                String[]arr = instruction.split(" /from ");
                String[]arr1 = arr[1].split(" /to ");
                storage[count] = new Event(arr[0].substring(6),arr1[0],arr1[1]);
                count++;
                System.out.println("Got it. I've added this task:\n" + storage[count - 1].toString()
                        + "\n" + "Now you have " + count + " tasks to the list" + "\n");
            }else{
               storage[count] = new Task(instruction);
               count++;
               System.out.println("added: " + instruction + "\n");
            }
        }
    }

}
