package array_list_structure_data.week3.WebLogProgram;

/**
 *  class LogAnalyzer
 * 
 * @author Ankita
 * @version (a version number or a date)
 */

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.duke.FileResource;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
        records=new ArrayList<LogEntry>();
     FileResource fr=new FileResource();
     readFile(fr.asString());
     }
        
     public void readFile(String fileContent) {
for (String logEntry:fileContent.split("\\n"))
{
    LogEntry le=WebLogParser.parseEntry(logEntry);
records.add(le);
}
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
   int countUniqueIPs()
   {
       HashSet<String> uniqueIps=new HashSet<String>();
       for (LogEntry le:records)
       {
           if(!uniqueIps.contains(le.getIpAddress()))
           {
               uniqueIps.add(le.getIpAddress());
           }
       }
return uniqueIps.size();
   }

    void printAllHigherThanNum(int statusCode)
    {

        for (LogEntry le:records)
        {
            if(le.getStatusCode()>statusCode)
                System.out.println(le);
        }
    }

   ArrayList<String> uniqueIPVisitsOnDay(String someday)
   {

       ArrayList<String> ipsList=new ArrayList<String>();
      SimpleDateFormat formatter=new SimpleDateFormat("MMM dd");

       for (LogEntry le:records)
       {
           String someDate = formatter.format(le.getAccessTime());
           System.out.println(someDate);
           if(someDate.equalsIgnoreCase(someday))
           {

               if(!ipsList.contains(le.getIpAddress()))
            ipsList.add(le.getIpAddress()) ;
           }

       }
return ipsList;
   }

    int countUniqueIPsInRange(int low,int high)
    {

        ArrayList<String> ipsList=new ArrayList<String>();
        for (LogEntry le:records)
        {

            if(le.getStatusCode()>=low && le.getStatusCode()<=high)
            {
                if(!ipsList.contains(le.getIpAddress()))
                    ipsList.add(le.getIpAddress()) ;
            }

        }


return ipsList.size();
    }

    HashMap<String,Integer>countVisitsPerIP()
    {
        HashMap<String,Integer> uniqIpsVisit=new HashMap<String,Integer>();
        for (LogEntry le:records)
        {
                if(!uniqIpsVisit.containsKey(le.getIpAddress()))
                    uniqIpsVisit.put(le.getIpAddress(),1) ;
                else
                {
                    uniqIpsVisit.put(le.getIpAddress(),uniqIpsVisit.get(le.getIpAddress())+1) ;
                }

        }
        return uniqIpsVisit;

    }
    int mostNumberVisitsByIP(HashMap<String,Integer> ipVists)
    {
        int mostNumberVisitsCount=0;
        for (String ipKey:ipVists.keySet())
        {
            if(ipVists.get(ipKey)>mostNumberVisitsCount)
            {
                mostNumberVisitsCount=ipVists.get(ipKey);
            }
        }
        return mostNumberVisitsCount;
    }
   ArrayList<String> ipsMostVisits(HashMap<String,Integer> ipVists)
   {
       int mostNumberVisitsCount=0;
       ArrayList<String> ipsMostVisits=new ArrayList<>();
       for (String ipKey:ipVists.keySet())
       {
           if(ipVists.get(ipKey)>mostNumberVisitsCount)
           {
               ipsMostVisits.clear();
               mostNumberVisitsCount=ipVists.get(ipKey);
               ipsMostVisits.add(ipKey);
           }
           else
               if(ipVists.get(ipKey)==mostNumberVisitsCount)
           {
               mostNumberVisitsCount=ipVists.get(ipKey);
               ipsMostVisits.add(ipKey);
           }
       }
return ipsMostVisits;
   }

   HashMap<String,ArrayList<String>>ipsForDays()
   {
       HashMap<String ,ArrayList<String>> ipsForDaysInLog=new HashMap<>();
for(LogEntry le:records)
       {
           SimpleDateFormat formatter=new SimpleDateFormat("MMM dd");
           String accessDate = formatter.format(le.getAccessTime());
           if(!ipsForDaysInLog.containsKey(accessDate))
           {
               ipsForDaysInLog.put(accessDate,new ArrayList<String>(Arrays.asList(le.getIpAddress())));
           }
           else
           {
               ipsForDaysInLog.get(accessDate).add(le.getIpAddress());

           }

       }
return ipsForDaysInLog;

   }

  String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIpsMap)
  {
      int countMostIPVisits=0;
      String day=null;
for (String acessDate:dayIpsMap.keySet())
{
    int countIps=0;

    if((countIps=dayIpsMap.get(acessDate).size())>countMostIPVisits)
    {
        countMostIPVisits=countIps;
        day=acessDate;
    }
}
return day;
  }
    ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String accessDate)
    {
       HashMap<String,Integer>ipsWithCountRequests=new HashMap<>();
       for (String ip:map.get(accessDate))
       {
           if(!ipsWithCountRequests.containsKey(ip))
           {
               ipsWithCountRequests.put(ip,1);
           }
           else
           {
               ipsWithCountRequests.put(ip,ipsWithCountRequests.get(ip)+1);
           }
       }
       return ipsMostVisits(ipsWithCountRequests);

    }


}
