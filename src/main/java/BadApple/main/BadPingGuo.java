package BadApple.main;

import BadApple.task.Parser;
import BadApple.uiElements.DialogBox;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public class BadPingGuo {
    public static final String FILENAME = "src/main/data/whiteSpace.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Sunny.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/MARI.png"));

    public static boolean makeFile() {
        try {
            File f1 = new File("src/main/data");
            File f = new File("src/main/data/whiteSpace.txt");
            return f1.mkdir() && f.createNewFile();
        } catch (IOException e) {
            System.out.println("Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?");
            return false;
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input){
        try {
            return Parser.ProcessQuery(input);
        } catch (IOException e) {
            return "FAILED";
        }
    }

}
