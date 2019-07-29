package array_list_structure_data.week2.GladLib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    WordFrequencies() {
        this.myWords = new ArrayList<>();
        this.myFreqs = new ArrayList<>();
    }

    void findUnique() throws IOException {
        myWords.clear();
        myFreqs.clear();
        File file = new File("src/array_list_structure_data/week2/GladLib/ProgrammingRandomStoryData/testwordfreqs.txt");
        FileReader file_reader = new FileReader(file);
        BufferedReader br = new BufferedReader(file_reader);
        String line_in_file;
        while ((line_in_file = br.readLine()) != null) {
            String words_array[] = line_in_file.split(" ");
            for (int i = 0; i < words_array.length; i++) {
                words_array[i] = words_array[i].toLowerCase();
                if (!myWords.contains(words_array[i])) {
                    myWords.add(words_array[i]);
                    myFreqs.add(1);
                } else {
                    int index = myWords.indexOf(words_array[i]);
                    myFreqs.set(index, (myFreqs.get(index) + 1));
                }
            }
        }
    }

    void tester() throws IOException {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int highest_frquency = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are " + myWords.get(highest_frquency) + " " + myFreqs.get(highest_frquency));
    }

    int findIndexOfMax() {
        int max = 0;
        int index = -1;
        for (int i = 0; i < myFreqs.size(); i++) {
            int frequency = myFreqs.get(i);
            if (frequency > max) {
                max = frequency;
                index = i;
            }
        }
        return index;
    }

    public static void main(String args[]) throws IOException {


        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.findUnique();
        wordFrequencies.tester();
    }


}
