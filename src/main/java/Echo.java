public class Echo implements Action {
    private String input;
    Echo(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        PrintUtil.print(this.input);
    }
}
