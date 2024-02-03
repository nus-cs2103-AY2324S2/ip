import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Mamta {
    private static final ArrayList<Task> history = new ArrayList<Task>();

    public static String greet() {
        return "Hello! I'm Mamta\nWhat can I do for you?";
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String echo(String taskType, String command, int taskNum, String deadline, String endTime) {
        String output = "";
        switch (command) {
            case "bye":
                return String.format("------------------------------------------\n%s\n------------------------------------------", Mamta.exit());
            case "list":
                StringBuilder returnOutput = new StringBuilder();
                int count = 0;
                returnOutput.append("------------------------------------------\n");
                for (Task item : history) {
                    count += 1;
                    returnOutput.append(String.format("%d. %s\n", count, history.get(count - 1)));
                }
                returnOutput.append("------------------------------------------");
                return returnOutput.toString();
            case "mark":
                history.set(taskNum - 1, history.get(taskNum - 1).markDone());
                return String.format("------------------------------------------\nNice! I've marked this task as done\n%s\n------------------------------------------", history.get(taskNum - 1));
            case "unmark":
                history.set(taskNum - 1, history.get(taskNum - 1).unmarkTask());
                return String.format("------------------------------------------\nOK, I've marked this task as not done yet:\n%s\n------------------------------------------", history.get(taskNum - 1));
            case "delete":
                Task objToRemove = history.get(taskNum - 1);
                history.remove(objToRemove);
                return String.format("------------------------------------------\nNoted. I've removed this task:\n%s\nNow you have %d tasks in the list\n------------------------------------------", objToRemove, history.size());
            default:
                //handle case where there is no command

                if (taskType.equals("todo")) {
                    Todo myTodo = new Todo(command);
                    if (!command.isEmpty()) {
                        history.add(myTodo);
                        output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myTodo, history.size());
                    } else {
                        output = String.valueOf(MamtaException.incompleteTaskDescription());
                    }

                } else if (taskType.equals("deadline")) {
                    Deadline myDead = new Deadline(command, deadline);
                    if (!command.isEmpty()) {
                        history.add(myDead);
                        output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myDead, history.size());
                    } else {
                        output = String.valueOf(MamtaException.incompleteTaskDescription());
                    }
                } else if (taskType.equals("event")) {
                    Event myEve = new Event(command, deadline, endTime);
                    if (!command.isEmpty()) {
                        history.add(myEve);
                        output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myEve, history.size());
                    } else {
                        output = String.valueOf(MamtaException.incompleteTaskDescription());
                    }
                } else {
                    //handling the default case where its not a tracked task type, throw an errors
                    output = String.format("------------------------------------------\n%s\n------------------------------------------", MamtaException.invalidTaskType());
                }
                break;
        }
        return output;

    }

    public static void loadTaskData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitOutput = line.split("\\|");
                System.out.println(splitOutput[0]);
                System.out.println(splitOutput[1]);
                System.out.println(splitOutput[2]);
                switch (splitOutput[0]) {
                    case "T":
                        if (splitOutput[1].equals("X")) {
                            history.add(new Todo(true, splitOutput[2]));
                        } else {
                            history.add(new Todo(false, splitOutput[2]));
                        }
                        break;
                    case "D":
                        if (splitOutput[1].equals("X")) {
                            history.add(new Deadline(true, splitOutput[2],splitOutput[3]));
                        } else {
                            history.add(new Deadline(false, splitOutput[2], splitOutput[3]));
                        }
                        break;
                    case "E":
                        if (splitOutput[1].equals("X")) {
                            history.add(new Event(true, splitOutput[2],splitOutput[3], splitOutput[4]));
                        } else {
                            history.add(new Event(false, splitOutput[2], splitOutput[3], splitOutput[4]));
                        }
                        break;
                }
            }
            System.out.println(history);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTasks(String filePath) {
        //prepare the output to be saved first
        StringBuilder output = new StringBuilder();
        for (Task task: history) {
            output.append(task.toString()).append("\n");
        }
        //save the output
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(String.valueOf(output));
            System.out.println("String has been successfully saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String relativeFilePath = "./data/mamtainput.txt";
        Mamta.loadTaskData(relativeFilePath); //loads tasks from file




        try (Scanner scanner = new Scanner(new File("./text-ui-test/input.txt"))) {
            while (scanner.hasNextLine()) {
                String userOutput = scanner.nextLine();

                String[] splitOutput = userOutput.split(" ");

                String word = "";
                int taskNum = -1;

                //in the case user wants to mark/unmark , export this into a helper later
                switch (splitOutput[0]) {
                    case "mark":
                    case "unmark":
                    case "delete":
                        word = splitOutput[0];
                        taskNum = Integer.parseInt(splitOutput[1]);
                        System.out.println(Mamta.echo("default", word, taskNum, "", ""));
                        break;
                    case "todo": {
                        StringBuilder task = new StringBuilder();
                        for (int i = 1; i < splitOutput.length; i++) {
                            task.append(splitOutput[i]).append(" ");
                        }
                        System.out.println(Mamta.echo(splitOutput[0], task.toString(), taskNum, "", ""));
                        break;
                    }
                    case "deadline":
                    case "event": {
                        StringBuilder task = new StringBuilder();
                        StringBuilder deadline = new StringBuilder();
                        StringBuilder endTime = new StringBuilder();
                        boolean reachedBy = false;
                        boolean reachedTo = false;

                        //string splitting logic for parsing tasks
                        for (int i = 1; i < splitOutput.length; i++) {
                            if ((!reachedBy && !reachedTo) && (!splitOutput[i].equals("/by") && !splitOutput[i].equals("/from"))) {
                                if (splitOutput[i+1].equals("/by") || splitOutput[i+1].equals("/from")) {
                                    task.append(splitOutput[i]);
                                } else {
                                    task.append(splitOutput[i]).append(" ");
                                }
                            } else if (!reachedTo && (splitOutput[i].equals("/by") || splitOutput[i].equals("/from"))) {
                                reachedBy = true;
                            } else if (reachedBy && (!splitOutput[i].equals("/to"))) {
                                if (i+1 == splitOutput.length ||  (i+1 != splitOutput.length && splitOutput[i+1].equals("/to"))) {
                                    deadline.append(splitOutput[i]);
                                } else {
                                    deadline.append(splitOutput[i]).append(" ");
                                }
                            } else if (splitOutput[i].equals("/to")) {
                                reachedTo = true;
                                reachedBy = false;
                            } else if (reachedTo) {
                                endTime.append(i + 1 == splitOutput.length ? splitOutput[i] : splitOutput[i] + " ");
                            }
                        }
                        System.out.println(Mamta.echo(splitOutput[0], task.toString(), taskNum, deadline.toString(), endTime.toString()));

                        break;
                    }
                    default:
                        System.out.println(Mamta.echo("default", userOutput, taskNum, "", ""));
                        break;
                }
            }

            Mamta.saveTasks(relativeFilePath);



            // At this point, the loop exits because there is no next line, indicating the end of the file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
