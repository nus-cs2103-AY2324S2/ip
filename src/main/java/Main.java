import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Zero().run().close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
