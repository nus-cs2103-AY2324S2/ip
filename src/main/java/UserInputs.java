import java.util.ArrayList;

public class UserInputs {

    ArrayList<String> userInputs = new ArrayList<>();

    public void add(String userInput) {
        this.userInputs.add(userInput);
    }

    public ArrayList<String> getUserInputs(){
        return userInputs;
    }
}
