import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExport {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryinfo(parser, "Nauru");
        //parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //parser = fr.getCSVParser();
        //numberOfExporters(parser, "cocoa");
        //parser = fr.getCSVParser();
        bigExporters (parser, "$999,999,999,999");
    }
    
    public String countryinfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)){
                String info = record.get("Country") + ": "
                + record.get("Exports") 
                + ": " + record.get("Value (dollars)");
                System.out.println(info);
                return info;
            }
        }
        System.out.println("NOT FOUND");
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
    String exportItem2){
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem1) && 
            record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                count += 1;
            }
        }
        System.out.println(count);
        return count;
    }
    
    public void bigExporters (CSVParser parser, String amount){
        int amount_length = amount.length();
        for (CSVRecord record : parser){
            if ((record.get("Value (dollars)").length()) > amount_length){
                System.out.println(record.get("Country") 
                + " " + record.get("Value (dollars)"));
            }
        }
    }
}
