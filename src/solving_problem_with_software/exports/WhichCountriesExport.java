/*
  Reads a chosen CSV file of country exports and prints each country that exports coffee.

  @author Duke Software Team
 */
package solving_problem_with_software.exports;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class WhichCountriesExport {

	public void listExporters(CSVParser parser, String exportOfInterest) {
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			//Check if it contains exportOfInterest
			if (export.contains(exportOfInterest)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}



	String countryInfo(CSVParser parser,String country)
	{

		for (CSVRecord rec:parser)
		{

			if(rec.get("Country").equalsIgnoreCase(country))
			{

				StringBuilder sb=new StringBuilder();
				int i=1;
				for(String values:rec)
				{

					sb.append(values);
					if(i==rec.size())
                      return sb.toString();
						sb.append(":");
						i++;
				}


			}

		}
return "NOT FOUND";
	}
	void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2)
	{
		for (CSVRecord rec:parser)
		{

			if(rec.get("Exports").contains(exportItem1.toLowerCase()) && rec.get("Exports").contains(exportItem2.toLowerCase()))
			{
				System.out.println(rec.get("Country"));

			}

		}

	}

	int numberOfExporters(CSVParser parser,String exportItem)
	{
		int count=0;
		for (CSVRecord rec:parser)
		{

			if(rec.get("Exports").contains(exportItem.toLowerCase()))
			{

         count++;
			}

		}
return count;
	}

	public static void main(String[] args) {

		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		WhichCountriesExport obj=new WhichCountriesExport();
		obj.listExporters(fr.getCSVParser(), "coffee");
		System.out.println(obj.countryInfo(fr.getCSVParser(),"Malawi"));
		obj.listExportersTwoProducts(fr.getCSVParser(),"tea","sugar");
		System.out.println(obj.numberOfExporters(fr.getCSVParser(),"tea"));


	}



}
