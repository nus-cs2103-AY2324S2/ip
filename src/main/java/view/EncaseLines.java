package view;

public class EncaseLines {
    public static void display(String string) {
        SingleLine.display();
        System.out.println(string);
        SingleLine.display();
    }
}
