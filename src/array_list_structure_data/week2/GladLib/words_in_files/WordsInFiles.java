package array_list_structure_data.week2.GladLib.words_in_files;

import edu.duke.DirectoryResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class WordsInFiles {

    private HashMap<String, ArrayList<String>> hashMap;

    WordsInFiles() {
        hashMap = new HashMap<>();
    }


    public void buildWordFileMap() throws IOException {
        hashMap.clear();
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    private void addWordsFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String line_in_file = "";
        while ((line_in_file = br.readLine()) != null) {
            String words_array[] = line_in_file.split("\\s+");
            for (String word : words_array) {
                if (!hashMap.containsKey(word)) {
                    ArrayList<String> filenames = new ArrayList<>();
                    filenames.add(file.getName());
                    hashMap.put(word, filenames);
                } else {
                    ArrayList<String> filenames = hashMap.get(word);
                    if (!filenames.contains(file.getName()))
                        filenames.add(file.getName());


                }
            }
        }

    }

    public int maxNumber() {
        int max = 0;
        for (String word : hashMap.keySet()) {
            int numberOfFiles = hashMap.get(word).size();
            if (numberOfFiles > max)
                max = numberOfFiles;
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordslist = new ArrayList<>();
        for (String word : hashMap.keySet()) {
            ArrayList<String> filenames = hashMap.get(word);
            if (filenames.size() == number)
                wordslist.add(word);
        }
        return wordslist;
    }

    public void printFilesIn(String word) {
        ArrayList<String> filenames = hashMap.get(word);
        System.out.println(word + " is in files :");
        for (String filename : filenames) {
            System.out.print(filename + " ");
        }
    }

    public static void main(String args[]) throws IOException {

        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
    }

    public void tester() throws IOException {
        buildWordFileMap();
        int maximumNUmberOfFiles = maxNumber();
        System.out.println("Maximum number of files any word is in: " + maximumNUmberOfFiles);
        for (String word : hashMap.keySet()) {
            if (hashMap.get(word).size() == maximumNUmberOfFiles)
                System.out.println(word + " " + hashMap.get(word));
        }
        System.out.println();
        System.out.println("The entire map: ");
        System.out.println("Words" + "       Present in files\n\n");
        for (String word : hashMap.keySet()) {
            System.out.print(word + "         ");
            printFilesIn(word);
            System.out.println();
        }
    }
}




