import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
public class GrumbleBug {

    public static String filePath = "./tasks.txt";

    public static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + list.get(i).getFullStatus());
        }
    }

    private static void writeToFile(ArrayList<Task> myList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < myList.size(); i++) {

                Task t = myList.get(i);
                fw.write(t.taskType);
                fw.write("\n");
                fw.write(t.isDone ? "true" : "false");
                fw.write("\n" + t.description + "\n");
                if (t.taskType == 'D') {
                    fw.write(t.endDate + "\n");
                } else if (t.taskType == 'E') {
                    fw.write(t.startDate + "\n");
                    fw.write(t.endDate + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops, IO Exception when writing to file");
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> myList = new ArrayList<>();

        // load data from hard disk
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {

                String task = s.nextLine();
                boolean done = s.nextLine() == "true" ? true : false;
                String desc = s.nextLine();
                Task t;
                if (task.equals("T")) {
                    t = new Task(done, desc);
                } else if (task.equals("D")) {
                    String deadline = s.nextLine();
                    t = new Task(done, desc, deadline);
                } else if (task.equals("E")) {
                    String start = s.nextLine();
                    String deadline = s.nextLine();
                    t = new Task(done, desc, start, deadline);
                } else {
                    t = null;
                    System.out.println("bad formatting in tasks.txt");
                }
                myList.add(t);
            }
        } catch (IOException e) {
            System.out.println("An IO error occurred with the data file.");
            e.printStackTrace();
        }

        String starter = "GrumbleBug:"
                + "_______________________________________\n"
                + "Oh hey, adventurer.\n"
                + "I'm GrumbleBug.\n";
        System.out.println(starter);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input1 = sc.nextLine();
            if (input1.equals("byebye")) {
                System.exit(0);
            } else if (input1.equals("list")) { // show the list!
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "You have these things in your list...";
                System.out.println(reply);
                GrumbleBug.printList(myList);
            } else if (input1.equals("mark")) {
                System.out.println("Which task to mark? Give me the number");
                try {
                    int u = Integer.parseInt(sc.nextLine());
                    myList.get(u-1).setDone(true);
                    System.out.println("Ok, marked it.");
                } catch (Exception e) {
                    System.err.println("That wasn't a good number");
                }
            } else if (input1.equals("unmark")) {
                try {
                    int u = Integer.parseInt(sc.nextLine());
                    myList.get(u-1).setDone(false);
                    System.out.println("Ugh, unmarked it.");
                } catch (Exception e) {
                    System.err.println("That wasn't a good number");
                }
            } else if (input1.equals("todo")) { // add to list
                System.out.println("Task name?");
                String name = sc.nextLine();
                Task task = new Task(false, name);
                myList.add(task);
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "Fine, added your stupid " + input1 + "."
                        + "\n";
                System.out.println(reply);
            } else if (input1.equals("deadline")) { // add to list
                System.out.println("Task name?");
                String name = sc.nextLine();
                System.out.println("Wow ok.. due by?");
                String endDate = sc.nextLine();
                Task task = new Task(false, name, endDate);
                myList.add(task);
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "K, better finish " + input1 + " on time."
                        + "\n_______________________________________\n";
                System.out.println(reply);
            } else if (input1.equals("event")) { // add to list
                System.out.println("Task name?");
                String name = sc.nextLine();
                System.out.println("Start date?");
                String startDate = sc.nextLine();
                System.out.println("End date?");
                String endDate = sc.nextLine();
                Task task = new Task(false, name, startDate, endDate);
                myList.add(task);
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "Better not miss your silly " + input1 + " event."
                        + "\n_______________________________________\n";
                System.out.println(reply);
            } else if (input1.equals("delete")) {
                System.out.println("Which one? Gimme the number\n");
                GrumbleBug.printList(myList);
                try {
                    int u = Integer.parseInt(sc.nextLine());
                    myList.remove(u-1);
                    System.out.println("Ok it's gone. You're wasting my time.");
                } catch (Exception e) {
                    System.err.println("That wasn't a good number");
                }
            } else {
                // error, cannot understand
                System.out.println("I don't understand. Try again, or go away");
            }
            try {
                GrumbleBug.writeToFile(myList);
            } catch (IOException e) {
                System.out.println("IOEXception");
            }
        }
    }
}
