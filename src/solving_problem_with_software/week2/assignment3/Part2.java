package solving_problem_with_software.week2.assignment3;

public class Part2 {

    static float cgRatio(String dna) {
        int numOfCGs = 0;

        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
                numOfCGs++;

        }

        return (float) numOfCGs / dna.length();

    }

    public static void main(String[] args) {

        System.out.println(cgRatio("abCGabCCabG"));
    }
}
