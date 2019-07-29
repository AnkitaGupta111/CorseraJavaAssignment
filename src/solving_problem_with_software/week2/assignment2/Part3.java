package solving_problem_with_software.week2.assignment2;

public class Part3 {

    static void countGene(String dna) {
        String dnaStr = dna;
        int numOfGenes = 0;
        String gene = Part1.findGene(dna);
        while (!gene.equals("")) {
            numOfGenes++;
            int indexOfGene = dna.indexOf(gene);

            dna = dna.substring(indexOfGene + gene.length());
            gene = Part1.findGene(dna);
        }

        System.out.println("Number of genes in " + dnaStr + " = " + numOfGenes);
    }

    static void printAllGenes(String dna) {
        System.out.println("All the genes present in " + dna + " are");
        String gene = Part1.findGene(dna);
        while (!gene.equals("")) {

            System.out.println(gene);
            int indexOfGene = dna.indexOf(gene);
            dna = dna.substring(indexOfGene + gene.length());
            gene = Part1.findGene(dna);
        }
    }


    static void testCountGenes() {

        countGene("abcATGdefgTAAhiTAG");
        printAllGenes("abcATGdefgTAAhiTAG");

        countGene("aATGTAAGATGCCCTAGT");
        printAllGenes("aATGTAAGATGCCCTAGT");
    }


    public static void main(String[] args) {
        testCountGenes();

    }
}
