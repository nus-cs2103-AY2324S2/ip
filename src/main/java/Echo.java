public class Echo implements Action {
    private final String stuff;
    public Echo(String stuff) {
        this.stuff=stuff;
    }

    @Override
    public void response() {
        System.out.println("  " + "added: " + stuff);
    }
}
