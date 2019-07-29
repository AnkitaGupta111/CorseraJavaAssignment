package solving_problem_with_software.week2.assignment1;

public class Part2 {
    static String findSimpleGene(String dna,String startCodon,String stopCodon){
        int positionofStartCodon = dna.toLowerCase().indexOf(startCodon.toLowerCase());
        if(positionofStartCodon == -1)
            return "";
        else{
            int positionOfStopCodon = dna.toLowerCase().indexOf(stopCodon.toLowerCase(),positionofStartCodon);
            if(positionOfStopCodon == -1)
                return "";
            else{
                int lenOfSubstrBtwStartAndStopCodon = (positionofStartCodon) - (positionOfStopCodon);
                if(lenOfSubstrBtwStartAndStopCodon%3 == 0)
                    return dna.substring(positionofStartCodon, positionOfStopCodon+3);
            }

        }
        return "";

    }

    static void testSimpleGene(){
        String[] dnaStrings = {"QWERTYTAA" , "ATTTAA" , "atg234dfdtaa" ,
                "QATGWERTAAB", "QATG1234TAA"
        };
        for(String dna:dnaStrings)
            System.out.println(dna +" - " +findSimpleGene(dna,"ATG","TAA"));
    }

    public static void main(String[] args){
        testSimpleGene();
    }
}







