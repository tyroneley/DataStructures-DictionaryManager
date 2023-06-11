import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordSort {
    public static HashMap<String, String> bucketSort(HashMap<String, String> hashMap) {
        long timeStart = System.nanoTime();

        List<List<Map.Entry<String, String>>> buckets = new ArrayList<>(26);

        for (int i = 0; i < 26; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey().toUpperCase();
            int bucketIndex = key.charAt(0) - 'A';

            buckets.get(bucketIndex).add(entry);
        }

        for (List<Map.Entry<String, String>> bucket : buckets) {
            Collections.sort(bucket, Comparator.comparing(Map.Entry::getKey));
        }

        HashMap<String, String> sortedHashMap = new LinkedHashMap<>();

        for (List<Map.Entry<String, String>> bucket : buckets) {
            for (Map.Entry<String, String> entry : bucket) {
                sortedHashMap.put(entry.getKey(), entry.getValue());
            }
        }

        long timeEnd = System.nanoTime();
        long sortingTime = TimeUnit.MILLISECONDS.convert(timeEnd - timeStart, TimeUnit.NANOSECONDS);
        System.out.println("[BUCKET SORT] SORTING TIME: " + sortingTime + "ms");

        return sortedHashMap;
    }

    public static HashMap<String, String> quickSort(HashMap<String, String> hashMap) {
        long timeStart = System.nanoTime();

        List<Map.Entry<String, String>> entries = new ArrayList<>(hashMap.entrySet());

        quickSort(entries, 0, entries.size() - 1);

        HashMap<String, String> sortedHashMap = new LinkedHashMap<>();

        for (Map.Entry<String, String> entry : entries) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        long timeEnd = System.nanoTime();
        long sortingTime = TimeUnit.MILLISECONDS.convert(timeEnd - timeStart, TimeUnit.NANOSECONDS);
        System.out.println("[QUICK SORT] SORTING TIME: " + sortingTime + "ms");

        return sortedHashMap;
    }

    public static HashMap<String, String> radixSort(HashMap<String, String> hashMap) {
        long timeStart = System.nanoTime();

        List<Map.Entry<String, String>> entries = new ArrayList<>(hashMap.entrySet());

        int maxLength = getMaxKeyLength(entries);

        radixSort(entries, maxLength);

        HashMap<String, String> sortedHashMap = new LinkedHashMap<>();

        for (Map.Entry<String, String> entry : entries) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        long timeEnd = System.nanoTime();
        long sortingTime = TimeUnit.MILLISECONDS.convert(timeEnd - timeStart, TimeUnit.NANOSECONDS);
        System.out.println("[RADIX SORT] SORTING TIME: " + sortingTime + "ms");

        return sortedHashMap;
    }

    private static void quickSort(List<Map.Entry<String, String>> entries, int low, int high) {
        if (low < high) {
            
            int pivotIndex = partition(entries, low, high);

            quickSort(entries, low, pivotIndex - 1);
            quickSort(entries, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Map.Entry<String, String>> entries, int low, int high) {
        String pivot = entries.get(high).getKey().toUpperCase();

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (entries.get(j).getKey().toUpperCase().compareTo(pivot) <= 0) {
                i++;
                Collections.swap(entries, i, j);
            }
        }

        Collections.swap(entries, i + 1, high);

        return i + 1;
    }

    private static int getMaxKeyLength(List<Map.Entry<String, String>> entries) {
        int maxLength = 0;
        for (Map.Entry<String, String> entry : entries) {
            int length = entry.getKey().length();
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    private static void radixSort(List<Map.Entry<String, String>> entries, int maxLength) {
        for (int i = maxLength - 1; i >= 0; i--) {
            List<List<Map.Entry<String, String>>> buckets = new ArrayList<>(26);
            for (int j = 0; j < 26; j++) {
                buckets.add(new ArrayList<>());
            }

            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey().toLowerCase();
                if (i < key.length()) {
                    int bucketIndex = key.charAt(i) - 'a';
                    buckets.get(bucketIndex).add(entry);
                } else {
                    buckets.get(0).add(entry);
                }
            }

            entries.clear();
            for (List<Map.Entry<String, String>> bucket : buckets) {
                entries.addAll(bucket);
            }
        }
    }
}
