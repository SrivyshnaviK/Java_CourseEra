package com.Vyshnavi.CSV.temperature;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVAssignment {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord SmallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            SmallestSoFar = getSmallestOfTwo(currentRow, SmallestSoFar);
        }
        return SmallestSoFar;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("src/com/Vyshnavi/CSV/temperature/nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
                " at " + smallest.get("TimeEDT"));
    }
    public String fileWithColdestTemperature(){
        CSVRecord SmallestSoFar = null;
        File fName=null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (SmallestSoFar == null) {
                SmallestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(SmallestSoFar.get("TemperatureF"));
                if (currentTemp < largestTemp) {
                    SmallestSoFar = currentRow;
                    fName=f; } } }
        return fName.getName();
    }
    public void testFileWithColdestTemperature()
    {
        String fileName = fileWithColdestTemperature();
        FileResource fr = new FileResource("src/com/Vyshnavi/CSV/temperature/nc_weather/2013/"+fileName);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file "+fileName);
        System.out.println("Coldest temperature on that day was "+coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord csvRecord:fr.getCSVParser()) {
            System.out.println(csvRecord.get("DateUTC")+":"+csvRecord.get("TemperatureF"));
        }

    }
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord SmallestSoFar) {
        if (SmallestSoFar == null) {
            SmallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(SmallestSoFar.get("TemperatureF"));
            if (currentTemp < largestTemp) {
                SmallestSoFar = currentRow;
            }
        }
        return SmallestSoFar;
    }
    public CSVRecord lowestHumidityInFile(CSVParser csvParser) {
        CSVRecord SmallestSoFar = null;
        for (CSVRecord currentRow : csvParser) {
            if (SmallestSoFar == null) {
                SmallestSoFar = currentRow;
            } else {
                if(currentRow.get("Humidity").equals("N/A"))
                    continue;
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double smallestHumidity = Double.parseDouble(SmallestSoFar.get("Humidity"));
                if (currentHumidity < smallestHumidity)
                    SmallestSoFar = currentRow;
            }
        }
        return SmallestSoFar;
    }
    public void testLowestHumidityInFile(){
        FileResource fileResource=new FileResource("src/com/Vyshnavi/CSV/temperature/nc_weather/2014/weather-2014-07-22.csv");
        CSVParser csvParser=fileResource.getCSVParser();
        CSVRecord csv=lowestHumidityInFile(csvParser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord SmallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (SmallestSoFar == null) {
                SmallestSoFar = currentRow;
            } else {
                if(currentRow.get("Humidity").equals("N/A"))
                    continue;
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                //System.out.println(currentHumidity);
                double smallestHumidity = Double.parseDouble(SmallestSoFar.get("Humidity"));
                if (currentHumidity < smallestHumidity)
                    SmallestSoFar = currentRow;
            } }
        return SmallestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser csvParser){
        double avg=0;
        int count=0;
        for (CSVRecord currentRow : csvParser) {
            avg=avg+Double.parseDouble(currentRow.get("TemperatureF"));
            count++;
        }
        return avg/count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fileResource=new FileResource("src/com/Vyshnavi/CSV/temperature/nc_weather/2013/weather-2013-08-10.csv");
        System.out.println("Average temperature in file is "+averageTemperatureInFile(fileResource.getCSVParser()));
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser csvParser,int value){
        double avg=0;
        int count=0;
        for (CSVRecord currentRow : csvParser) {
            if(currentRow.get("Humidity").equals("N/A"))
                continue;
            double humidity = Double.parseDouble(currentRow.get("Humidity"));
            if(humidity>=value){
            avg=avg+Double.parseDouble(currentRow.get("TemperatureF"));;
            count++;}
        }
        if(count==0)
            return 0;
        return avg/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fileResource=new FileResource("src/com/Vyshnavi/CSV/temperature/nc_weather/2013/weather-2013-09-02.csv");
        double temperature=averageTemperatureWithHighHumidityInFile(fileResource.getCSVParser(),80);
        if(temperature==0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average temperature when high Humidity is "+temperature);
    }
    public static void main(String[] args) {
        CSVAssignment csvAssignment=new CSVAssignment();
        //csvAssignment.testColdestHourInFile();
        csvAssignment.testFileWithColdestTemperature();
        //csvAssignment.testLowestHumidityInFile();
        //csvAssignment.testLowestHumidityInManyFiles();
        //csvAssignment.testAverageTemperatureInFile();
        //csvAssignment.testAverageTemperatureWithHighHumidityInFile();

    }

}
