import java.util.Scanner;
public class BMO {
    public static void main(String[] args) {
        greet();
        echo();
    }

    static void greet() {
        String hi = "-----------------------------------------\n"
                    + "    BMO chop!\n    Do you want to play video games?\n"
                    + "-----------------------------------------";
        System.out.println(hi);
    }

    static void salute() {
        String bye = "-----------------------------------------\n"
                    + "    Beep boop BMO shut down :(\n"
                    + "-----------------------------------------";
        System.out.println(bye);
    }

    static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("bye")) {
            salute();
        } else {
            String output = "-----------------------------------------\n"
                    + "    " + input + "\n"
                    + "-----------------------------------------";
            System.out.println(output);
            echo();
        }
    }
}
