import java.util.Scanner;

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
            String input = sc.nextLine();

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

            // Add task msg
            taskList.addTask(input);
            input = "Added: " + input;
            int n = input.length();
            String horizontal = "  +" + "-".repeat(n) + "+";
            System.out.println(horizontal + "    /\\_/\\");
            System.out.println("  |" + input + "|" + "   =O.O=|");
            System.out.println(horizontal + "     \\  \\/");

        }
    }
}
