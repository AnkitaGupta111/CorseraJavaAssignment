package solving_problem_with_software.week2.assignment1;

public class Part1 {
    static String findSimpleGene(String dna){
    int positionOfATG = dna.indexOf("ATG");
    if(positionOfATG == -1)
        return "";
    else{
        int positionOfTAA = dna.indexOf("TAA",positionOfATG+3);
        if(positionOfTAA == -1)
            return "";
        else{
            int lenOfSubstrBetweenATgAngTAA = (positionOfTAA) - (positionOfATG);
            if(lenOfSubstrBetweenATgAngTAA%3 == 0)
                return dna.substring(positionOfATG, positionOfTAA+3);
        }

    }
    return "";

}


    public static void main(String[] args){
        System.out.println(findSimpleGene("QATGGWERATTAAB"));
    }
}
