import java.util.Scanner;

public class Main {
    private static Naruto naruto;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        naruto = new Naruto();
        naruto.greet();
        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                exit();
            }
            naruto.echo(s);
        }

    }
    static void exit() {
        naruto.sayBye();
        System.exit(0);
    }
}
