import java.util.*;
public class    Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n"
                + "        Hello! I'm sibehupzcoder9000\n"
                + "        What you want sia\n"
                + "____________________________________________________________\n";
        String outro = "____________________________________________________________\n"
                + "        wow so ur gg to leave me...\n"
                + "____________________________________________________________\n";

        Scanner sc= new Scanner(System.in);
        System.out.println(intro);
        String str = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                StringBuilder listOutput = new StringBuilder("____________________________________________________________\n");
                for (int i = 0; i < list.size(); i++) {
                    listOutput.append(i + 1).append(". ").append(list.get(i)).append("\n");
                }
                listOutput.append("____________________________________________________________\n");
                System.out.print(listOutput);
            } else {
                String output = "____________________________________________________________\n"
                        + "added: "
                        + str
                        + "\n____________________________________________________________\n";
                System.out.print(output);
                list.add(str);
            }
            str = sc.nextLine();
        }
        System.out.print(outro);
    }
}
