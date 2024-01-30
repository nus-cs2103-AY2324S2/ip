import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void intro() {
        System.out.println("Hello, I am The Advisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                " to help you in your investing journey!");
        System.exit(0);
    }

    public String getUserInput() throws IOException {
        return br.readLine();
    }

    public void printList(ArrayList<Task> taskList) {
        int counter = 1;
        if (taskList.size() == 0) {
            System.out.println("     Sorry, there are no tasks in your list. Start adding them :)");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("     " + counter + ". " + taskList.get(i).toString());
                counter++;
            }
        }
    }
}
