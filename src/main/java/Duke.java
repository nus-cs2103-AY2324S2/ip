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
            if(key.length <= 1){
                System.out.println("Say something valid please -_-");
                System.out.println(line);
                continue;
            }
            switch (key[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list!");
                    for (int j = 0; j < i; j++) {
                        int nr = j + 1;
                        System.out.println(nr + lst[j].toString()+ ".");
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
                case "todo":
                    Task item = new ToDo(key[1]);
                    lst[i] = item;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(item.toString());
                    i++;
                    System.out.println("Now you have " + i + " task(s) in your list!");
                    System.out.println(line);
                    break;
                case "deadline":
                    String[] by = key[1].split("/", 2);
                    Task dline = new Deadline(by[0], by[1]);
                    lst[i] = dline;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(dline.toString());
                    i++;
                    System.out.println("Now you have " + i + " task(s) in your list!");
                    System.out.println(line);
                    break;
                case "event":
                    String[] fromto = key[1].split("/", 3);
                    Task e = new Event(fromto[0], fromto[1], fromto[2]);
                    lst[i] = e;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e.toString());
                    i++;
                    System.out.println("Now you have " + i + " task(s) in your list!");
                    System.out.println(line);
                    break;
                case "delete":
                    int toDelete = Integer.valueOf(key[1]) -1;
                    if(toDelete >=0 && toDelete< i){
                        Task taskToDelete = lst[toDelete];
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskToDelete.toString());
                        for(int p = toDelete; p< i; p++){
                            lst[p] = lst[p+1];

                        }
                        lst[i - 1] = null;
                        i--;
                        System.out.println("Now you have " + i + " task(s) in the list.");
                    } else {
                        System.out.println("That is an invalid task to delete sir??");
                    }
                    System.out.println(line);
                    break;

                default:
                    System.out.println("Sorry what??, I did not get that!");


            }

        }
    }
}
