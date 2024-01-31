public class TODO extends Task{
    public TODO(String s) {
        super(s);
    }
    @Override
    public String toString(){
        String X = this.getMark() ? "X" : " ";
        return ("[T]"+"[" + X + "] " + this.getItem());
    }
}
