import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>(); // list to store tasks

        System.out.println("Hello! I'm Scribbles.");
        System.out.println("What can I do for you?\n");

        String input = sc.nextLine(); // takes in input from user
        while (!(input.equals("bye"))) {
            if (input.equals("list")) { // user inputs "list"
                int numOfTasks = list.size(); // finds the number of tasks in list currently
                int taskNumber = 1;
                while (numOfTasks != 0) {
                    System.out.println(taskNumber + ". " + list.get(taskNumber - 1));
                    taskNumber++;
                    numOfTasks--;
                }
                input = sc.nextLine();
            } else {
                list.add(input); // add input into list
                System.out.println("added:" + input);
                input = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // exits when user inputs bye
    }
}
