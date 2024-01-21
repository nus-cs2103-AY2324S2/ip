public class Greet implements Action {
    Greet(){}
    @Override
    public void execute() {
        PrintUtil.print("Hello! I'm Naruto, and I'm gonna become a ninja!" +
                "\n    Believe it!");
    }
}
