import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Parser {

    File file;

    public Parser(String path) {

        try{

            this.file = new File(path);

            file.createNewFile(); //if file exists, does nothing

        } catch(IOException e) {

            System.out.println("An exception was thrown!");
        }
    }


}
