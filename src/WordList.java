import javax.swing.*;

import java.util.HashMap;
import java.util.Vector;

public class WordList extends JPanel {
    private static JScrollPane list;
    private static JTable table;

    public WordList() {
        add(list);
    }

    public static void updateList() {
        Vector<String> headings = new Vector<String>();
        headings.add("Word");
        headings.add("Definition");

        Vector<Vector<String>> rows = new Vector<Vector<String>>();

        HashMap<String, String> wordsList = Dictionary.getWords();
        for(String word : wordsList.keySet()) {
            Vector<String> row = new Vector<String>();
            row.add(word);
            row.add(wordsList.get(word));
            rows.add(row);
        }  

        table = new JTable(rows, headings);
        list = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
    }

    public static void updateListByBucketSort() {
        Vector<String> headings = new Vector<String>();
        headings.add("Word");
        headings.add("Definition");

        Vector<Vector<String>> rows = new Vector<Vector<String>>();

        HashMap<String, String> wordsList = WordSort.bucketSort(Dictionary.getWords());
        for(String word : wordsList.keySet()) {
            Vector<String> row = new Vector<String>();
            row.add(word);
            row.add(wordsList.get(word));
            rows.add(row);
        }

        table = new JTable(rows, headings);
        list = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
    }

    public static void updateListByQuickSort() {
        Vector<String> headings = new Vector<String>();
        headings.add("Word");
        headings.add("Definition");

        Vector<Vector<String>> rows = new Vector<Vector<String>>();

        HashMap<String, String> wordsList = WordSort.quickSort(Dictionary.getWords());
        for(String word : wordsList.keySet()) {
            Vector<String> row = new Vector<String>();
            row.add(word);
            row.add(wordsList.get(word));
            rows.add(row);
        }

        table = new JTable(rows, headings);
        list = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
    }

    public static void updateListByRadixSort() {
        Vector<String> headings = new Vector<String>();
        headings.add("Word");
        headings.add("Definition");

        Vector<Vector<String>> rows = new Vector<Vector<String>>();

        HashMap<String, String> wordsList = WordSort.radixSort(Dictionary.getWords());
        for(String word : wordsList.keySet()) {
            Vector<String> row = new Vector<String>();
            row.add(word);
            row.add(wordsList.get(word));
            rows.add(row);
        }

        table = new JTable(rows, headings);
        list = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
    }

    public static JTable getTable() {
        return table;
    }
}
