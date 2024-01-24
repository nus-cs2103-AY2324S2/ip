public class Deadline extends Task {
    String deadline;
    public Deadline(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[D]%s(by:%s)", super.toString(), deadline);
        return str;
    }
}
