import edu.duke.*;
public class StringsandCodonsPart1 {
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
    
    public StorageResource getAllGenes (String dna){
        StorageResource genelist = new StorageResource();
        while (true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            //System.out.println(gene);
            genelist.add(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        return genelist;
    }
    
    public double cgRatio(String dna){
    double countcg = 0.0;
    int start = 0;
    double lengthofdna = dna.length();
    while (true) {
        int pos = dna.indexOf("C", start);
        if (pos == -1) {
            break;
          }
        countcg += 1;
        start = pos + 1;
    }
    start = 0;
    while (true) {
        int pos = dna.indexOf("G", start);
        if (pos == -1) {
            break;
          }
        countcg += 1;
        start = pos + 1;
    }
    double ratio = countcg/lengthofdna;
    return ratio;
    }
    
    public int countCTG(String dna){
        int count = 0;
        int index = dna.indexOf("CTG");
        while (index != -1){
            count+=1;
            index = dna.indexOf("CTG", index + "CTG".length());
        }
        return count;
    }
    
    public void processGenes (StorageResource sr){
        int char60count = 0;
        int cgRationCount = 0;
        String longest_gene = "";
        int total_genes = 0;
        for (String gene : sr.data()){
            total_genes += 1;
            if (gene.length() > 60){
                char60count += 1;
                System.out.println(gene);
            }
            if (cgRatio(gene) > 0.35){
                cgRationCount += 1;
                //System.out.println(gene);
            }
            while (gene.length() > longest_gene.length()){
                longest_gene = gene;
            }
        }
        System.out.println("total strings with more characters than 60 are "
        + char60count);
        System.out.println("\n\n\n");
        System.out.println("total strings with more ration than 0.35 are "
        + cgRationCount);
        System.out.println("\n\n\n");
        System.out.println("longest gene is\n"
        + longest_gene + "and this long: " + longest_gene.length());
        System.out.println("\n\n\n");
        System.out.println("total genes = " + total_genes);
        System.out.println("\n\n\n");
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        int ctg_count = countCTG(dna);
        System.out.println("ctg appearances: " + ctg_count);
        StorageResource dna_list = getAllGenes (dna);
        processGenes(dna_list);
    }
}
