import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    private static int taskCount = 0;
    private static final Task[] tasks = new Task[100];
    public static void main(String[] args) {
        Duke.hello();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine().trim();
            String[] cmdarg = input.split(" ", 2); // [command, arguments]
            if (cmdarg[0].equals("bye")) {
                Duke.bye();
                break;
            } else if (cmdarg[0].equals("list")) {
                System.out.print(LINE);
                System.out.println(INDENT + "I gotchu bruv. Here's your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(INDENT + (i + 1) + "." + tasks[i]);
                }
                System.out.print(LINE);
            } else if (cmdarg[0].equals("mark") || cmdarg[0].equals("unmark")) {
                if (cmdarg.length == 2) {
                    try {
                        int i = Integer.parseInt(cmdarg[1]);
                        if (cmdarg[0].equals("mark")) {
                            tasks[i - 1].markAsDone();
                            System.out.print(LINE + INDENT + "Yasss King/Queen! This task is officially slayed:\n  "
                                    + INDENT + tasks[i - 1] + "\n" + LINE);
                        } else {
                            tasks[i - 1].unmark();
                            System.out.print(LINE + INDENT + "Bruh! This task never happened:\n  "
                                    + INDENT + tasks[i - 1] + "\n" + LINE);
                        }
                    } catch (java.lang.NumberFormatException e) {
                        System.out.println("error"); // edit in error handling
                    }
                } else {
                    System.out.println("error"); // error
                }
            } else if (cmdarg[0].equals("todo")) {
                addTask(cmdarg[1], 0);
            } else if (cmdarg[0].equals("deadline")) {
                addTask(cmdarg[1], 1);
            } else if (cmdarg[0].equals("event")) {
                addTask(cmdarg[1], 2);
            } else {
                System.out.println("error"); // error
            }
        }
    }

    public static void hello() {
        String logo =
                "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n" +
                "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n" +
                "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n" +
                "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n" +
                "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n" +
                "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n" +
                "    ###          ###     ###    ###             ###             ##########       ###    ###\n\n";
        System.out.print(LINE + "    What's poppin' fam, it's ya boi\n\n" + logo +
                "    Hit me up with those deets and let's vibe together!\n" + LINE);
    }

    public static void bye() {
        System.out.print(LINE + INDENT + "Peace out, fam! Stay lit and keep those good vibes rollin'!\n" + LINE);
    }

    // add task according to what type they are
    // ID = { 0 : Todo, 1 : Deadline, 2 : Event }
    public static void addTask(String arg, int id) {
        switch (id) {
            case 0:
                tasks[taskCount] = new Todo(arg);
                System.out.println(LINE + INDENT + "Ayo new task just dropped:\n  " + INDENT + tasks[taskCount]);
                taskCount++;
                System.out.print(INDENT + "Yo, we're " + taskCount + " task(s) deep! Let's keep this SIGMA GRINDSET!\n" + LINE);
                break;
            case 1: {
                String[] descTime = arg.split(" /by "); // [description, by]
                if (descTime.length == 2) {
                    tasks[taskCount] = new Deadline(descTime[0], descTime[1]);
                    System.out.println(LINE + INDENT + "Ayo new task just dropped:\n  " + INDENT + tasks[taskCount]);
                    taskCount++;
                    System.out.print(INDENT + "Yo, we're " + taskCount + " task(s) deep! Let's keep this SIGMA GRINDSET!\n" + LINE);
                } else {
                    System.out.println("error");
                }
                break;
            }
            case 2: {
                String[] descTime = arg.split(" /from "); // [description, fromTo]
                if (descTime.length == 2) {
                    String[] fromTo = descTime[1].split(" /to "); // [from , to]
                    if (fromTo.length == 2) {
                        tasks[taskCount] = new Event(descTime[0], fromTo[0], fromTo[1]);
                        System.out.println(LINE + INDENT + "Ayo new task just dropped:\n  " + INDENT + tasks[taskCount]);
                        taskCount++;
                        System.out.print(INDENT + "Yo, we're " + taskCount + " task(s) deep! Let's keep this SIGMA GRINDSET!\n" + LINE);
                    } else {
                        System.out.println("error");
                    }
                } else {
                    System.out.println("error");
                }
                break;
            }
            default:
                System.out.println("Invalid Task ID, user shouldn't reach here");
        }
    }
}

