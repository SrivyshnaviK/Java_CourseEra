package com.Vyshnavi.WebServerLogs;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records=new ArrayList<>();
     }
        
     public void readFile(String filename) {
        FileResource fileResource=new FileResource();
         for (String line:fileResource.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
