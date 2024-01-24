import java.util.Scanner;
public class AaronBot {
    public static void main(String[] args) {
        boolean notBye = true;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Hello, I am AaronBot, please talk to me I love my students very much :)");
        while (notBye) {
            notBye = chat(inputScanner.nextLine());
        }
        inputScanner.close();
    }

    private static boolean chat(String echoString) {
        if (echoString.equalsIgnoreCase("what is your favorite module")) {
            System.out.println("CS1231S :)");
            return true;
        } else if (echoString.equalsIgnoreCase("bye")) {
            System.out.println("Ok byebye, HAND.");
            return false;
        } else {
            System.out.println(echoString + " student.");
            return true;
        }
    }
}
