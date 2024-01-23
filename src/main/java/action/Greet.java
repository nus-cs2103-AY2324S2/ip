package action;

import util.PrintUtil;

public class Greet implements Action {
    public Greet() {
    }

    @Override
    public void execute() {
        PrintUtil.print("Hello! I'm Naruto, and I'm training to become a ninja!" +
                "\nBelieve it!");
    }
}
