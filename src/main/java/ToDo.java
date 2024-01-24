public class ToDo extends Task {
    public ToDo(String name) {
        this.name = name;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[T]%s", super.toString());
        return str;
    }
}
