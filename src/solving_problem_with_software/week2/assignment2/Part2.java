package solving_problem_with_software.week2.assignment1.assignment2;

public class Part2 {

    static void howMany(String stringA, String stringB) {
        int numOfOccurences = 0;
        int pos = stringB.indexOf(stringA);
        while (pos != -1) {
            numOfOccurences++;
            pos = stringB.indexOf(stringA, pos + stringA.length());

        }

        System.out.println("Number of times " + stringA + " occurs in " + stringB);
        System.out.println(numOfOccurences);
    }

    static void testHowMany() {
        howMany("GAA", "ATGAACGAATTGAATC");
        howMany("AA", "ATAAAA");
        howMany("OL", "SHERLOCK HOLMES");
    }

    public static void main(String[] args) {
        testHowMany();
    }

}
