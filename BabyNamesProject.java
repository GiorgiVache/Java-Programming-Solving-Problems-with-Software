import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNamesProject {
    public int totalBirths(CSVParser parser){
        int total_births = 0;
        int girl_births = 0;
        int boy_births = 0;
        int total_names = 0;
        int total_girl_names = 0;
        int total_boy_names = 0;
        for (CSVRecord row : parser){
            total_births += Integer.parseInt(row.get(2));
            total_names += 1;
            if (row.get(1).equals("F")){
                girl_births += Integer.parseInt(row.get(2));
                total_girl_names += 1;
            }
            else {
                boy_births += Integer.parseInt(row.get(2));
                total_boy_names += 1;
            }
        }
        System.out.println("total girl names: " + total_girl_names);
        System.out.println("total boy names: " + total_boy_names);
        return total_births;
    }
    
    public void testtotalBirths(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int totalbirths = totalBirths(parser);
    }
    
    public int getRank(int year, String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        for (File file : dr.selectedFiles()){
            if (file.getName().contains(Integer.toString(year))){
                FileResource fr = new FileResource(file);
                CSVParser parser = fr.getCSVParser(false);
                for (CSVRecord row : parser){
                    if (row.get(1).equals(gender)){
                        rank += 1;
                        if (row.get(0).equals(name)){
                            return rank;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public void testgetRank(){
        //int rank1 = getRank(1960, "Emily", "F");
        int rank2 = getRank(1971, "Frank", "M");
        //int rank3 = getRank(2014, "Mason", "M");
        //int rank4 = getRank(2015, "Mason", "M");
        //int rank5 = getRank(2012, "Mason", "F");
        //System.out.println("Emily's Rank is " + rank1);
        System.out.println("Frank's Rank is " + rank2);
        //System.out.println(rank3);
        //System.out.println(rank4);
        //System.out.println(rank5);
    }
    
    public String getName(int year, int rank, String gender){
        DirectoryResource dr = new DirectoryResource();
        int counted_rank = 0;
        for (File file : dr.selectedFiles()){
            if (file.getName().contains(Integer.toString(year))){
                FileResource fr = new FileResource(file);
                CSVParser parser = fr.getCSVParser(false);
                for (CSVRecord row : parser){
                    if (row.get(1).equals(gender)){
                        counted_rank += 1;
                        if (counted_rank == rank){
                            return row.get(0);
                        }
                    }
                }
            }
        }
        return "NO NAME";
    }
    
    public void testgetName(){
        String name = getName(1980, 350, "F");
        System.out.println("girl's name on rank 350 in 1980 " + name);
        String name2 = getName(1982, 450, "M");
        System.out.println("boy's name on rank 450 in 1982 " + name2);
        //String name3 = getName(2014, 6, "M");
        //System.out.println(name3);
        //String name4 = getName(2012, 2, "F");
        //System.out.println(name4);
        //String name5 = getName(2013, 4, "F");
        //System.out.println(name5);
        //String name6 = getName(2014, 6, "F");
        //System.out.println(name6);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear,
    String gender){
        int current_name_rank = getRank(year, name, gender);
        String new_name = getName(newYear, current_name_rank, gender);
        System.out.println(name + " born in " + Integer.toString(year) +
        " would be " + new_name + " if she/he was born in " + 
        Integer.toString(newYear) + ".");
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestrank = -1;
        int highestrankyear = -1;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int rank = 0;
            for (CSVRecord row : parser){
                if (row.get(1).equals(gender)){
                    rank += 1;
                    if (row.get(0).equals(name)){
                        if (highestrank == -1){
                            highestrank = rank;
                            highestrankyear = 
                            Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
                        }
                        else if (rank < highestrank){
                            highestrank = rank;
                            highestrankyear = 
                            Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
                        }
                        break;
                    }
                }
            }
        }
        return highestrankyear;
    }
    
    public void testyearOfHighestRank(){
        //System.out.println(yearOfHighestRank("Genevieve", "F"));
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double ranktotal = 0.0;
        double nameappearancehowmanyfiles = 0.0;
        double averagerank = -1.0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            double rank = 0.0;
            for (CSVRecord row : parser){
                if (row.get(1).equals(gender)){
                    rank += 1.0;
                    if (row.get(0).equals(name)){
                        ranktotal += rank;
                        nameappearancehowmanyfiles += 1.0;
                    }
                }
            }
        }
        if (nameappearancehowmanyfiles != 0){
            averagerank = ranktotal/nameappearancehowmanyfiles;
            return averagerank;
        }
        return averagerank;
    }
    
    public void testgetAverageRank(){
        //System.out.println(getAverageRank("Mason", "M"));
        //System.out.println(getAverageRank("Jacob", "M"));
        //System.out.println(getAverageRank("Susan", "F"));
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalbeforename = 0;
        boolean nameappearance = false;
        for (File file : dr.selectedFiles()){
            if (file.getName().contains(Integer.toString(year))){
                int rank = 0;
                FileResource fr = new FileResource(file);
                CSVParser parser = fr.getCSVParser(false);
                for (CSVRecord row : parser){
                    if (row.get(1).equals(gender) && !row.get(0).equals(name)){
                        totalbeforename += Integer.parseInt(row.get(2));
                    }
                    if (row.get(1).equals(gender) && row.get(0).equals(name)){
                        nameappearance = true;
                        break;
                    }
                }
            }
        }
        if (nameappearance = false){
            return -1;
        }
        return totalbeforename;
    }
    
    public void testgetTotalBirthsRankedHigher(){
        //System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
        //System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
