import java.io.*;
import java.util.HashMap;

public class Dictionary {
    private static HashMap<String, String> wordsList = new HashMap<String, String>();

    public static HashMap<String, String> getWords() {
        return wordsList;
    }

    public static void setWords(HashMap<String, String> newWords) {
        wordsList = newWords;
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
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("words.txt"))) {
            outputStream.writeObject(wordsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("words.txt"))) {
            @SuppressWarnings("unchecked")
            HashMap<String, String> temp = (HashMap<String, String>) inputStream.readObject();
            wordsList = temp;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
