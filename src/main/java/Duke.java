import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String line = "------------------------------";
        Scanner obj = new Scanner(System.in);
        Task[] lst = new Task[100];
        int i = 0;
        System.out.println("\n Hello! I'm Leo\n" +
                " What can I do for you?");
        System.out.println(line);
        while (obj.hasNextLine()) {
            String res = obj.nextLine();
            if (res.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            }
            String[] key = res.split(" ", 2);
            switch (key[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list!");
                    for (int j = 0; j < i; j++) {
                        int nr = j + 1;
                        System.out.println(nr + ". [" + lst[j].getStatusIcon() + "]" + lst[j].description);
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                case "mark":
                    for(Task task:lst){
                        if (task != null){
                            String str = task.description;
                            if (str.equals(key[1])) {
                                task.markAsDone();
                            }
                        }
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + key[1]);
                    break;

                case "unmark":
                    for(Task task:lst){
                        if(task != null) {
                            String str = task.description;
                            if (str.equals(key[1])) {
                                task.unMark();
                            }
                        }
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + key[1] );
                    break;


                default:
                    Task item = new Task(res);
                    lst[i] = item;
                    System.out.println("added:" + res);
                    i++;


                    System.out.println(line);
            }

        }
    }
}
