import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Area.\n" +
                "What can I do for you?\n"
        );

        Scanner sc = new Scanner(System.in);
        String[] storage = new String[100];
        int count = 0;
        while(true){
            String instruction = sc.nextLine();
            if(instruction.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if(instruction.equals("list")){
                for(int i=0;i<count;i++){
                    System.out.println( i + 1 +". " + storage[i]);
                }
            }else{
               storage[count] = instruction;
               count++;
               System.out.println("added: " + instruction );
            }
        }
    }
}
