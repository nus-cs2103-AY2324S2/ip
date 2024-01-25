import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jojo :)");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] listArr = new Task[100];
        String userTxt = sc.nextLine();
        int i = 0;
        while (!userTxt.equals("bye")) {
            if (userTxt.equals("list")) {
                for (int j = 0; j < 100; j++) { // printing out all items in the list
                    if (listArr[j] == null) { // for early termination
                        break;
                    } else {
                        System.out.println(j + 1 + ". " + listArr[j]);
                    }
                }
            } else {
//                System.out.println("added: " + userTxt);
                Task t = new Task(userTxt);
                t.toString();
                listArr[i] = t; // add task to list
                i++;
            }
            userTxt = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
