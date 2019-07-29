package solving_problem_with_software.week2.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Part4 {
    static void checkYoutube() throws IOException {
        URL url = new URL("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            String temp = inputLine.toLowerCase();
            if(temp.contains("www.youtube.com")) {
                int posOfYoutube = temp.indexOf("www.youtube.com");
                String link = inputLine.substring(inputLine.lastIndexOf("\"",posOfYoutube)+1,
                        inputLine.indexOf("\"",posOfYoutube));
                System.out.println(link);
            }
        }

    }

    public static void main(String[] args) throws IOException{
       checkYoutube();
    }
}
