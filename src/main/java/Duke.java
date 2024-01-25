import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    private static int taskCount = 0;
    private static Task[] tasks = new Task[100];
    public static void main(String[] args) {
        Duke.hello();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                Duke.bye();
                break;
            } else if (input.equals("list")) {
                System.out.print(LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(INDENT + (i + 1) + ". " + tasks[i].getStatusIcon() + " " + tasks[i]);
                }
                System.out.print(LINE);
            } else if (input.indexOf("mark") == 0 || input.indexOf("unmark") == 0) {
                String[] split = input.split(" ");
                if (split.length == 2 && (split[0].equals("mark") || split[0].equals("unmark"))) {
                    try {
                        int i = Integer.parseInt(split[1]);
                        if (split[0].equals("mark")) {
                            tasks[i - 1].markAsDone();
                            System.out.println(LINE + "Yasss! This task is officially slayed:\n  "
                                    + INDENT + tasks[i - 1].getStatusIcon() + " " + tasks[i - 1] + "\n" + LINE);
                        } else {
                            tasks[i - 1].unmark();
                            System.out.println(LINE + "Bruh! This task never happened:\n  "
                                    + INDENT + tasks[i - 1].getStatusIcon() + " " + tasks[i - 1] + "\n" + LINE);
                        }
                    } catch (java.lang.NumberFormatException e) {
                        addTask(input);
                    }
                } else {
                    addTask(input);
                }
            } else {
                addTask(input);
            }
        }
    }

    public static void hello() {
        String logo = "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       ::::::::: \n" +
                "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+: \n" +
                "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+  \n" +
                "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:    \n" +
                "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+    \n" +
                "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#     \n" +
                "    ###          ###     ###    ###             ###             ##########       ###    ###      \n\n";
        System.out.println(LINE + "    What's poppin' fam, it's ya boi \n\n" + logo +
                "    Hit me up with those deets and let's vibe together!" + LINE);
    }

    public static void bye() {
        System.out.print(LINE + "    Peace out, fam! Stay lit and keep those good vibes rollin'!\n" + LINE);
    }

    public static void addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.print(LINE + INDENT + "added: " + input + "\n" + LINE);
    }
}

