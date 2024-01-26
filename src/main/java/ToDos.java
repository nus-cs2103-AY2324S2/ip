public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDesc(){
        return String.format("%s", this.getName());
    };
}
