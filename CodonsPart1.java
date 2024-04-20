public class CodonsPart1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex + 1);
        while (((stopIndex - startIndex) % 3 != 0) && (stopIndex != -1)){
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        if (stopIndex == -1){
            int answer = dna.length();
            return -1;
        }
        return stopIndex;
    }
    
    public void testFindStopCodon (){
        String dna1 = "ATTTAAATGGGTTAATGT";
        System.out.println(findStopCodon(dna1, 6, "TAA"));
        String dna2 = "ATTTAAATGGGTAATGT";
        System.out.println(findStopCodon(dna2, 6, "TAA"));
        String dna3 = "ATTTAAATGGGTTGT";
        System.out.println(findStopCodon(dna3, 6, "TAA"));
        String dna4 = "ATTTAAATGGGTAATTAA";
        System.out.println(findStopCodon(dna4, 6, "TAA"));
    }
    
    public String findGene (String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int index1 = findStopCodon(dna, startIndex, "TAA");
        int index2 = findStopCodon(dna, startIndex, "TAG");
        int index3 = findStopCodon(dna, startIndex, "TGA");
        int temp = -1;
        int minimum = 0;
        if ((index1 < index2 && index1 != -1 && index2 != -1) || 
            (index1 != -1 && index2 == -1)){
            temp = index1;
        }
        if ((index2 < index1 && index1 != -1 && index2 != -1) ||
            (index2 != -1 && index1 == -1)){
            temp = index2;
        }
        if (index1 == -1 && index2 == -1){
            temp = -1;
        }
        
        if ((temp < index3 && temp != -1 && index3 != -1) || 
            (temp != -1 && index3 == -1)){
            minimum = temp;
            return dna.substring(startIndex, temp + 3);
        }
        if ((index3 < temp && temp != -1 && index3 != -1) || 
            (index3 != -1 && temp == -1)){
            minimum = index3;
            return dna.substring(startIndex, minimum + 3);
        }
        return "";
        }
        
    public void testFindGene(){
        String dna = "TAATAAGGTGGTCCTCCGGCT";
        System.out.println("THIS IS THE DNA STRING: " + dna);
        System.out.println(findGene (dna));
        
        String dna2 = "TGCATGGGTGGCGGTGGCTAAGGC";
        System.out.println("THIS IS THE DNA STRING: " + dna2);
        System.out.println(findGene (dna2));
        
        String dna3 = "TGCATGGAATGAGGCGGTTAGGGCTAA";
        System.out.println("THIS IS THE DNA STRING: " + dna3);
        System.out.println(findGene (dna3));
        
        String dna4 = "TGCATGGATGAGGCGGTATAGGGCTAA";
        System.out.println("THIS IS THE DNA STRING: " + dna4);
        System.out.println(findGene (dna4));
        
        String dna5 = "GCTATGGCTGGTGAA";
        System.out.println("THIS IS THE DNA STRING: " + dna5);
        System.out.println(findGene (dna5));
    }
    
    public void printAllGenes (String dna){
        while (true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
    }
    public void main_test (){
    printAllGenes("TAATAAGGTGGTCCTCCGGCTTGCATGGGTGGCGGTGGCTAAGGCTGCATGGAATGAGGCGGTTAGGGCTAATGCATGGATGAGGCGGTATAGGGCTAAGCTATGGCTGGTGAA");
    }
}