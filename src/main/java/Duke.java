import java.util.Scanner;

public class Duke {
    // print list of stored tasks
    public static void printTasks(Task[] listStore, int listCount) {
        for (int i = 0; i < listCount; i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + ". " + listStore[i]);
        }
        System.out.println();
    }

    // function to mark task as done and print it
    public static void markTaskAsDone(Task[] listStore, int taskNum) {
        listStore[taskNum - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + listStore[taskNum - 1].toString() + "\n");
    }

    //function to mark task as not done and print it
    public static void markTaskAsNotDone(Task[] listStore, int taskNum) {
        listStore[taskNum - 1].markAsNotdone();
        System.out.println("OK, I've marked this task as not done yet:\n" + listStore[taskNum - 1].toString() + "\n");
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        //Chatbot greets user
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");

        //Chatbot stores user commands in a fixed array
        Task[] listStore = new Task[100];
        int listCount = 0;

        //Chatbot echos user commands
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            // print list of stored tasks if user inputs "list"
            if (input.equals("list")) {
                printTasks(listStore, listCount);
            } else if (input.startsWith("mark")) { // mark task as done
                String[] inputArr = input.split(" ");
                int taskNum = Integer.parseInt(inputArr[1]); //retrieve task number from input array
                markTaskAsDone(listStore, taskNum);
            } else if (input.startsWith("unmark")) { // mark task as not done
                String[] inputArr = input.split(" ");
                int taskNum = Integer.parseInt(inputArr[1]); //retrieve task number from input array
                markTaskAsNotDone(listStore, taskNum);
            } else { // add user inputs to list
                listStore[listCount] = new Task(input);
                listCount++;
                System.out.println("added: " + input + "\n");
            }
//            System.out.println("added: " + input + "\n");
            input = sc.nextLine();
        }
        //Chatbot exits
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
