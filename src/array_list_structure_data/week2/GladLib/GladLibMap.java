package array_list_structure_data.week2.GladLib;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class GladLibMap {
    HashMap<String, ArrayList<String>> labelToListMap;
    private ArrayList<String> categories;
    private ArrayList<String> usedWordList;
    private Random myRandom;
    int toatalReplacedWord = 0;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "src/array_list_structure_data/week2/GladLib/data";

    public GladLibMap() {
        labelToListMap = new HashMap<>();
        categories = new ArrayList<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWordList = new ArrayList<String>();
    }

    public GladLibMap(String source) {
        initializeFromSource(source);
        myRandom = new Random();
        usedWordList = new ArrayList<>();
    }

    private void initializeFromSource(String source) {
        String labels[] = {"adjective", "animal", "color", "country", "fruit", "name", "verb", "noun", "timeframe"};
        for (String label : labels) {

            labelToListMap.put(label, readIt(source + "/" + label + ".txt"));

        }
    }

    boolean isWordUsed(String word) {
        if (usedWordList.indexOf(word) == -1) {
            usedWordList.add(word);
            return false;
        }
        return true;
    }

    private String randomFrom(ArrayList<String> source) {
        String randomWord;
        int index = myRandom.nextInt(source.size());
        randomWord = source.get(index);

        while (isWordUsed(randomWord) == true) {
            index = myRandom.nextInt(source.size());
            randomWord = source.get(index);
        }
        return randomWord;
    }

    private String getSubstitute(String label) {
        String substitue;

        if (label.equals("number")) {
            substitue = "" + myRandom.nextInt(50) + 5;
        } else {
            substitue = randomFrom(labelToListMap.get(label));
            if (substitue == null)
                return "**UNKNOWN**";
            if (!categories.contains(label))
                categories.add(label);
        }


        return substitue;
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        //String prefix = w.substring(0,first);
        //String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first + 1, last));
        toatalReplacedWord++;
        return sub;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        usedWordList.clear();
        categories.clear();
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }

        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("src/array_list_structure_data/week2/GladLib/data/madtemplate2.txt");
        printOut(story, 60);

        System.out.println("\n\ntotal replaced word " + toatalReplacedWord);
    }

    public int totalWordsInMap() {
        int count = 0;
        for (String key : labelToListMap.keySet()) {
            count += labelToListMap.get(key).size();
        }
        return count;
    }

    public int totalWordsConsidered() {
        int total = 0;
        for (String category : categories) {
            total += (labelToListMap.get(category).size());
        }
        return total;
    }

    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
        System.out.println("total words considered " + gl.totalWordsConsidered());

        System.out.println("total words of replace " + gl.totalWordsInMap());

    }

}
