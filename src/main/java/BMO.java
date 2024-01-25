import java.util.*;
import java.lang.*;

public class BMO {

    static List<String> backLog = new ArrayList<>();
    static List<Boolean> checkLog = new ArrayList<>();

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
            undone(index);
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
        backLog.add(input);
        checkLog.add(false);
        String addPrint = "-----------------------------------------\n"
                + "    added: " + input + "\n"
                + "-----------------------------------------";
        System.out.println(addPrint);
    }

    static void viewLog() {
        StringBuilder logPrint = new StringBuilder();
        for (int i = 0; i < backLog.size(); i++) {
            String checkBox = "[";
            if (checkLog.get(i)) {
                checkBox = checkBox + "X]";
            } else {
                checkBox = checkBox + " ]";
            }

            logPrint.append(i + 1).append(". ")
                    .append(checkBox).append(" ")
                    .append(backLog.get(i)).append("\n");
        }
        System.out.println(logPrint.toString());
    }

    static void done(int index) {
        String donePrint = "-----------------------------------------\n"
                + "    Nice! Can you play video games yet?!\n"
                + "    Completed: " + backLog.get(index - 1) + "\n"
                + "-----------------------------------------";
        System.out.println(donePrint);
        checkLog.set(index - 1, true);
    }

    static void undone(int index) {
        String unDonePrint = "-----------------------------------------\n"
                + "    Aww come on hurry up and finish so we can play!\n"
                + "    Now I have to wait awhile longer >:(\n"
                + "    Incomplete again: " + backLog.get(index - 1) + "\n"
                + "-----------------------------------------";
        System.out.println(unDonePrint);
        checkLog.set(index - 1, false);
    }
}
