// finds genes with specific startcodon and stopcodon
// but in this case startcodon and stopcodon are variables

public class workingwithstringsPart2 {
     public String findSimpleGene(String dna, String startcodon, 
     String stopcodon){
        dna = dna.toUpperCase();
        startcodon = startcodon.toUpperCase();
        stopcodon = stopcodon.toUpperCase();
        int start_codon_index = dna.indexOf(startcodon);
        if (start_codon_index == -1) {
            return "";
        }
        int stop_codon_index = dna.indexOf(stopcodon, 
        (start_codon_index + 3));
        if (stop_codon_index == -1){
            return "";
        }
        String gene = dna.substring(start_codon_index, 
        (stop_codon_index + 3));
        
        if (gene.length() % 3 != 0){
            return "";
        }
        return gene;
    }
    
    public void testSimpleGene() {
        //String dna1 = "TTGAAAGGATAATGT";
        //String dna2 = "TTGATGAAAGGGTGG";
        //String dna3 = "TTGATCAAAGGGTGG";
        //String dna4 = "TTGATGTCCTGGTAAGTT";
        //String dna5 = "TTGATGTCCGGTAAGTT";
        String startcodon = "ATG";
        String stopcodon = "TAA";
        //System.out.println(dna1 + "\n" + findSimpleGene(dna1, 
        //startcodon, stopcodon) + "\n");
        //System.out.println(dna2 + "\n" + findSimpleGene(dna2, 
        //startcodon, stopcodon) + "\n");
        //System.out.println(dna3 + "\n" + findSimpleGene(dna3, 
        //startcodon, stopcodon) + "\n");
        //System.out.println(dna4 + "\n" + findSimpleGene(dna4, 
        //startcodon, stopcodon) + "\n");
        //System.out.println(dna5 + "\n" + findSimpleGene(dna5, 
        //startcodon, stopcodon) + "\n");
        String quiz_dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println(quiz_dna + "\n" + findSimpleGene(quiz_dna,
        startcodon, stopcodon) + "\n");
    } 
}
