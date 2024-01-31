public class taskedString {
    private String item;
    private boolean mark;
    public taskedString(String item){
        this.mark = false;
        this.item = item;
    }
    public void mark(){
        this.mark = true;
    }
    public void unmark(){
        this.mark = false;
    }

    @Override
    public String toString(){
        String X = mark ? "X" : " ";
        return ("[" + X + "] " + item);
    }
}
