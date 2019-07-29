/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
package solving_problem_with_software.hottestTempManyRefactored;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVMax {
	public CSVRecord hottestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}

	public void testHottestInDay() {
		FileResource fr = new FileResource("src/solving_problem_with_software/hottestTempManyRefactored/data/2015/weather-2015-01-01.csv");
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				" at " + largest.get("TimeEST"));
	}

	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			// use method to compare two records
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}

	public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
		//If largestSoFar is nothing
		if (largestSoFar == null) {
			largestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp > largestTemp) {
				//If so update largestSoFar to currentRow
				largestSoFar = currentRow;
			}
		}
		return largestSoFar;
	}

	public void testHottestInManyDays() {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				" at " + largest.get("DateUTC"));
	}

	public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {

		if (smallestSoFar == null && Double.parseDouble(currentRow.get("TemperatureF")) != -9999) {
			smallestSoFar = currentRow;
		} else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp < largestTemp && currentTemp != -9999) {
				//If so update smallestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}

	CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord smallestTemp = null;
		for (CSVRecord currRow : parser) {
			smallestTemp = getSmallestOfTwo(currRow, smallestTemp);
		}

		return smallestTemp;

	}

	public void fileWithColdestTemperature() {
	 DirectoryResource dr=new DirectoryResource();
	 CSVRecord smallest=null;
	 File fileColdestDay=null;
	 for (File file:dr.selectedFiles())
	 {FileResource fr=new FileResource(file);
		 CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

		 smallest = getSmallestOfTwo(currentRow, smallest);
		 if(smallest==currentRow)
		 	fileColdestDay=file;
	 }
		System.out.println("coldest day was in file "+fileColdestDay.getName());
		System.out.println("Coldest temperature on that day was "+smallest.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were:");

		String fileNameWithColdestDay=fileColdestDay.getName();
		Pattern p=Pattern.compile("[0-9]+-[0-9]+-[0-9]+");
		Matcher m=p.matcher(fileNameWithColdestDay);
		String date=null;
if(m.find())
{
date=m.group();

}
		for (CSVRecord rec:new FileResource(fileColdestDay).getCSVParser())
		{

			System.out.println(date+" "+rec.get("TemperatureF"));

	 }

	}
	double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
	{
		int i=0;
		double avg=0;
		double temp=0;
for (CSVRecord rec:parser)
{int humi=0;
	String humidity=rec.get("Humidity");
	if(!humidity.equals("N/A")) {
		humi = Integer.parseInt(humidity);

		if (humi >= value) {
			temp = Double.parseDouble(rec.get("TemperatureF"));
			avg += temp;
			i++;
		}
	}
}
return avg/i;
	}

	public void testColdestInDay() {
		FileResource fr = new FileResource("src/solving_problem_with_software/hottestTempManyRefactored/data/2015/weather-2015-01-01.csv");
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("smallest temperature was " + coldest.get("TemperatureF") +
				" at " + coldest.get("TimeEST"));
	}
	public void testColdestdDayInManyFiles(){
		fileWithColdestTemperature();
	}
	void testAverageTemperatureWithHighHumidityInFile()
	{
		FileResource fr = new FileResource("src/solving_problem_with_software/hottestTempManyRefactored/data/2015/weather-2015-01-01.csv");
		double avTemp=averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
		System.out.println(avTemp==0?"NO temperatures with that humidity":"Average Temp when high Humidity is "+avTemp);
	}
	public static void main(String[] args) {
		CSVMax obj=new CSVMax();
		obj.testAverageTemperatureWithHighHumidityInFile();
		obj.testColdestdDayInManyFiles();
		obj.testColdestInDay();
		obj.testHottestInDay();
		obj.testHottestInManyDays();

	}
}
