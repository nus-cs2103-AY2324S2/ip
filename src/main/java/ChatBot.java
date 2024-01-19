import java.util.List;
import java.util.Random;

public class ChatBot {
    private String name;
    private String icon;
    private List<String> greetings;
    private List<String> farewells;
    private List<String> commands;

    public ChatBot(String name, String icon, List<String> greetings, List<String> farewells, List<String> commands) {
        this.name = name;
        this.icon = icon;
        this.greetings = greetings;
        this.farewells = farewells;
        this.commands = commands;
    }

    public ChatBot(String name, String icon, List<String> greetings, List<String> farewells) {
        this.name = name;
        this.icon = icon;
        this.greetings = greetings;
        this.farewells = farewells;
        this.commands = List.<String>of("greet", "bye");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<String> greetings) {
        this.greetings = greetings;
    }

    public void addGreeting(String greeting) {
        this.greetings.add(greeting);
    }

    public List<String> getFarewells() {
        return farewells;
    }

    public void setFarewells(List<String> farewells) {
        this.farewells = farewells;
    }

    public void addFarewell(String farewell) {
        this.farewells.add(farewell);
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public void greet() {
        System.out.println(this.icon);
        if (this.greetings.isEmpty()) {
            System.out.println("Hello World");
        } else {
            Random randomizer = new Random();
            int dialogueOption = randomizer.nextInt(greetings.size());
            System.out.println(greetings.get(dialogueOption));
        }
    }


    public void bye() {
        if (this.farewells.isEmpty()) {
            System.out.println("Goodbye World");
        } else {
            Random randomizer = new Random();
            int dialogueOption = randomizer.nextInt(farewells.size());
            System.out.println(farewells.get(dialogueOption));
        }
    }



    @Override
    public String toString() {
        return "ChatBot{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", greetings=" + greetings +
                ", farewells=" + farewells +
                '}';
    }
}
