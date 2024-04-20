public class CodonsPart2 {
    public int howMany (String stringa, String stringb){
        int count = 0;
        while (stringb.indexOf(stringa) != -1){
            count += 1;
            stringb = stringb.substring(stringb.indexOf(stringa) 
            + stringa.length());
        }
        return count;
    }
    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
        System.out.println(howMany("GAA", "ATAAAA"));
        System.out.println(howMany("GAA", "ATGAACTTGAATC"));
    }
}
