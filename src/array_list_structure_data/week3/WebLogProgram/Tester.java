package array_list_structure_data.week3.WebLogProgram;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        ArrayList<String> ipsList=new ArrayList<String>();
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {

       LogAnalyzer la =new LogAnalyzer();
       la.printAll();
       System.out.println( "count unique ips "+la.countUniqueIPs());
     la.printAllHigherThanNum(200);
       System.out.println("unique ips visited :" +la.uniqueIPVisitsOnDay("Sep 14"));
        System.out.println("uniqueIps in range: "+la.countUniqueIPsInRange(200,299));
        HashMap<String,Integer> mapIpToCount=la.countVisitsPerIP();
        System.out.println("ips count:"+mapIpToCount);
        System.out.println("highest times visit by ip "+ la.mostNumberVisitsByIP(mapIpToCount));
        ArrayList<String> ipsListMostVisited=la.ipsMostVisits(mapIpToCount);
        System.out.println(ipsListMostVisited);
HashMap<String,ArrayList<String>> mapDayToIpList=la.ipsForDays();
        System.out.println(mapDayToIpList);
        System.out.println("day on which highest requests encountered : "+la.dayWithMostIPVisits(mapDayToIpList));
        System.out.println("ip list visited most "+la.iPsWithMostVisitsOnDay(mapDayToIpList,"Sep 14"));

    };

    public static void main(String[] args) {
        Tester tester=new Tester();
        tester.testLogAnalyzer();

    }
}
