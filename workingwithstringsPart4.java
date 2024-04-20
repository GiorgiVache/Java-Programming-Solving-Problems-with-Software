// searches for youtube links

import edu.duke.*;

public class workingwithstringsPart4 {
    public String read_url(String url_link){
        URLResource url = new URLResource(url_link);
        for (String word : url.words()){
            String all_lowercase_word = word.toLowerCase();
            if (all_lowercase_word.indexOf("youtube.com")!=-1) {
                int quotation_start = word.indexOf("\"");
                int quotation_end = word.lastIndexOf("\"");
                System.out.println(word.substring
                    (quotation_start + 1, (quotation_end)));
            }
        }   
        return "";
    }

    public void test_read_url(){
        String ur = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        read_url(ur);
    }
}
