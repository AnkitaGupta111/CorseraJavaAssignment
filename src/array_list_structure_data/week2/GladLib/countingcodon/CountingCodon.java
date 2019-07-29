package array_list_structure_data.week2.GladLib.countingcodon;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class CountingCodon {
    private HashMap<String, Integer> hashMap;

    CountingCodon() {
        hashMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {

        hashMap.clear();
        int i;
      for (i=start+3;i<=dna.length();i=i+3)
      {
            String codon = dna.substring(i-3, i);
            if (!hashMap.containsKey(codon)) {
                hashMap.put(codon, 1);
            } else
                hashMap.put(codon, hashMap.get(codon) + 1);
        }
        System.out.println("Number of unique codons=" + hashMap.size());
        System.out.println("Most common codon: " + getMostCommonCodon());
    }

    public String getMostCommonCodon() {
        int max = 0;
        String mostFrequentCodon = "";
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) > max) {
                max = hashMap.get(key);
                mostFrequentCodon = key;
            }
        }
        return mostFrequentCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String key : hashMap.keySet()) {
            int codon_count = hashMap.get(key);
            if (codon_count >= start && codon_count <= end) {
                System.out.println(key + " " + codon_count);
            }
        }
    }

    public void tester(String file_path) throws IOException {
        File file = new File(file_path);
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String dna_strand = br.readLine();
        dna_strand = dna_strand.toUpperCase().trim();
        for (int i = 0; i <= 2; i++) {
            buildCodonMap(i, dna_strand);
            printCodonCounts(1, 3);
        }
    }

    public static void main(String args[]) throws IOException {
        CountingCodon counting_codons = new CountingCodon();
        String file_name="src/array_list_structure_data/week2/GladLib/countingcodon/smalldna";
        counting_codons.tester(file_name);
    }
}

