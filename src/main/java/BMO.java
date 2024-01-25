import java.util.*;
import java.lang.*;

public class BMO {

    static List<String> backLog = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        receive();
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

    static void receive() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("bye")) {
            salute();
        } else if (input.equals("backlog")) {
            StringBuilder logPrint = new StringBuilder();
            for (int i = 0; i < backLog.size(); i++) {
                logPrint.append(i + 1).append(". ").append(backLog.get(i)).append("\n");
            }
            System.out.println(logPrint.toString());
            receive();
        } else {
            backLog.add(input);
            String addPrint = "-----------------------------------------\n"
                    + "added: " + input + "\n"
                    + "-----------------------------------------";
            System.out.println(addPrint);
            receive();
        }
    }
}
