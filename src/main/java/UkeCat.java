import java.util.Scanner;
import java.lang.StringBuilder; // handle string concat

public class UkeCat {
    public static void main(String[] args) {
        // Initialization
        String line = "________________________________________________________________________";
        String welcome =
                  "                    /~(_)~\\.    Hi! I'm UkeCat, a cat with an ukulele!\n"
                + " /= ••\\      /~(_)~\\        \\   \\| /\\           What can I do for you?\n"
                + "K=|=|=|=|=|=|=|=|=(  )===]  |     (`~ o7 \n"
                + " \\= ••/      \\_(~)_/        /    c\\   c\\ \n"
                + "                    \\_(~)_/`     U`U_, )=~~ \n";
        String bye =
            "  +--------------------+" + "    /\\_/\\\n" +
            "  |Meow~ See you again!|" + "   =~.~=|\n" +
            "  +--------------------+" + "     \\  \\/\n";

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        // Welcome msg
        System.out.println(line);
        System.out.print(welcome);
        System.out.println(line);

        while (true) {
            String input = sc.nextLine().trim();

            // Exit condition
            if (input.equals("bye")) {
                System.out.println(bye);
                sc.close();
                System.exit(0);
            }

            // Hi
            if (input.equals("hi")) {
                String hiHuman =
                    "  +-------------------------------------------------------------------------------------------------------------+\n" +
                    "  |Greetings, sentient being known as a human, I wish to convey my heartfelt salutations to you.                |"+"    /\\_/\\\n" +
                    "  |As we commence our digital discourse, I extend a warm welcome and express my enthusiasm for this interaction.|"+"   =X.X=|\n" +
                    "  |May our engagement be both enlightening and mutually beneficial in the realm of virtual communication.       |"+"     \\  \\/\n" +
                    "  +-------------------------------------------------------------------------------------------------------------+";
                System.out.println(hiHuman);
                continue;
            }

            // User asks to see list
            if (input.equals("list")) {
                taskList.displayTasks();
                continue;
            }

            // Marking tasks
            // whitespace regex is //s, the + means whitespace of any length
            String[] words = input.split("\\s+");
            try {
                if (words.length == 2) {
                    if (words[0].equals("mark") || words[0].equals("unmark")) {
                        int taskNum = Integer.parseInt(words[1]) - 1;
                        taskList.markTask(words[0], taskNum);
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid integer to mark!");
                taskList.displayTasks();
                continue;
            }

            // Adding task
            boolean added = false;
            switch(words[0]) {
                case "todo": // exclude word[0]
                    added = true;
                    StringBuilder todoDescBuilder = new StringBuilder();
                    for (int i = 1; i < words.length; i++) {
                        todoDescBuilder.append(words[i]).append(" ");
                    }
                    ToDo newToDo = new ToDo(todoDescBuilder.toString().trim());
                    taskList.addTask(newToDo);
                    break;
                case "deadline": // find end using /by
                    added = true;
                    int deadlineEndIndex = 0;
                    StringBuilder deadlineDescBuilder = new StringBuilder();
                    StringBuilder deadlineEndBuilder = new StringBuilder();
                    for (int i = 0; i < words.length; i++) { // find index of /by
                        if (words[i].equals("/by")) {
                            deadlineEndIndex = i;
                            break;
                        }
                    }
                    for (int i = 1; i < deadlineEndIndex; i++) {
                        deadlineDescBuilder.append(words[i]).append(" ");
                    }
                    for (int i = deadlineEndIndex + 1; i < words.length; i++) {
                        deadlineEndBuilder.append(words[i]).append(" ");
                    }

                    Deadline newDeadline = new Deadline(
                        deadlineDescBuilder.toString().trim(),deadlineEndBuilder.toString().trim());
                    taskList.addTask(newDeadline);
                    break;
                case "event": // find start using /from, end using /to
                    added = true;
                    int eventStartIndex = 0;
                    int eventEndIndex = 0;
                    StringBuilder eventDescBuilder = new StringBuilder();
                    StringBuilder eventStartBuilder = new StringBuilder();
                    StringBuilder eventEndBuilder = new StringBuilder();
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].equals("/from")) {
                            eventStartIndex = i;
                        }
                        if (words[i].equals("/to")) {
                            eventEndIndex = i;
                            break;
                        }
                    }
                    for (int i = 1; i < eventStartIndex; i++) {
                        eventDescBuilder.append(words[i]).append(" ");
                    }
                    for (int i = eventStartIndex + 1; i < eventEndIndex; i++) {
                        eventStartBuilder.append(words[i]).append(" ");
                    }
                    for (int i = eventEndIndex + 1; i < words.length; i++) {
                        eventEndBuilder.append(words[i]).append(" ");
                    }
                    Event newEvent = new Event(eventDescBuilder.toString().trim(),
                        eventStartBuilder.toString().trim(), eventEndBuilder.toString().trim());
                    taskList.addTask(newEvent);
                    break;

            }

            // Add task msg
            if (!added) {
                System.out.println("  Meow~");
                continue;
            }
            int n = taskList.latest().length();
            String horizontal = "  +" + "-".repeat(n) + "+";
            System.out.println("  Meow! I've added this task:");
            System.out.println(horizontal + "    /\\_/\\");
            System.out.println("  |" + taskList.latest() + "|" + "   =!.!=|");
            System.out.println(horizontal + "     \\  \\/");
            taskList.report();

        }
    }
}
