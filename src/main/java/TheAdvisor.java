import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheAdvisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String intro = "Hello, I am The Advisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?";
        System.out.println(intro + "\n");

        // An ArrayList that stores the tasks to be done
        ArrayList<Task> output = new ArrayList<>();

        while (true) {
            String str = br.readLine();
            String[] strings = str.split(" ");
            if (str.equals("bye")) {
                System.out.println("     Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                        "to help you in your investing journey!");
                break;
            } else if (str.equals("list")){
                int counter = 1;
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < output.size(); i++) {
                    System.out.println("     " + counter + ". " + output.get(i).toString());
                    counter++;
                }
            } else if (strings[0].equals("mark")) {
                // 1-based indexing on input
                int number = Integer.parseInt(strings[1]);
                Task temp = output.get(number - 1);
                temp.markDone();
                System.out.println("     Nice! I've marked this task as done:\n" + "       " +
                        temp.toString());
            } else if (strings[0].equals("unmark")) {
                // 1-based indexing on input
                int number = Integer.parseInt(strings[1]);
                Task temp = output.get(number - 1);
                temp.unmark();
                System.out.println("     OK, I've marked this task as not done yet:\n" + "       " +
                        temp.toString());
            } else {
                Task task = new Task(str);
                output.add(task);
                System.out.println("    added: " + str);
            }
        }
    }
}
