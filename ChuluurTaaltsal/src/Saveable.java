public interface Saveable {
    void saveToFile(String filename) throws java.io.IOException;
    void loadFromFile(String filename) throws java.io.IOException;
    String toFileString();
}
