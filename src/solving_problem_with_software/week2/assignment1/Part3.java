package solving_problem_with_software.week2.assignment1;

public class Part3 {

    static boolean twoOccurrences(String stringa, String stringb) {
        System.out.println(stringa + " " + stringb);
        if (stringb.contains(stringa)) {
            int positionOfStringA = stringb.indexOf(stringa);
            if (stringb.indexOf(stringa, positionOfStringA + stringa.length()) != -1) {

                return true;
            }
        }
        return false;
    }

    static String lastPart(String stringa, String stringb) {

        if (stringb.contains(stringa)) {
            int positionOfStringA = stringb.indexOf(stringa);
            return stringb.substring(positionOfStringA + stringa.length());
        }
        return stringb;
    }

    static void testTwoOccurrences() {
        System.out.println("Occurences");
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));


        System.out.println(lastPart("by", "A story by Abby Long"));
        System.out.println(lastPart("a", "banana"));
        System.out.println(lastPart("attg", "ctgtatgta"));


    }

    public static void main(String[] args) {
        testTwoOccurrences();
    }
}