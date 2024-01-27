import exception.NarutoException;
import naruto.Naruto;
import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args)  {
        new Naruto();
        while (Naruto.hasNextAction()) {
            Naruto.act();
            Naruto.listen();
        }
    }
}
