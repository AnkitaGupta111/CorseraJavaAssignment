/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Duke Software Team
 */
package solving_problem_with_software.babyNamesTotals;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;
import java.lang.Math;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalGirlsName = 0;
        int totalBoysName = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalNames++;
            int numBorn = Integer.parseInt(rec.get(2));

            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysName++;
            } else {
                totalGirls += numBorn;
                totalGirlsName++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total Names = " + totalNames);
        System.out.println("female girls names = " + totalGirlsName);
        System.out.println("male boys names= " + totalBoysName);
    }


    public int getRank(int year, String name, String gender) {

        String folderPath = "src/solving_problem_with_software/babyNamesTotals/data/";
        FileResource fr = new FileResource(folderPath + "yob" + year + ".csv");


        int currRank = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                currRank++;
                if (rec.get(0).equalsIgnoreCase(name)) {
                    return currRank;
                }
            }
        }

        return -1;

    }

    public String getName(int year, int rank, String gender) {

        String folderPath = "src/solving_problem_with_software/babyNamesTotals/data/";
        FileResource fr = new FileResource(folderPath + "yob" + year + ".csv");

        int currRank = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                currRank++;
                if (currRank == rank) {
                    return rec.get(0);
                }
            }

        }
        return "NO Name";
    }

    void whatIsNameInYear(int year, String name, String gender, int newYear) {

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if " + (gender.equals("M") ? "he" : "she") + " was born in " + newYear + ".");
    }

    int yearOfHighestRank(String name, String gender) {
        DirectoryResource d = new DirectoryResource();
        int highestRank = -1, yearHighestRank = -1;
        int currYear = 0;
        for (File file : d.selectedFiles()) {
            String fileName = file.getName();
            Pattern digitsPattern = Pattern.compile("\\d+");
            Matcher matcher = digitsPattern.matcher(fileName);
            if (matcher.find()) {
                currYear = Integer.parseInt(matcher.group());
            }

            int currRank = getRank(currYear, name, gender);
            if (highestRank == -1) {
                highestRank = currRank;
                yearHighestRank = currYear;
            } else {
                if (highestRank > currRank) {
                    highestRank = currRank;
                    yearHighestRank = currYear;
                }
            }

        }
        return yearHighestRank;
    }

    double getAverageRank(String name,String gender) {

        DirectoryResource d = new DirectoryResource();
        int countFiles = 0;
        int sumRank=0;
        int year=0;

        for (File file : d.selectedFiles()) {
            countFiles++;
            String fileName = file.getName();
            Pattern digitsPattern = Pattern.compile("\\d+");
            Matcher matcher = digitsPattern.matcher(fileName);
            if (matcher.find()) {
                year = Integer.parseInt(matcher.group());
            }

            int currRank = getRank(year, name, gender);

            if(currRank!=-1)
                sumRank+=currRank;

            }
        if(sumRank!=0)
        {
          return Math.round((double)sumRank/countFiles * 100) / 100.0;

        }
return -1.0;
        }
    int getTotalBirthsRankedHigher(int year,String name,String gender) {

        String folderPath = "src/solving_problem_with_software/babyNamesTotals/data/";
        FileResource fr = new FileResource(folderPath + "yob" + year + ".csv");


        int totalBirthRankedHigher = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {

                if (rec.get(0).equalsIgnoreCase(name)) {
                    return totalBirthRankedHigher;
                }
                int currBirths = Integer.parseInt(rec.get(2));
                totalBirthRankedHigher += currBirths;

            }
        }
return 0;
    }


    public void testTotalBirths() {
        //FileResource fr = new FileResource();
        //FileResource fr = new FileResource("src/solving_problem_with_software/babyNamesTotals/data/yob2014.csv");

        //totalBirths(fr);
        System.out.println(getRank(2014, "Mason", "M"));
        System.out.println(getName(2014, 3, "M"));
        whatIsNameInYear(1880, "A", "M", 2014);
       // System.out.println(yearOfHighestRank("M", "F"));
      //  System.out.println(getAverageRank("Ava","F"));
        System.out.println(getTotalBirthsRankedHigher(2014,"s","F"));
    }


    public static void main(String[] args) {
        BabyBirths babyBirths = new BabyBirths();
        babyBirths.testTotalBirths();

    }
}