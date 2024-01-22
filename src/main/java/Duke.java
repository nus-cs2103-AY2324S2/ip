import java.util.*;
public class Duke {
    public static void main(String[] args) {

        //name and introduction
        String name = "Bearducky";
        System.out.println("Quack! My name is " + name + ". I would like to earn some bread. How may I " +
                "help?");

        //Set up scanner, store user's inputs, structure to store tasks
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        label:
        while (true) {
            String userInput = scanner.nextLine();
            int x = tasks.size();

            if (userInput.toLowerCase().equals("feed bread to bearducky")) {
                System.out.println("[very happy quacking]");
                continue;
            }

            //marking or not
            if (userInput.toLowerCase().startsWith("mark ") || userInput.toLowerCase().startsWith("unmark ")) {
                String[] inputs = userInput.split(" ");
                try {
                    int num = Integer.parseInt(inputs[1]);
                    if (inputs[0].equals("mark")) {
                        tasks.get(num - 1).Mark();
                    } else {
                        tasks.get(num - 1).unMark();
                    }
                    System.out.println("[busy quacking]");
                } catch (NumberFormatException e) {
                    System.out.println("[angry quacking] I can only mark numbers!");
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("[exasperated quacking] You're not that busy - numbers from 1 to " + x + " only, please.");
                }
                continue;
            }

            //other hard commands
            switch (userInput.toLowerCase()) {
                case "bye":
                    System.out.println("[sad quacking] Can I have my bread now?\n");
                    break label;
                case "list":
                    int i = 1;
                    for (Task a : tasks) {
                        System.out.println(i + ". " + a);
                        i++;
                    }
                    System.out.println("\n");
                    break;
            }

            //marking or not
            if (userInput.toLowerCase().startsWith("todo ") || userInput.toLowerCase().startsWith("event ") ||
                    userInput.toLowerCase().startsWith("deadline ")) {
                String[] inputs = userInput.split("/");
                String[] temp = inputs[0].split(" ");
                switch (temp[0].toLowerCase()) {
                    case "todo":
                        String todoname = "";
                        for (int a = 1; a < temp.length; a++) {
                            todoname = todoname.concat(temp[a]);
                            todoname = todoname.concat(" ");
                        }
                        Task nt = new ToDo(todoname);
                        tasks.add(nt);
                        System.out.println("Task added! You now have " + tasks.size() +" tasks to attend to.");
                        break;
                    case "event":
                        String eventname = "";
                        for (int a = 1; a < temp.length; a++) {
                            eventname = eventname.concat(temp[a]);
                            eventname = eventname.concat(" ");
                        }
                        Task ne = new Event(eventname, inputs[1], inputs[2]);
                        tasks.add(ne);
                        System.out.println("Task added! You now have " + tasks.size() +" tasks to attend to.");
                        break;
                    case "deadline":
                        String deadlinename = "";
                        for (int a = 1; a < temp.length; a++) {
                            deadlinename = deadlinename.concat(temp[a]);
                            deadlinename = deadlinename.concat(" ");
                        }
                        Task nd = new Deadline(deadlinename, inputs[1]);
                        tasks.add(nd);
                        System.out.println("Task added! You now have " + tasks.size() +" tasks to attend to.");
                        break;

                }

            } else if (!userInput.toLowerCase().equals("bye") && !userInput.toLowerCase().equals("list")) {
                System.out.println("[quack] I don't understand that command. If you would like to add a task to the Duckalendar,\n" +
                        " please specify with the task type - \"todo\", \"deadline\" or \"event\" followed by a space in front of the task" +
                        " name.\n For deadlines, please add a /by followed by the deadline (eg. /by Monday) . For events, please add a / followed by the start time, then another / followed by the end time.\n" +
                        " If you would like me to list the things you are procrastinating, please enter the word \"list\"." +
                        " \n If you would like to leave, please enter the word \"bye\". \n Do also feel free to also type the words \"feed bread to bearducky\" ?" +
                        " [Hopeful quacking]");
            }
        }
    }
}
