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
        Parser parser = new Parser(scanner, tasks, storage);
        parser.read();
    }

    public static void main(String[] args) {
        Duke bearducky = new Duke("./data/tasks.txt");
        bearducky.run();
    }

}
/*
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


                }


        }
    }
} */
