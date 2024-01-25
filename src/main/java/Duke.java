import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    private static final ArrayList<Task> tasks = new ArrayList<Task>();
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
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
                }
                System.out.print(LINE);
            } else if (cmdarg[0].equals("mark") || cmdarg[0].equals("unmark") || cmdarg[0].equals("delete")) {
                if (cmdarg.length == 2) {
                    try {
                        int i = Integer.parseInt(cmdarg[1]);
                        if (i > tasks.size()) { // incorrect index
                            System.out.print(LINE + INDENT + "You ain't got that many tasks bruh!\n" + LINE);
                        } else if (i < 1) { // incorrect index
                            System.out.print(LINE + INDENT + "Start from task 1 lil bro!\n" + LINE);
                        } else if (cmdarg[0].equals("mark")) {
                            Task task = tasks.get(i - 1);
                            task.markAsDone();
                            System.out.print(LINE + INDENT + "Yasss King/Queen! This task is officially slayed:\n  "
                                    + INDENT + task + "\n" + LINE);
                        } else if (cmdarg[0].equals("unmark")){
                            Task task = tasks.get(i - 1);
                            task.unmark();
                            System.out.print(LINE + INDENT + "You forgor:\n  "
                                    + INDENT + task + "\n" + LINE);
                        } else {
                            Task task = tasks.get(i - 1);
                            tasks.remove(i - 1);
                            System.out.print(LINE + INDENT + "Zamn! This task never happened:\n  "
                                    + INDENT + task + "\n" + LINE);
                        }
                    } catch (java.lang.NumberFormatException e) { // non number typed
                        System.out.print(LINE + INDENT + "Ain't no way! We lackin' just numbers after mark/unmark/delete.\n"
                                + INDENT + "e.g. unmark 2\n" + LINE);
                    }
                } else { // no arguments
                    System.out.print(LINE + INDENT + "Ain't no way! Which task we vibin' with?\n"
                            + INDENT + "e.g. mark/unmark/delete 1\n" + LINE);
                }
            } else if (cmdarg[0].equals("todo")) {
                if (cmdarg.length == 2) {
                    addTask(cmdarg[1], 0);
                } else { // no arguments
                    System.out.print(LINE + INDENT + "Ain't no way! You got caught lackin' the format!\n"
                            + INDENT + "e.g. todo <task>\n" + LINE);
                }
            } else if (cmdarg[0].equals("deadline")) {
                if (cmdarg.length == 2) {
                    addTask(cmdarg[1], 1);
                } else { // no arguments
                    System.out.print(LINE + INDENT + "Ain't no way! You got caught lackin' the format!\n"
                            + INDENT + "e.g. deadline <task> /by <date/time>\n" + LINE);
                }
            } else if (cmdarg[0].equals("event")) {
                if (cmdarg.length == 2) {
                    addTask(cmdarg[1], 2);
                } else { // no arguments
                    System.out.print(LINE + INDENT + "Ain't no way! You got caught lackin' the format!\n"
                            + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>\n" + LINE);
                }
            } else { // invalid command
                System.out.print(LINE + INDENT + "What is blud yappin'? Here's the legit commands:"
                        + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"); // error
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
                Todo todo = new Todo(arg);
                tasks.add(todo);
                System.out.println(LINE + INDENT + "Ayo new task just dropped:\n  " + INDENT + todo);
                System.out.print(INDENT + "Yo, we're " + tasks.size() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n" + LINE);
                break;
            case 1: {
                String[] descTime = arg.split(" /by "); // [description, by]
                if (descTime.length == 2) {
                    Deadline deadline = new Deadline(descTime[0], descTime[1]);
                    tasks.add(deadline);
                    System.out.println(LINE + INDENT + "Ayo new task just dropped:\n  " + INDENT + deadline);
                    System.out.print(INDENT + "Yo, we're " + tasks.size() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n" + LINE);
                } else { // incorrect formatting for /by
                    System.out.print(LINE + INDENT + "When you wanna do this task by lil bro?\n"
                            + INDENT + "e.g. deadline <task> /by <date/time>\n" + LINE);
                }
                break;
            }
            case 2: {
                String[] descTime = arg.split(" /from "); // [description, fromTo]
                if (descTime.length == 2) {
                    String[] fromTo = descTime[1].split(" /to "); // [from , to]
                    if (fromTo.length == 2) {
                        Event event = new Event(descTime[0], fromTo[0], fromTo[1]);
                        tasks.add(event);
                        System.out.println(LINE + INDENT + "Ayo new task just dropped:\n  " + INDENT + event);
                        System.out.print(INDENT + "Yo, we're " + tasks.size() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n" + LINE);
                    } else { // incorrect formatting for /to
                        System.out.println(LINE + INDENT + "When does this event end lil bro?\n"
                                + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>\n" + LINE);
                    }
                } else { // incorrect formatting for /from
                    System.out.println(LINE + INDENT + "When does this event start lil bro?\n"
                            + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>\n" + LINE);
                }
                break;
            }
            default: // Invalid Task ID
                System.out.println("Invalid Task ID, user shouldn't reach here");
        }
    }
}