import java.util.*;
import java.lang.*;

public class BMO {

    static List<Task> taskLog = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        receive();
    }

    static void receive() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("bye")) {
            salute();
        } else if (input.equals("backlog")) {
            viewLog();
            receive();
        } else if (input.toLowerCase().startsWith("strike") && input.length() > 7) {
            int index = Integer.parseInt(input.substring(7));
            done(index);
            receive();
        } else if (input.toLowerCase().startsWith("unstrike") && input.length() > 9) {
            int index = Integer.parseInt(input.substring(9));
            unDone(index);
            receive();
        } else {
            addLog(input);
            receive();
        }
    }

    static void greet() {
        String hiPrint = "-----------------------------------------\n"
                    + "    BMO chop!\n    Do you want to play video games?\n"
                    + "-----------------------------------------";
        System.out.println(hiPrint);
    }

    static void salute() {
        String byePrint = "-----------------------------------------\n"
                    + "    Beep boop BMO shut down :(\n"
                    + "-----------------------------------------";
        System.out.println(byePrint);
    }

    static void addLog(String input) {
        Task newTask = new Task(input);
        taskLog.add(newTask);
        String addPrint = "-----------------------------------------\n"
                + "    added: " + newTask + "\n"
                + "-----------------------------------------";
        System.out.println(addPrint);
    }

    static void viewLog() {
        StringBuilder logPrint = new StringBuilder();
        for (int i = 0; i < taskLog.size(); i++) {
            Task currTask = taskLog.get(i);
            logPrint.append(i + 1).append(". ").append(currTask.getStatusIcon())
                    .append(" ").append(currTask.toString())
                    .append("\n");
        }
        System.out.println(logPrint.toString());
    }

    static void done(int index) {
        Task currTask = taskLog.get(index - 1);
        currTask.setStatus(true);
        String donePrint = "-----------------------------------------\n"
                + "    Nice! Can you play video games yet?!\n"
                + "    Completed: " + taskLog.get(index - 1) + "\n"
                + "-----------------------------------------";
        System.out.println(donePrint);
    }

    static void unDone(int index) {
        Task currTask = taskLog.get(index - 1);
        currTask.setStatus(false);
        String unDonePrint = "-----------------------------------------\n"
                + "    Aww come on hurry up and finish so we can play!\n"
                + "    Now I have to wait awhile longer >:(\n"
                + "    Incomplete again: " + taskLog.get(index - 1) + "\n"
                + "-----------------------------------------";
        System.out.println(unDonePrint);
    }
}
