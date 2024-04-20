import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord LowestTemperatureRow = null;
        for (CSVRecord CurrentRow : parser){
            if (CurrentRow.get("TemperatureF").equals("-9999")){
                continue;
            }
            if (LowestTemperatureRow == null){
                LowestTemperatureRow = CurrentRow;
            }
            else {
                double CurrentRowTemperature = 
                Double.parseDouble(CurrentRow.get("TemperatureF"));
                double LowestTemperature =
                Double.parseDouble(LowestTemperatureRow.get("TemperatureF"));
                if (CurrentRowTemperature < LowestTemperature){
                    LowestTemperatureRow = CurrentRow;
                }
            }
        }
        //System.out.println(LowestTemperatureRow.get("TemperatureF"));
        return LowestTemperatureRow;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        coldestHourInFile(parser);
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord LowestTemperatureRow = null;
        String ColdestFileName = "";
        File ColdestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord CurrentRow = coldestHourInFile(parser);
            if (LowestTemperatureRow == null){
                LowestTemperatureRow = CurrentRow;
                ColdestFileName = f.getName();
                ColdestFile = f;
            }
            else {
                double CurrentRowTemperature = 
                Double.parseDouble(CurrentRow.get("TemperatureF"));
                double LowestTemperature =
                Double.parseDouble(LowestTemperatureRow.get("TemperatureF"));
                if (CurrentRowTemperature < LowestTemperature){
                    LowestTemperatureRow = CurrentRow;
                    ColdestFileName = f.getName();
                    ColdestFile = f;
                }
            }
        }
        
        System.out.println("Coldest day was in file " + ColdestFileName);
        
        FileResource fr = new FileResource(ColdestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowesttemprow = coldestHourInFile(parser);
        double coldesttemperature = Double.parseDouble
        (lowesttemprow.get("TemperatureF"));
        System.out.println("Coldest temperature on that day was " + coldesttemperature);
        
        parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord row : parser){
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
        return ColdestFileName;
    }
    
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord LowestHumidityRow = null;
        for (CSVRecord CurrentRow : parser){
            if (CurrentRow.get("Humidity").equals("N/A")){
                continue;
            }
            LowestHumidityRow = 
            comparetworecords(LowestHumidityRow, CurrentRow);
        }
        return LowestHumidityRow;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " 
        + Integer.parseInt(csv.get("Humidity")) + " at " 
        + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord LowestHumidityRow = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord CurrentRow = lowestHumidityInFile(parser);
            LowestHumidityRow = 
            comparetworecords(LowestHumidityRow, CurrentRow);
        }
        return LowestHumidityRow;
    }
    
    public CSVRecord comparetworecords(CSVRecord LowestHumidityRow,
    CSVRecord CurrentRow){
        if (LowestHumidityRow == null){
            LowestHumidityRow = CurrentRow;
            }
        else {
            double CurrentRowHumidity = 
            Integer.parseInt(CurrentRow.get("Humidity"));
            double LowestHumidity =
            Integer.parseInt(LowestHumidityRow.get("Humidity"));
            if (CurrentRowHumidity < LowestHumidity){
                LowestHumidityRow = CurrentRow;
        }
        }
        return LowestHumidityRow;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowesthumidityrow = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " 
        + Integer.parseInt(lowesthumidityrow.get("Humidity")) + " at " 
        + lowesthumidityrow.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double TotalTemp = 0.0;
        int TotalRowCount = 0;
        for (CSVRecord CurrentRow : parser){
            if (CurrentRow.get("TemperatureF").equals("-9999")){
                continue;
            }
            TotalTemp += Double.parseDouble(CurrentRow.get("TemperatureF"));
            TotalRowCount += 1;
        }
        double AverageTemp = TotalTemp/TotalRowCount;
        return AverageTemp;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double AverageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + AverageTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile
    (CSVParser parser, int value){
        double TotalTemp = 0.0;
        int TotalRowCount = 0;
        for (CSVRecord CurrentRow : parser){
            if (CurrentRow.get("TemperatureF").equals("-9999")){
                continue;
            }
            if (Integer.parseInt(CurrentRow.get("Humidity")) >= value){
                TotalTemp += Double.parseDouble(CurrentRow.get("TemperatureF"));
                TotalRowCount += 1;
            }
        }
        if (TotalRowCount == 0){
            return 0;
        }
        double AverageTemp = TotalTemp/TotalRowCount;
        return AverageTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double AverageTemp = 
        averageTemperatureWithHighHumidityInFile(parser, 80);
        if (AverageTemp == 0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " 
            + AverageTemp);
        }
    }
}
