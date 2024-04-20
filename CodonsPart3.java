public class CodonsPart3 {
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
    public int countGenes (String dna){
        int count = 0;
        while (findGene(dna) != ""){
            count += 1;
            String gene = findGene(dna);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        return count;
    }
    public void testCountGenes (){
        System.out.println(countGenes("TAATAAGGTGGTCCTCCGGCTTGCATGGGTGGCGGTGGCTAAGGCTGCATGGAATGAGGCGGTTAGGGCTAATGCATGGATGAGGCGGTATAGGGCTAAGCTATGGCTGGTGAA"));
        System.out.println(countGenes("TAATAAGGTGGTCCTCCGGCTTGCATGGGTGGCGGTG"));
        System.out.println(countGenes(""));
        System.out.println(countGenes("    "));
        System.out.println(countGenes("TGAGGCGGTTAGGGCTAATGCATG"));
        System.out.println(countGenes("ATGGGTGGCGGTGGCTAAGG"));
        System.out.println(countGenes("ATGGGTGGCGGTGGCTAA"));
    }
}
