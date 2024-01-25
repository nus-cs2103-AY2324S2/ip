import java.util.ArrayList;
import java.util.Scanner;
public class Blawg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>(100);
        String line = "____________________________________________________________\n";
        String intro = line +
                " Paws what you're doing! I'm Blawg\n" +
                " What can I do for you?\n" +
                line;
        System.out.println(intro);
        String outro = line +
                " Alright, this kitty's got to go chase some shadows. See you later!\n" +
                line;
        while (true) {
            String userInput = sc.next();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(outro);
                break;
            } else if (userInput.equalsIgnoreCase("list")){
                StringBuilder output = new StringBuilder();
                output.append(line);
                for (int i = 0; i < taskList.size(); i++) {
                    output.append(i+1).append(".");
                    output.append(taskList.get(i)).append("\n");
                }
                output.append(line);
                System.out.println(output);
            } else if (userInput.equalsIgnoreCase("mark")) {
                while (true) {
                    int num = sc.nextInt();
                    if (num > taskList.size() || num < 0) {
                        System.out.println("Please enter a valid number.");
                    } else {
                        taskList.get(num - 1).markDone();
                        String output = line + "Nice! I've marked this task as done: \n" +
                                taskList.get(num - 1) + line;
                        System.out.println(output);
                        break;
                    }
                }
            } else if (userInput.equalsIgnoreCase("unmark")) {
                while (true) {
                    int num = sc.nextInt();
                    if (num > taskList.size() + 1 || num < 0) {
                        System.out.println("Please enter a valid number.");
                    } else {
                        taskList.get(num - 1).unmarkDone();
                        String output = line + "OK, I've marked this task as not done yet: \n" +
                                taskList.get(num - 1) + line;
                        System.out.println(output);
                        break;
                    }
                }
            } else {
                System.out.println("____________________________________________________________\n" +
                        "added: " + userInput + "\n" +
                        "____________________________________________________________\n");
                taskList.add(new Task(userInput));
            }
        }
        sc.close();
    }
}
