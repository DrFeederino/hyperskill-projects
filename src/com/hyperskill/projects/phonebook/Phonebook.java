package com.hyperskill.projects.phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Phonebook {
    public static void main(String[] args) throws FileNotFoundException {
        File dict = new File("directory.txt");
        File find = new File("find.txt");

        if (!dict.isFile() || !find.isFile()) {
            System.out.println("Error, can't read file");
        } else {
            searchFileLinearly(dict, find);
            searchFileWithJumpAndBubble(dict, find);
            searchFileWithQuickAndBinary(dict, find);
            searchFileWithHashTable(dict, find);
        }

    }

    public static void searchFileLinearly(File dict, File find) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        int entries = 0;
        int entriesFound = 0;
        List<String> idsList = new ArrayList<>();
        List<String> namesList = new ArrayList<>();
        Scanner sc = new Scanner(dict);

        while (sc.hasNext()) {
            String id = sc.next().trim();
            String val = sc.nextLine().trim();
            idsList.add(id);
            namesList.add(val);
        }
        String[][] arr = new String[namesList.size()][2];
        for (int i = 0; i < namesList.size(); i++) {
            arr[i][0] = idsList.get(i);
            arr[i][1] = namesList.get(i);
        }

        sc = new Scanner(find);
        while (sc.hasNextLine()) {
            String entry = sc.nextLine().trim();
            entries++;
            entriesFound += linearSearch(arr, entry) ? 1 : 0;
        }

        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println("Start searching (linear search)...");
        System.out.println("Found "
                + entriesFound
                + " / "
                + entries
                + " entries. "
                + "Time taken: "
                + totalTime / 60000 + " min. "
                + totalTime / 1000 % 60 + " sec. "
                + totalTime % 1000 + " ms.\n");
    }

    public static void searchFileWithJumpAndBubble(File dict, File find) throws FileNotFoundException {
        long timeOfBeginningSorting = System.currentTimeMillis();
        int entries = 0;
        int entriesFound = 0;
        List<String> idsList = new ArrayList<>();
        List<String> namesList = new ArrayList<>();
        Scanner sc = new Scanner(dict);

        while (sc.hasNextLine()) {
            String id = sc.next();
            String line = sc.nextLine().trim();
            idsList.add(id);
            namesList.add(line);
        }

        String[][] arr = new String[namesList.size()][2];
        for (int i = 0; i < namesList.size(); i++) {
            arr[i][0] = idsList.get(i);
            arr[i][1] = namesList.get(i);
        }

        bubbleSort(arr);

        long timeOfEndingSorting = System.currentTimeMillis() - timeOfBeginningSorting;
        long timeOfBeginningSearching = System.currentTimeMillis();
        sc = new Scanner(find);

        while (sc.hasNextLine()) {
            String entry = sc.nextLine().trim();
            entries++;
            entriesFound += jumpSearch(arr, entry) != -1 ? 1 : 0;
        }

        long timeOfEndingSearching = System.currentTimeMillis() - timeOfBeginningSearching;
        long totalTime = timeOfEndingSorting + timeOfEndingSearching;

        System.out.println("Start searching (bubble sort + jump search)...");
        System.out.println("Found "
                + entriesFound
                + " / "
                + entries
                + " entries. "
                + "Time taken: "
                + (totalTime) / 60000 + " min. "
                + (totalTime) / 1000 % 60 + " sec. "
                + (totalTime) % 1000 + " ms.");
        System.out.println("Sorting time: "
                + (timeOfEndingSorting) / 60000 + " min. "
                + (timeOfEndingSorting) / 1000 % 60 + " sec. "
                + (timeOfEndingSorting) % 1000 + " ms.");
        System.out.println("Searching time: "
                + timeOfEndingSearching / 60000 + " min. "
                + timeOfEndingSearching / 1000 % 60 + " sec. "
                + timeOfEndingSearching % 1000 + " ms.\n");
    }

    public static void searchFileWithQuickAndBinary(File dict, File find) throws FileNotFoundException {
        long timeOfBeginningSorting = System.currentTimeMillis();
        int entries = 0;
        int entriesFound = 0;
        List<String> names = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        Scanner sc = new Scanner(dict);

        while (sc.hasNextLine()) {
            String id = sc.next().trim();
            String name = sc.nextLine().trim();
            ids.add(id);
            names.add(name);
        }

        String[][] arr = new String[names.size()][2];
        for (int i = 0; i < names.size(); i++) {
            arr[i][0] = ids.get(i);
            arr[i][1] = names.get(i);
        }

        quickSort(arr, 0, arr.length - 1);

        long timeOfEndingSorting = System.currentTimeMillis() - timeOfBeginningSorting;
        long timeOfBeginningSearching = System.currentTimeMillis();
        sc = new Scanner(find);

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            entriesFound += binarySearch(arr, name, 0, entries - 1) != -1 ? 1 : 0;
            entries++;
        }

        long timeOfEndingSearching = System.currentTimeMillis() - timeOfBeginningSearching;
        long totalTime = timeOfEndingSearching + timeOfEndingSorting;

        System.out.println("Start searching (quick sort + binary search)...");
        System.out.println("Found "
                + entriesFound
                + " / "
                + entries
                + " entries. "
                + "Time taken: "
                + (totalTime) / 60000 + " min. "
                + (totalTime) / 1000 % 60 + " sec. "
                + (totalTime) % 1000 + " ms.");
        System.out.println("Sorting time: "
                + (timeOfEndingSorting) / 60000 + " min. "
                + (timeOfEndingSorting) / 1000 % 60 + " sec. "
                + (timeOfEndingSorting) % 1000 + " ms.");
        System.out.println("Searching time: "
                + timeOfEndingSearching / 60000 + " min. "
                + timeOfEndingSearching / 1000 % 60 + " sec. "
                + timeOfEndingSearching % 1000 + " ms.\n");
    }

    public static void searchFileWithHashTable(File dict, File find) throws FileNotFoundException {
        long timeOfBeginningSorting = System.currentTimeMillis();
        int entries = 0;
        int entriesFound = 0;
        Map<String, String> table = new HashMap<>();
        Scanner sc = new Scanner(dict);

        while (sc.hasNextLine()) {
            String val = sc.next().trim();
            String id = sc.nextLine().trim();
            table.put(id, val);
        }

        long timeOfEndingSorting = System.currentTimeMillis() - timeOfBeginningSorting;
        long timeOfBeginningSearching = System.currentTimeMillis();
        sc = new Scanner(find);

        while (sc.hasNextLine()) {
            String val = sc.nextLine().trim();
            entries++;
            entriesFound += table.get(val) != null ? 1 : 0;
        }

        long timeOfEndingSearching = System.currentTimeMillis() - timeOfBeginningSearching;
        long totalTime = timeOfEndingSearching + timeOfEndingSorting;

        System.out.println("Start searching (hash table)...");
        System.out.println("Found "
                + entriesFound
                + " / "
                + entries
                + " entries. "
                + "Time taken: "
                + (totalTime) / 60000 + " min. "
                + (totalTime) / 1000 % 60 + " sec. "
                + (totalTime) % 1000 + " ms.");
        System.out.println("Creating time: "
                + (timeOfEndingSorting) / 60000 + " min. "
                + (timeOfEndingSorting) / 1000 % 60 + " sec. "
                + (timeOfEndingSorting) % 1000 + " ms.");
        System.out.println("Searching time: "
                + timeOfEndingSearching / 60000 + " min. "
                + timeOfEndingSearching / 1000 % 60 + " sec. "
                + timeOfEndingSearching % 1000 + " ms.\n");
    }

    private static boolean linearSearch(String[][] arr, String target) {
        boolean hasFound = false;

        for (String[] row : arr) {
            String val = row[1];
            if (target.compareTo(val) == 0) {
                hasFound = true;
                break;
            }
        }

        return hasFound;
    }

    private static void bubbleSort(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1][1].compareToIgnoreCase(arr[j][1]) > 0) {
                    String[] temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static int jumpSearch(String[][] array, String target) {
        int currentRight = 0;
        int prevRight = 0;

        if (array.length == 0) {
            return -1;
        }

        if (array[currentRight][2].equals(target)) {
            return 0;
        }

        int jumpLength = (int) Math.sqrt(array.length);

        while (currentRight < array.length - 1) {
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);
            if (array[currentRight][2].compareToIgnoreCase(target) >= 0) {
                break;
            }
            prevRight = currentRight;
        }

        if ((currentRight == array.length - 1) && target.compareToIgnoreCase(array[currentRight][2]) > 0) {
            return -1;
        }

        return backwardSearch(array, target, prevRight, currentRight);
    }

    private static int backwardSearch(String[][] array, String target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i][2].compareToIgnoreCase(target) == 0) {
                return i;
            }
        }

        return -1;
    }

    public static void quickSort(String[][] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    private static int partition(String[][] array, int left, int right) {
        String pivot = array[right][1];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i][1].compareTo(pivot) <= 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(String[][] array, int i, int j) {
        String[] temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int binarySearch(String[][] arr, String val, int left, int right) {
        int elem = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid][1].compareTo(val) == 0) {
                elem = mid;
                break;
            } else if (arr[mid][1].compareTo(val) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return elem;
    }
}
