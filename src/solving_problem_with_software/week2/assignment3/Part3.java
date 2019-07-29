package solving_problem_with_software.week2.assignment3;


import edu.duke.FileResource;
import edu.duke.StorageResource;


public class Part3 {


    static StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int indexOfATG = dna.indexOf("ATG");
        if (indexOfATG == -1)
            return geneList;
        int startIndex = indexOfATG;
        while (startIndex < dna.length()) {

            int indexOfTAA = solving_problem_with_software.week2.assignment2.Part1.findStopCodon(dna, startIndex, "TAA");
            int indexOfTAG = solving_problem_with_software.week2.assignment2.Part1.findStopCodon(dna, startIndex, "TAG");
            int indexOfTGA = solving_problem_with_software.week2.assignment2.Part1.findStopCodon(dna, startIndex, "TGA");

            int minIndex = Math.min(indexOfTAA, indexOfTAG);
            minIndex = Math.min(minIndex, indexOfTGA);

            if (minIndex == dna.length())
                return geneList;

            geneList.add(dna.substring(indexOfATG, minIndex + 3));

            startIndex = minIndex + 3;
        }
        return geneList;

    }

    static float cgRatio(String dna) {
        int numOfCs = 0;
        int numOfGs = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C')
                numOfCs++;
            if (dna.charAt(i) == 'G')
                numOfGs++;
        }
        if (numOfGs == 0)
            return -1;
        return (float) numOfCs / numOfGs;


    }

    static void processGenes(StorageResource sr) {
        System.out.println("Genes Longer than 60 characters : ");
        int LongrThan60Char = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                LongrThan60Char++;
                System.out.println(gene);
            }
        }
        System.out.println("\nNum of genes with length longer than 60 = " + LongrThan60Char);

        System.out.println("Genes with cgratio greater than 0.35 : ");
        int cgRatioGtrThan0_35 = 0;
        for (String gene : sr.data()) {
            if (cgRatio(gene) > 0.35) {
                cgRatioGtrThan0_35++;
                System.out.print(gene + "   ");
            }
        }
        System.out.println("\nNum of genes with cgRatio greater than 0.35 = " + cgRatioGtrThan0_35);

        System.out.println("Longest Gene");
        int max = 0;
        String longestGene = "";
        for (String gene : sr.data()) {
            if (gene.length() > max) {
                max = gene.length();
                longestGene = gene;
            }
        }
        System.out.println(longestGene);

    }

    static void testProcessGenes() {
        FileResource fr = new FileResource("src/solving_problem_with_software/week2/dna/brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println(dna);
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);

    }

    public static void main(String[] args) {
        testProcessGenes();
    }
}

