public class Storage {
    private static String[] items = new String[100];

    private static int itemCount;

    public Storage(){
        itemCount = 0;
    }

    public void addItem(String item){
        items[itemCount] = item;
        itemCount++;
        System.out.println("added: " + item);
    }

    public void showItem(){
        for (int i = 0 ; i < itemCount; i++){
            System.out.println(i+1 + ". " + items[i]);
        }
    }
}
