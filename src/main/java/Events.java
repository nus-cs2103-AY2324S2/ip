public class Events extends Task {
    private String from;
    private String to;

    public Events(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDesc(){
        return String.format("%s (from: %s to: %s)", this.getName(), this.from, this.to);
    };
}
