import java.util.Scanner;

public class Duke {
    // print list of stored tasks
    //Chatbot stores user commands in a fixed array
    public static Task[] listStore = new Task[100];
    public static int listCount = 0;
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

    //function to delete task and move remaining tasks up in the list
    public static void deleteTask(Task[] listStore, int taskNum) {
        System.out.println("Noted. I've removed this task:\n" + listStore[taskNum - 1].toString());
        for (int i = taskNum - 1; i < listCount - 1; i++) {
            listStore[i] = listStore[i + 1];
        }
        listCount--;
        System.out.println("Now you have " + listCount + " tasks in the list.\n");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");

        //Chatbot echos user commands
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
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
                } else if (input.startsWith("delete")) { //delete specified task
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]);
                    deleteTask(listStore, taskNum);
                } else {
                    // add user inputs to list based on what task it is accordingly
                    Task newTask = null;
                    if (input.startsWith("todo")) {
                        if (input.length() < 5) {
                            throw new Exception("ChatterOOHNOO! A todOO's description cannot be empty!");
                        }
                        input = input.substring(5);
                        newTask = new ToDo(input);
                    } else if (input.startsWith("deadline")) {
                        if (input.length() < 9) {
                            throw new Exception("ChatterOOHNOO! A deadline's description cannot be empty!");
                        }
                        String[] inputArr = input.substring(9).split("/by");
                        input = inputArr[0];
                        String by = inputArr[1];
                        newTask = new Deadline(input, by);
                    } else if (input.startsWith("event")) {
                        if (input.length() < 6) {
                            throw new Exception("ChatterOOHNOO! An event's description cannot be empty!");
                        }
                        input = input.substring(6);
                        String[] inputArr = input.split("/from");
                        input = inputArr[0];
                        String[] timeArr = inputArr[1].split("/to");
                        String from = timeArr[0];
                        String to = timeArr[1];
                        newTask = new Event(input, from, to);
                    } else {
                        throw new Exception("ChatterOOHNOO! Chatteroo doesn't understand what yoo mean!");
                    }
                    if (newTask != null) {
                        listStore[listCount] = newTask;
                        listCount++;
                        System.out.println("Got it. I've added this task:\n" + newTask.toString());
                        if (listCount == 1) {
                            System.out.println("Now you have " + listCount + " task in the list.\n");
                        } else
                            System.out.println("Now you have " + listCount + " tasks in the list.\n");
                    }
                }
//            System.out.println("added: " + input + "\n");
                input = sc.nextLine();
            } catch (IllegalArgumentException e) {
                throw new Exception("ChatterOOHNOO! Chatteroo doesn't understand what yoo mean!");
            }

        }
        //Chatbot exits
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
