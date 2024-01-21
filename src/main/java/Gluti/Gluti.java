package Gluti;

import Gluti.utils.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Gluti {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        String logo = "____________________________________________________________\n" +
                " Hello! I'm Gluti\n" +
                " What can I do for you?\n";
        String end = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(logo);
        ArrayList<Task> storage = new ArrayList<>();
        String word = "";
        String function = word.split(" ")[0].toLowerCase();
        while(!function.equals("bye")) {
            word = sc.nextLine();
            String[] input = word.split(" ");
            function = input[0].toLowerCase();
            switch (function) {
                case "bye":
                    break;
                case "list":
                    int num = 1;
                    for (Task x : storage) {
                        System.out.println(String.valueOf(num++) + "." + x.toString());
                    }
                    break;
                case "mark":
                    int index = Integer.parseInt(input[1]);
                    storage.get(index - 1).setDone();
                    Task temp = storage.get(index - 1);
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            temp.toString() + "\n" +
                            "____________________________________________________________\n");
                    break;
                case "unmark":
                    index = Integer.parseInt(input[1]);
                    storage.get(index - 1).setunDone();
                    temp = storage.get(index - 1);
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            temp.toString() + "\n" +
                            "____________________________________________________________\n");
                    break;
                case "todo":
                    //todo
                    break;
                case "event":
                    //event
                    break;
                case "deadline":
                    //deadline
                    break;
                default:
                    storage.add(new Task(word));
                    System.out.println("____________________________________________________________\n" +
                            "added: " + word + "\n" +
                            "____________________________________________________________\n");
                    break;
            }
        }
        /*
            if (word.equals("list")) {

            } else if (word.contains("mark")) {

                String input[] = word.split(" ");
                int index = Integer.parseInt(input[1]);
                storage.get(index - 1).togglestatus();
                Task temp = storage.get(index - 1);
                if (!temp.getDoneness()) {
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            temp.toString() + "\n" +
                            "____________________________________________________________\n");
                } else {
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            temp.toString() + "\n" +
                            "____________________________________________________________\n");
                }

            } else if (word.equals("bye")) {
                continue;
            } else if (word.contains("todo")) {
                String input[] = word.split(" ");
            } else if (word.contains("deadline")) {
                String input[] = word.split(" ");
            } else if (word.contains("event")) {
                String input[] = word.split(" ");
            }
            else {
                storage.add(new Task(word));
                System.out.println("____________________________________________________________\n" +
                        "added: " + word + "\n" +
                        "____________________________________________________________\n");
            }
        }

         */
        System.out.println(end);
    }


}
