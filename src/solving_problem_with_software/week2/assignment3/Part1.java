package solving_problem_with_software.week2.assignment3;

import edu.duke.StorageResource;

public class Part1 {


    static StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int indexOfATG = dna.indexOf("ATG");
        if (indexOfATG == -1)
            return geneList;
        int startIndex = indexOfATG;
        while (true) {

            int indexOfTAA = solving_problem_with_software.week2.assignment2.Part1.findStopCodon(dna, startIndex + 3, "TAA");
            int indexOfTAG = solving_problem_with_software.week2.assignment2.Part1.findStopCodon(dna, startIndex + 3, "TAG");
            int indexOfTGA = solving_problem_with_software.week2.assignment2.Part1.findStopCodon(dna, startIndex + 3, "TGA");

            int minIndex = Math.min(indexOfTAA, indexOfTAG);
            minIndex = Math.min(minIndex, indexOfTGA);

            if (minIndex == dna.length())
                return geneList;

            geneList.add(dna.substring(indexOfATG, minIndex + 3));
            startIndex = minIndex;

        }


    }


    public static void main(String[] args) {


        StorageResource geneList = getAllGenes("abcATGdefTAAhijTAG");
        for (String gene : geneList.data())
            System.out.println(gene);
    }
}
