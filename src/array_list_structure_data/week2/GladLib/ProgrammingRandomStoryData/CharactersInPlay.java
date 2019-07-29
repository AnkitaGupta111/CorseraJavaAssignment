package array_list_structure_data.week2.GladLib.ProgrammingRandomStoryData;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> namesOfCharacters;
    private ArrayList<Integer> countsOfCharacters;

    CharactersInPlay() {
        this.namesOfCharacters = new ArrayList<>();
        this.countsOfCharacters = new ArrayList<>();
    }

    void update(String person) {
        if (!namesOfCharacters.contains(person)) {
            namesOfCharacters.add(person);
            countsOfCharacters.add(1);
        } else {
            int index = namesOfCharacters.indexOf(person);
            countsOfCharacters.set(index, countsOfCharacters.get(index) + 1);
        }
    }

    void findAllCharacters() throws IOException {
        namesOfCharacters.clear();
        countsOfCharacters.clear();
        File file = new File("src/array_list_structure_data/week2/GladLib/ProgrammingRandomStoryData/macbeth.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() != 0) {
                int indexOfFirstPeriod = line.indexOf('.');
                if (indexOfFirstPeriod != -1)
                    update(line.substring(0, indexOfFirstPeriod).trim());
            }
        }
    }

    void tester() throws IOException {
        findAllCharacters();
        int i = 0;
        double max = 0;
        for (i = 0; i < countsOfCharacters.size(); i++) {
            if (countsOfCharacters.get(i) > max)
                max = countsOfCharacters.get(i);
        }
        int minimumNumberOfDialogues = (int) Math.ceil(max / 2);
        int maximumNUmberOfDialogues = (int) max;
        for (i = 0; i < countsOfCharacters.size(); i++) {
            if (countsOfCharacters.get(i) >= minimumNumberOfDialogues)
                System.out.println(namesOfCharacters.get(i) + " " + countsOfCharacters.get(i));
        }
        charactersWithNumParts(minimumNumberOfDialogues, maximumNUmberOfDialogues);
    }

    void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < countsOfCharacters.size(); i++) {
            if (countsOfCharacters.get(i) >= num1 && countsOfCharacters.get(i) <= num2) {
                System.out.println(namesOfCharacters.get(i));
            }
        }
    }

    public static void main(String args[]) throws IOException {

        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.findAllCharacters();
        charactersInPlay.tester();
    }


}
