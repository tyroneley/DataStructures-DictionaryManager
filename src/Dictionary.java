import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private static HashMap<String, String> wordsList = new HashMap<String, String>();

    public static HashMap<String, String> getWords() {
        return wordsList;
    }

    public static void addWord(String newWord, String definition) {
        wordsList.put(newWord, definition);
    }

    public static String searchWord(String searchedWord) {
        return wordsList.get(searchedWord);
    }

    public static void deleteWord(String wordToDelete) {
        wordsList.remove(wordToDelete);
    }

    public static void saveFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/words.txt"))) {
            String wordData = "";
            for (String word : wordsList.keySet()) {
                wordData = word + ";" + wordsList.get(word);
                writer.write(wordData);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/words.txt"))) {
            String wordData = "";
            while ((wordData = reader.readLine()) != null) {
                String[] dataTable = wordData.split(";");
                wordsList.put(dataTable[0], dataTable[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
