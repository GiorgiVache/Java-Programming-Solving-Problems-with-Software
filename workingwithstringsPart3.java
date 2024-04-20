// returns true if the substtring occurs at least 2 times
// returns the part of the word following the substring occurence, 
// if it doesn't appear returns the word

public class workingwithstringsPart3 {
    public String twoOccurrences(String stringa, String stringb){
        int start_index = stringb.indexOf(stringa);
        if (start_index != -1) {
            int second_start_index = stringb.indexOf(stringa, start_index
            + 1);
            if (second_start_index != -1){
                return "true";
            }
        }
        return "false";
    }
    public void testOccurences(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
    }
    public String lastPart(String stringa, String stringb){
        int start_index = stringb.indexOf(stringa);
        if (start_index != -1){
            String rest_string = stringb.substring((start_index + 
            stringa.length()), (stringb.length()));
            return rest_string;
        }
        return stringb;
    }
    public void testlastPart(){
        System.out.println("The part of the string after an in banana is " 
        + lastPart("an", "banana"));
        System.out.println("The part of the string after zoo in forest is " 
        + lastPart("zoo", "forest"));
    }
}
