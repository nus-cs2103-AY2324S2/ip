import java.util.List;

public class ChatBot {
    private String name;
    private String icon;
    private List<String> greetings;
    private List<String> farewells;

    public ChatBot(String name, String icon, List<String> greetings, List<String> farewells) {
        this.name = name;
        this.icon = icon;
        this.greetings = greetings;
        this.farewells = farewells;
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
