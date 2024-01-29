import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class Duke {
    private Storage storage; //deals with loading and saving
    private TaskList tasks; //operations to add and delete
    private final Ui ui; //deals with interactions from user

    public Duke(String filePath) { //creating a Duke object
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(scanner, tasks);
        parser.read();
    }

    public static void main(String[] args) {
        Duke bearducky = new Duke("./data/tasks.txt");
        bearducky.run();
    }

}
/*
                try {
                        tasks.get(num - 1).Mark();
                    } else if (inputs[0].equals("unmark")) {
                        tasks.get(num - 1).unMark();
                    } else if (inputs[0].equals("delete")) {
                        System.out.println("Bearducky is fighting the task " +  tasks.get(num - 1));
                        tasks.remove(num - 1);
                        System.out.println("[tired quacking] Task has been... eliminated.");
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
                    if (tasks.isEmpty()) {
                        System.out.println("You're a lazy bastard. Go get something to do.");
                        break;
                    }
                    int i = 1;
                    for (Task a : tasks) {
                        System.out.println(i + ". " + a);
                        i++;
                    }
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
                        if (temp.length == 1) {
                            System.out.println("Event cannot be blank");
                            break;
                        }
                        String eventname = "";
                        for (int a = 1; a < temp.length; a++) {
                            eventname = eventname.concat(temp[a]);
                            eventname = eventname.concat(" ");
                        }
                        try {
                            Task ne = new Event(eventname, inputs[1], inputs[2]);
                            tasks.add(ne);
                            System.out.println("Task added! You now have " + tasks.size() + " tasks to attend to.");
                            break;
                        } catch (ArrayIndexOutOfBoundsException b) {
                            System.out.println("Please enter an event with the format event eventname /start time /finish time!");
                            break;
                        }
                    case "deadline":
                        String deadlinename = "";
                        if (temp.length == 1) {
                            System.out.println("Deadline cannot be blank");
                            break;
                        }
                        for (int a = 1; a < temp.length; a++) {
                            deadlinename = deadlinename.concat(temp[a]);
                            deadlinename = deadlinename.concat(" ");
                        }
                        try {
                            String deadline = inputs[1];
                            if (!deadline.startsWith("by ")) {
                                System.out.println("Please enter a deadline with the format deadline deadlinename /by datetime!");
                                continue;
                            }
                            Task nd = new Deadline(deadlinename, deadline);
                            tasks.add(nd);
                            System.out.println("Task added! You now have " + tasks.size() +" tasks to attend to.");
                            break;
                        } catch (ArrayIndexOutOfBoundsException b) {
                            System.out.println("Please enter a deadline with the format /by datetime!");
                        }

                }

            } else if (!userInput.toLowerCase().equals("bye") && !userInput.toLowerCase().equals("list")) {
                System.out.println("[quack] I don't understand that command. If you would like to add a task to the Duckalendar," +
                        " please specify with the task type - \"todo\", \"deadline\" or \"event\" followed by a space in front of the task" +
                        " name.\n For deadlines, please add a /by followed by the deadline (eg. /by Monday) . For events, please add a / followed by the start time,\n" + " then another / followed by the end time.\n" +
                        " To mark or unmark the nth task on the list, enter the command \"mark n\" or \"unmark n\" where n is the number on the list.\n" +
                        " To delete the nth task on the list, enter the command \"delete n\" where n is the number on the list.\n" +
                        " If you would like me to list the things you are procrastinating, please enter the word \"list\"." +
                        " \n If you would like to leave, please enter the word \"bye\". \n Do also feel free to also type the words \"feed bread to bearducky\"?" +
                        " [Hopeful quacking]");
            }
        }
    }
} */
