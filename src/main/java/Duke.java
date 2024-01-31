import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
public class Duke {
    private ArrayList<Task> tasks;
    public static void main(String[] args) {
        Duke greg = new Duke();
    }

    public Duke() {
        tasks = new ArrayList<Task>();
        read();
        greet();
        listen();
    }
    public void greet() {
        fillerLine();
        System.out.println("    Hello! I am Greg.\n    What can I do for you?");
        fillerLine();
    }

    public void bye() {
        save();
        fillerLine();
        System.out.println("    Goodbye! Hope to see you again soon!");
        fillerLine();
    }

    public static void fillerLine() {
        System.out.println("    _______________________________________");
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String request = sc.nextLine();
            if (request.equals("bye")) {
                bye();
                break;
            } else {
                commands(request, false, false);
            }
        }
    }

    public void commands(String request, boolean reading, boolean isDone) {
        String[] words = request.split(" ", 0);
        int length = words.length;
        boolean addSuccessful = false;

        switch (words[0]) {
            case "true": {
                commands(request.substring(5), reading, true);
                break;
            }
            case "false": {
                commands(request.substring(6), reading, false);
                break;
            }

            case "list": {
                System.out.println(list());
                break;
            }
            case "mark": {
                int index = Integer.parseInt(words[1]) - 1;
                if(index > tasks.size() - 1 || index < 0) {
                    System.out.println("Invalid index!");
                } else {
                    tasks.get(index).mark();
                }
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(words[1]) - 1;
                if(index > tasks.size() - 1 || index < 0) {
                    System.out.println("Invalid index!");
                } else {
                    tasks.get(index).unmark();
                }
                break;
            }
            case "delete": {
                int index = Integer.parseInt(words[1]) - 1;
                delete(index);
                break;
            }
            case "todo": {
                addSuccessful = addTodo(words, !reading, isDone);
                break;
            }
            case "deadline": {
                addSuccessful = addDeadline(words, !reading, isDone);
                break;
            }
            case "event": {
                addSuccessful = addEvent(words, !reading, isDone);
                break;
            }
            default:
                System.out.println("Sorry, I don't know this command :(");
                break;
        }
    }

    public String list() {
        StringBuilder str = new StringBuilder();
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty! Congratulations!");
        } else {
            for(int i = 0; i < tasks.size(); i++) {
                str.append(String.format("%s: %s \n", i + 1, tasks.get(i)));
            }
        }
        return str.toString();
    }

    public boolean addTodo(String[] words, boolean announce, boolean isDone) {
        boolean successful = false;
        int length = words.length;
        int wordsIndex = 1;

        if (length < 2) {
            System.out.println("The name cannot be empty!");
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            while(wordsIndex < length) {
                nameBuilder.append(" ").append(words[wordsIndex]);
                wordsIndex++;
            }
            String name = nameBuilder.substring(1);
            successful = true;
            Task task = new ToDo(name, isDone);
            tasks.add(task);

            if(announce) {
                announceAddition(task);
            }
        }
        return successful;
    }

    public boolean addDeadline(String[] words, boolean announce, boolean isDone) {
        boolean successful = false;
        int length = words.length;
        int wordsIndex = 1;

        if (length < 2) {
            System.out.println("The name cannot be empty!");
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            while(wordsIndex < length) {
                if (words[wordsIndex].equals("/by")) {
                    wordsIndex++;
                    break;
                } else {
                    nameBuilder.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                }
            }
            String name = nameBuilder.substring(1);

            if (wordsIndex >= length) {
                System.out.println("The deadline cannot be empty!");
            } else {
                StringBuilder deadlineBuilder = new StringBuilder();
                while (wordsIndex < length) {
                    deadlineBuilder.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                }
                String deadline = deadlineBuilder.substring(1);
                successful = true;
                Task task = new Deadline(name, deadline, isDone);
                tasks.add(task);

                if(announce) {
                    announceAddition(task);
                }
            }
        }
        return successful;
    }

    public boolean addEvent(String[] words, boolean announce, boolean isDone) {
        boolean successful = false;
        int length = words.length;
        int wordsIndex = 1;

        if (length < 2) {
            System.out.println("The name cannot be empty!");
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            while(wordsIndex < length) {
                if (words[wordsIndex].equals("/from")) {
                    wordsIndex++;
                    break;
                } else {
                    nameBuilder.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                }
            }
            String name = nameBuilder.substring(1);

            if (wordsIndex >= length) {
                System.out.println("The start date cannot be empty!");
            } else {
                StringBuilder startDateBuilder = new StringBuilder();
                while (wordsIndex < length) {
                    if (words[wordsIndex].equals("/to")) {
                        wordsIndex++;
                        break;
                    } else {
                        startDateBuilder.append(" ").append(words[wordsIndex]);
                        wordsIndex++;
                    }
                }
                String startDate = startDateBuilder.substring(1);

                if (wordsIndex >= length) {
                    System.out.println("The end date cannot be empty!");
                } else {
                    StringBuilder endDateBuilder = new StringBuilder();
                    while (wordsIndex < length) {
                        endDateBuilder.append(" ").append(words[wordsIndex]);
                        wordsIndex++;
                    }
                    String endDate = endDateBuilder.substring(1);
                    successful = true;
                    Task task = new Event(name, startDate, endDate, isDone);
                    tasks.add(task);

                    if(announce) {
                        announceAddition(task);
                    }
                }
            }
        }
        return successful;
    }

    public void announceAddition(Task task) {
        System.out.println("Alright. Adding this task:");
        System.out.println(task);
        String str = "";
        str = String.format("You now have %s tasks", tasks.size());
        System.out.println(str);
    }
    public void delete(int index) {
        if(index > tasks.size() - 1 || index < 0) {
            System.out.println("Invalid index!");
        } else {
            Task task = tasks.get(index);
            System.out.println("Alright, removing this task");
            System.out.println(task.toString());
            tasks.remove(index);
            System.out.println(String.format("You now have %s tasks left", tasks.size()));
        }
    }

    public void save() {
        try {
            FileWriter f = new FileWriter("save.txt");
            for(Task task : tasks) {
                String taskString = task.convertToText();
                f.write(taskString);
                f.write("\n");
            }
            f.close();
        } catch (java.io.IOException e) {
            System.out.println("Error in saving");
        }
    }

    public void read() {
        File f = new File("save.txt");
        if(!f.isFile()) {
            try {
                f.createNewFile();
            } catch (java.io.IOException e) {
                System.out.println("Could not create save file :(");
            }
        } else {
            try {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String input = s.nextLine();
                    commands(input, true, false);
                }
                System.out.println("Save loaded!");
            } catch (FileNotFoundException e2) {
                System.out.println("File Not Found :(");
            }
        }
    }
}
