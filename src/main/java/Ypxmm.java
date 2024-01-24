import java.util.*;

public class Ypxmm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>(); //tasks
        System.out.println("Hello, I'm Ypxmm.");
        System.out.println("Need me do what for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Oh you need zao alr? Okok see you next time!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Ok wait ah, here are your tasks:");
                int count = 1;
                for (Task t : tasks) {
                    System.out.println(count + ". " + t.toString());
                    count++;
                }
            } else if (input.startsWith("mark")) {
                String[] vals = input.split(" ");
                int index = Integer.parseInt(vals[1]);
                tasks.get(index - 1).markTask();
            } else if (input.startsWith("unmark")) {
                String[] vals = input.split(" ");
                int index = Integer.parseInt(vals[1]);
                tasks.get(index - 1).unmarkTask();
            } else {
                tasks.add(new Task(input));
                System.out.println(input + " added liao");
            }
        }
        sc.close();
    }
}
