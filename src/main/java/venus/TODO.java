package venus;
public class Todo extends Task{
    public Todo(String s) {
        super(s);
    }
    public Todo(String s, boolean mark) {
        super(s);
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }
    @Override
    public String toString() {
        String X = this.getMark() ? "X" : " ";
        return ("[T]"+"[" + X + "] " + this.getItem());
    }
}
