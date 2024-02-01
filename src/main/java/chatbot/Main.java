package chatbot;

import java.nio.file.Path;
import java.nio.file.Paths;

import chatbot.cortana.Cortana;

public class Main {

    // We will build the project around the location of this class

    private static Path getBaseDir(Class<?> clazz) {
        String classFilePath = clazz.getName().replace(".", "/") + ".class";
        String classLocation = clazz.getClassLoader().getResource(classFilePath).getPath();

        Path basePath = Paths.get(classLocation).getParent();
        return basePath.toAbsolutePath();
    }
    
    public static void main(String[] args) {
        Path basePath = getBaseDir(Main.class);
        Cortana cortana = new Cortana(basePath.toString());
        cortana.run();
    }

}
