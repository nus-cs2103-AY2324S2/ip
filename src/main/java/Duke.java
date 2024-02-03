import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Duke {
    //Initialise variables for the programs
    private static final String INDENT = "   ";
    private static final String bar = "____________________________________________________________";
    private static final String first = " Hello! I'm Pluiexo";
    private static final String second = " What can I do for you?";
    private static final String third = " Bye. Hope to see you again soon!";
    private static final String[] greet = new String[]{bar, first, second, bar};
    private static final String[] bye = new String[]{bar, third, bar};


    //Variables for file writing stuff
    private static final String FILE_PATH = "data/duke.txt";


    private static String handleException(String msg) {
        //Used when your user does not know how to use the application
        switch (msg) {
            case "description":
                return " OOPS!!! The description of this command cannot be empty.";
            case "from":
                return " OOPS!!! the from date .";
            case "by":
                return " OOPS!!! the by date cannot be empty.";
            case "dateError":
                return "OOPS!!! Incorrect date format!!!";
            case "number":
                return "OOPS!!! This is missing your index number";
            case "outOfRange":
                return "Opps!!!!! Your index in out of range!";
            case "empty":
                return "Opps!!!!! Your list is empty!!!You can't do any of these actions yet!";
            case "invalid":
                return "OOPS!!! I'm sorry, incorrect command or input";
            default:
                return "invalid application commence self-destruct";
        }
    }


    public static void main(String[] args) throws Exception {
        TaskManager manager = new TaskManager();
        //Greet first

        for (String l : greet) {
            System.out.println(INDENT + l);
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //Try to load the task
        //And get the items

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            //Load from file
            File storage = new File(FILE_PATH);
            if (!storage.createNewFile()) {
                //Load data if the file is not created
                manager.loadTasksFromFile(new File(FILE_PATH));
            }

        }

        //Update periodically for saving
        Timer save = new Timer();
        TimerTask savingTask = new TimerTask() {
            @Override
            public void run() {

                if (manager.getUpdate()) {
                    try (FileWriter fw = new FileWriter(FILE_PATH)) {
                        fw.write(manager.getTasksSave());
                        manager.setUpdate(false);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                }
            }

        };

        save.scheduleAtFixedRate(savingTask, 0, 1000);

        while (true) {
            String next = input.readLine();
            if (next.equals("bye")) {
                //Save the application again and exit
                try (FileWriter fw = new FileWriter(FILE_PATH)) {
                    fw.write(manager.getTasksSave());
                    manager.setUpdate(false);//reset the change here
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                for (String l : bye) {
                    System.out.println(INDENT + l);
                }
                break;
            }
            //This is and arraylist for managing the output items
            ArrayList<String> output = new ArrayList<>();
            output.add(bar);
            try {
                //Change to support regex instead to make things neater
                final Pattern PATTERN_MANAGE = Pattern.compile("((?i)unmark|mark|delete) (\\d+)");
                final Pattern PATTERN_ACTIONS = Pattern.compile("((?i)todo|deadline|event) (.+)");
                Matcher manageMatch = PATTERN_MANAGE.matcher(next);
                Matcher actionMatch = PATTERN_ACTIONS.matcher(next);
                if (next.matches("((?i)list)")) {
                    output.addAll(manager.ListItems());
                } else {
                    if (manageMatch.matches()) {
                        Manage act = Manage.valueOf(manageMatch.group(1).toUpperCase());
                        output.add(manager.manageTask(act, manageMatch.group(2)));
                    } else if (actionMatch.matches()) {
                        Actions act = Actions.valueOf(actionMatch.group(1).toUpperCase());
                        output.add(manager.addTask(act, actionMatch.group(2)));
                    } else {
                        throw new DukeException("invalid");
                    }
                }
            } catch (DukeException e) {
                output.add(INDENT + handleException(e.getMessage()));
            } catch (NumberFormatException e) {
                output.add(INDENT + " OPPPS!!!!That is not a number!!!!!!!!!!");
            }
            output.add(bar);
            for (String l : output) {
                System.out.println(INDENT + l);
            }
            System.out.println();
        }

    }
}
