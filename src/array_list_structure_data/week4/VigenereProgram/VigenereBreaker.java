package array_list_structure_data.week4.VigenereProgram;

import edu.duke.FileResource;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {
    private HashMap<String, HashSet<String>> languages = new HashMap<>();
    private char[] mostCommonChar;

    VigenereBreaker() {
        readDictionary();
        initMostCommonCharList();
    }

    void initMostCommonCharList() {
        mostCommonChar=new char[languages.size()];
        int i = 0;
        for (String lang : languages.keySet()) {
            mostCommonChar[i] = mostCommonCharIn(languages.get(lang));
            i++;
        }
    }

    char getMaxFreqChar(int letterFreq[]) {
        int maxCount = 0, maxIndex = 0;
        for (int i = 0; i < letterFreq.length; i++) {
            if (letterFreq[i] > maxCount) {
                maxCount = letterFreq[i];
                maxIndex = i;
            }
        }
        return (char) ('a' + maxIndex);
    }

    char mostCommonCharIn(HashSet<String> langDictionary) {
        int freqLetter[] = new int[26];
        for (String word : langDictionary) {
            for (char ch : word.toCharArray()) {
                int ascii=ch;
                if(ascii>=97 && ascii<122)
                freqLetter[ascii-'a']++;
            }
        }
        return getMaxFreqChar(freqLetter);
    }


    public String sliceString(String message, int whichSlice, int totalSlices) {

        StringBuilder sliceStr = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i = i + totalSlices) {
            sliceStr.append(message.charAt(i));
        }

        return sliceStr.toString();


    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String sliceStrings[] = new String[klength];
        for (int i = 0; i < klength; i++) {

            sliceStrings[i] = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(sliceStrings[i]);


        }

        return key;
    }

    void readDictionary() {
        File dictFolder = new File("src/array_list_structure_data/VigenereProgram/dictionaries");

        for (File languageFile : dictFolder.listFiles()) {
            String fileName;
            String lang;
            FileResource fr = new FileResource(languageFile);
            fileName = languageFile.getName();
            int indexOfDot = fileName.indexOf(".");
            if (indexOfDot != -1) {
                lang = fileName.substring(0, indexOfDot);
            } else
                lang = fileName;

            HashSet<String> wordList = new HashSet<>();
            for (String word : fr.words()) {

                wordList.add(word.toLowerCase());
            }
            languages.put(lang, wordList);


        }

    }

    int countWord(String message, HashSet<String> dictionary) {
        int count = 0;

        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;

    }

    public String breakForLanguage(String encryptMessage, char mostCommonChar, HashSet<String> dictList) {

        int keysLn = 0;

        int maxCount = 0;
        for (int i = 1; i <=100; i++) {

            String message = breakVigenere(encryptMessage, i, mostCommonChar);

//int dKeys[]=tryKeyLength(encryptMessage,i,mostCommonChar);
            //VigenereCipher vc=new VigenereCipher(dKeys);

//String message=vc.decrypt(encryptMessage);
            int matchedWordFreq = countWord(message, dictList);
            if (matchedWordFreq > maxCount) {
                maxCount = matchedWordFreq;
                keysLn = i;
            }


        }
        return breakVigenere(encryptMessage, keysLn, mostCommonChar);


    }


    public String breakVigenere(String encryptMessage, int klength, char mostCommon) {

        int[] dKeys = tryKeyLength(encryptMessage, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(dKeys);
        return vc.decrypt(encryptMessage);

    }

    public String breakForAllLanguage(String encryptMessage) {
        int i = 0;
int maxCount=0;
String langKey=null;
int langIndex=0;
        for (String lang : languages.keySet()) {
            char mostCommon = mostCommonChar[i];
           String message =breakForLanguage(encryptMessage, mostCommon, languages.get(lang));

            int matchedWordFreq = countWord(message, languages.get(lang));
            if (matchedWordFreq > maxCount) {
                maxCount = matchedWordFreq;
                langKey=lang;
                langIndex=i;
            }

            i++;
        }

return breakForLanguage(encryptMessage,mostCommonChar[langIndex],languages.get(langKey));
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource();

        String encryptMessage = fr.asString();

        System.out.println(new VigenereBreaker().breakForAllLanguage(encryptMessage));
    }
}