package Gluti;

import Gluti.helpers.FileStorage;
import Gluti.utils.Task;
import Gluti.utils.Todo;
import Gluti.utils.Deadline;
import Gluti.utils.Event;
import Gluti.utils.GlutiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gluti {
    public static void main(String[] args) throws GlutiException, IOException {
        FileStorage fStorage = new FileStorage();
        ArrayList<Task> storage = fStorage.readList();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        String logo = " Hello! I'm Gluti\n" +
                " What can I do for you?";
        String end = " Bye. Hope to see you again soon!";
        System.out.println(logo);
        //ArrayList<Task> storage = new ArrayList<>();
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
                System.out.println(storage.size());
                if (!storage.isEmpty()) {
                    for (Task x : storage) {
                        System.out.println(num++ + "." + x.toString());
                    }
                } else {
                    System.out.println("List is Empty!");
                }
                break;
            case "mark":
                int index = Integer.parseInt(input[1]);
                try {
                    storage.get(index - 1).setDone();
                    Task task = storage.get(index - 1);
                    System.out.println("Nice! I've marked this task as done:\n" +
                            task.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new GlutiException("Make sure that you have selected the correct task!");
                }
                //storage.get(index - 1).setDone();
                //Task task = storage.get(index - 1);
                //System.out.println("Nice! I've marked this task as done:\n" +
                //        task.toString());
                break;
            case "unmark":
                index = Integer.parseInt(input[1]);
                try {
                    storage.get(index - 1).setunDone();
                    Task task = storage.get(index - 1);
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            task.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new GlutiException("Make sure that you have selected the correct task!");
                }
//                    storage.get(index - 1).setunDone();
//                    task = storage.get(index - 1);
//                    System.out.println("OK, I've marked this task as not done yet:\n" +
//                            task.toString());
                break;
            case "todo":
                String[] tempinput = word.split(" ", 2);
                try {
                    assert tempinput.length > 2;
                    Todo toDo = new Todo(tempinput[1]);
                    storage.add(toDo);
                    System.out.println("Got it. I've added this task:\n" +
                            toDo);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } catch (ArrayIndexOutOfBoundsException e){
                    throw new GlutiException("Todo must have a description!");
                }
                break;
            case "event":
                String[] tempinpute = word.split(" ", 2);
                try {
                    assert tempinpute.length == 2 ;
                    assert tempinpute[0].length() > 1 ;
                    String description = tempinpute[1].split("/from",2)[0];
                    String[] period = tempinpute[1].split("/from",2)[1].split("/to",2);
                    Event event = new Event(description, period);
                    storage.add(event);
                    System.out.println("Got it. I've added this task:\n" +
                            event);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    throw new GlutiException("Event must be in this format event <description> /from <date+time> /to <time>");
                }

                break;
            case "deadline":
                String[] tempinputd = word.split(" ", 2);//[1].split(" /by ", 2);
                try {
                    assert tempinputd.length == 2;
                    assert tempinputd[0].length() > 3;
                    String description = tempinputd[1].split("/by", 2)[0];
                    String time = tempinputd[1].split("/by", 2)[1];
                    Deadline deadline = new Deadline(description, time);
                    storage.add(deadline);
                    System.out.println("Got it. I've added this task:\n" +
                            deadline);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    throw new GlutiException("Deadline must be in this format <description> /by <date+time>");
                }
                break;
            case "delete":
                index = Integer.parseInt(input[1]);
                try {
                    Task task = storage.get(index - 1);
                    storage.remove(index - 1);
                    System.out.println("Noted. I've removed this task:\n" +
                            task.toString());
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    throw new GlutiException("Make sure that you have selected the correct task!");
                }
                break;
            default:
//                storage.add(new Task(word));
//                System.out.println("added: " + word + "\n");
                break;
            }
        }
        fStorage.saveList(storage);
        System.out.println(end);
    }
}
