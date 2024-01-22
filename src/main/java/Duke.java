import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        String line = "\t________________________________________________________________";

        Scanner read = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println(line + "\n" + logo + "\n\t Hello! I'm JavAssist ૮ฅ・ﻌ・აฅ");
        System.out.println("\t What can I do for you?\n" + line);
        String input, lowerInput;
        while(true) {
            System.out.println();
            input = read.nextLine();
            input = input.trim();
            lowerInput = input.toLowerCase();
            System.out.println(line);
            if (input.equals("list")) {
                print(list);
            } else if (lowerInput.startsWith("mark")) {
                if (lowerInput.matches("mark\\s\\d+")) {
                    mark(input, true, list);
                } else {
                    System.out.println("\t OOPS!!! Your mark instruction is unclear ૮(˶˃ᆺ˂˶)");
                    System.out.println("\t Try 'mark [task number to mark as done]'.");
                }
            } else if (lowerInput.startsWith("unmark")) {
                if (lowerInput.matches("unmark\\s\\d+")) {
                    mark(input, false, list);
                } else {
                    System.out.println("\t OOPS!!! Your unmark instruction is unclear ૮(˶˃ᆺ˂˶)");
                    System.out.println("\t Try 'unmark [task number to mark as not done]'.");
                }
            } else if (lowerInput.startsWith("delete")) {
                if (lowerInput.matches("delete\\s\\d+")) {
                    delete(input, list);
                } else {
                    System.out.println("\t OOPS!!! Your delete instruction is unclear ૮₍ ´ ꒳ `₎ა");
                    System.out.println("\t Try 'delete [task number to be deleted]'.");
                }
            } else if (lowerInput.equals("bye")) {
                break;
            } else if (lowerInput.startsWith("todo")){
                if (lowerInput.matches("todo\\s.+")) {
                    Task t = new Todo(input.substring(5));
                    add(t, list);
                } else {
                    System.out.println("\t OOPS!!! The description of a todo cannot be empty ૮₍´˶• . • ⑅ ₎ა");
                    System.out.println("\t Try 'todo [task description]'.");
                }
            } else if (lowerInput.startsWith("deadline")){
                if (lowerInput.matches("deadline\\s.+\\s/by\\s.+")) {
                    String[] task = input.split("/by");
                    Task t = new Deadline(task[0].substring(9).trim(), task[1].trim());
                    add(t, list);
                } else {
                    System.out.println("\t OOPS!!! The description and due of a deadline cannot be empty ૮₍´˶• . • ⑅ ₎ა");
                    System.out.println("\t Try 'deadline [task description] /by [date/time]'.");
                }
            } else if (lowerInput.startsWith("event")) {
                if (lowerInput.matches("event\\s.+\\s/from\\s.+\\s/to\\s.+")) {
                    String[] task = input.split("/from");
                    String[] time = task[1].split("/to");
                    Task t = new Event(task[0].substring(6).trim(), time[0].trim(), time[1].trim());
                    add(t, list);
                } else {
                    System.out.println("\t OOPS!!! The description, start and end time of an event cannot be empty ૮₍´˶• . • ⑅ ₎ა");
                    System.out.println("\t Try 'event [task description] /from [start date/time] /to [end date/time]'.");
                }
            } else {
                System.out.println("\t OOPS!!! Sorry I don't know what that means ૮₍ ´• ˕ •` ₎ა");
                System.out.println("\t Try keywords: todo, deadline, event, list, mark, unmark, delete.");
            }
            System.out.println(line);
        }

        System.out.println("\t Bye. Hope to see you again soon! ʕ •ɷ•ʔฅ\n" + line);
    }

    private static void print(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("\t OOPS!!! No task in list ૮ ´• ﻌ ´• ა");
            System.out.println("\t You may add task with keywords: todo, deadline, event.");
        } else {
            System.out.println("\t Here are the tasks in your list ૮ ˙Ⱉ˙ ა :");
            int count = 1;
            for (Task t : list) {
                System.out.println("\t\t " + count + "." + t.printTask());
                count++;
            }
        }
    }

    private static void add(Task t, ArrayList<Task> list) {
        list.add(t);
        int size = list.size();
        System.out.println("\t ᨐฅ Got it! I've added this task:\n\t\t " + t.printTask());
        System.out.println("\t Now you have " + size + (size > 1 ? " tasks" : " task") + " in the list.");
    }

    private static void delete(String input, ArrayList<Task> list) {
        String[] s = input.split("\\s");
        int num = Integer.parseInt(s[1]);
        if (num <= list.size()) {
            Task t = list.remove(num - 1);
            System.out.println("\t Noted ૮ ˶ᵔ ᵕ ᵔ˶ ა I've removed this task:");
            System.out.println("\t\t " + t.printTask());
            System.out.println("\t Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.");
        } else {
            System.out.println("\t OOPS!!! Task (" + num + ") not found ૮₍ ˶0_0˶ ₎ა");
            print(list);
        }
    }
    private static void mark(String input, boolean done, ArrayList<Task> list) {
        String[] s = input.split("\\s");
        int num = Integer.parseInt(s[1]);
        if (num <= list.size()) {
            Task t = list.get(num - 1);
            if (!done) {
                t.undo();
                System.out.println("\t Ok, ૮₍ ˶•⤙•˶ ₎ა I've marked this task as not done yet:");
            } else {
                t.done();
                System.out.println("\t Nice! ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა I've marked this task as done:");
            }
            System.out.println("\t\t " + t.printTask());
        } else {
            System.out.println("\t OOPS!!! Task (" + num + ") not found ૮₍ ˶0_0˶ ₎ა");
            print(list);
        }
    }
}
