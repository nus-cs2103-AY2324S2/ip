import java.time.LocalDateTime;

public class Deadlines extends Task {
    private String by;

    public Deadlines(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDesc(){
        return String.format("%s (by: %s)", this.getName(), this.by);
    };

}
