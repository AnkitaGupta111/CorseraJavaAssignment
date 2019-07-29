package array_list_structure_data.week1.word_length_assignment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.duke.FileResource;
public class WordLength {



        void countWordLengths(FileResource resource, int[] counts) {
            for (String s : resource.words()) {
                int len = 0;

                if (s.startsWith("\"") && s.endsWith("\"")) {
                    len = s.length() - 2;
                } else {
                    Pattern p = Pattern.compile("\\w+\\W*\\w+");
                    Matcher matcher = p.matcher(s);
                    if (matcher.find()) {
                        String str = matcher.group();
                        len = str.length();
                    }
                }

                if (len < counts.length)
                    counts[len]++;

            }

        }

        int indexOfMax(int[] counts) {
            int max = 0, in = 0;
            for (int i = 0; i < counts.length; i++) {
                if (max < counts[i]) {
                    in = i;
                    max = counts[i];
                }
            }
            return in;

        }

        static void testCountWordsLength() {
            edu.duke.FileResource fr = new FileResource();
            int[] counts = new int[31];
            WordLength wl = new WordLength();
            wl.countWordLengths(fr, counts);
            for (int i = 1; i < counts.length; i++) {
                if (counts[i] == 0)
                    continue;
                    System.out.println(counts[i] + " Words of length " + i);
            }
            System.out.println("Most Common Word Length " + wl.indexOfMax(counts));
        }

        public static void main(String[] args) {

            testCountWordsLength();
        }
    }

