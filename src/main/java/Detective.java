import java.util.Objects;
import java.util.Scanner;

public class Detective {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Detective";
        String line = "____________________________________________________________";
        Task[] myList = new Task[100];
        int count = 0;

        System.out.println(line + "\nHello! I'm [" + name + "]" + "\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < 100; i++) {
                    if (myList[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + "." + myList[i].getStatusIcon() + myList[i].name);
                }
                System.out.println(line);
                input = sc.nextLine();
            } else {
                String[] inputContent = input.split(" ");
                if (Objects.equals(inputContent[0], "mark") && inputContent.length == 2) {
                    int taskNum = Integer.parseInt(inputContent[1]) - 1;
                    myList[taskNum].markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(myList[taskNum].getStatusIcon() + myList[taskNum].name);
                    System.out.println(line);
                    input = sc.nextLine();
                } else if (Objects.equals(inputContent[0], "unmark") && inputContent.length == 2) {
                    int taskNum = Integer.parseInt(inputContent[1]) - 1;
                    myList[taskNum].markAsNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(myList[taskNum].getStatusIcon() + myList[taskNum].name);
                    System.out.println(line);
                    input = sc.nextLine();
                } else { //add tasks
                    if (count >= 100) {
                        System.out.println(line + "\n" + "The list is full, add failed");
                        input = sc.nextLine();
                    } else {
                        myList[count++] = new Task(input);
                        System.out.println(line + "\n" + "added: " + input + "\n" + line);
                        input = sc.nextLine();
                    }
                }
            }
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}