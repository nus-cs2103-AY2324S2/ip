import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Welcome!! I'm Belle <3.");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        boolean exit = false;
        String input = "";
        ArrayList<Task> list = new ArrayList<>();
        Task curr;

        while (!exit) {
            input = sc.nextLine();
            String[] inputlist = input.split(" ");
            curr = new Task(input, false);
            if (input.equals("bye")) {
                exit = true;
                break;
            }
            if (input.equals("list")) {
                System.out.println("--------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.valueOf(i+1) + ".[" + list.get(i).isDone()
                            + "] " + list.get(i).getName());
                }
                System.out.println("--------------------------");
                continue;
            }
            if (inputlist[0].equals("mark")) {
                String index = inputlist[1];
                Task doingtask = list.get(Integer.valueOf(index)-1);
                doingtask.dotask();
                System.out.println("--------------------------");
                System.out.println("Nice! I have marked this task as done:");
                System.out.println("[X] "+ doingtask.getName());
                System.out.println("--------------------------");
                continue;
            }
            if (inputlist[0].equals("unmark")) {
                String index = inputlist[1];
                Task doingtask = list.get(Integer.valueOf(index)-1);
                doingtask.undotask();
                System.out.println("--------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[] "+ doingtask.getName());
                System.out.println("--------------------------");
                continue;
            }


            System.out.println("--------------------------");
            System.out.println("added: " + curr.getName());
            System.out.println("--------------------------");
            list.add(curr);

        }

        System.out.println("--------------------------");
        System.out.println("Till next time!! Goodbye.");
        System.out.println("--------------------------");

    }
}
