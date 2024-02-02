public class TODO extends Task{
    public TODO (String s) {
        super(s);
    }
    public TODO (String s, boolean mark) {
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
