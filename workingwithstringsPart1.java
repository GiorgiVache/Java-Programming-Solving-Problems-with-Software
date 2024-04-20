// finds genes with specific startcodons adn stopcodons

public class workingwithstringsPart1 {
    public String findSimpleGene(String dna){
        int start_codon_index = dna.indexOf("ATG");
        if (start_codon_index == -1) {
            return "";
        }
        int stop_codon_index = dna.indexOf("TAA", (start_codon_index + 3));
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
        String dna1 = "TTGAAAGGATAATGT";
        String dna2 = "TTGATGAAAGGGTGG";
        String dna3 = "TTGATCAAAGGGTGG";
        String dna4 = "TTGATGTCCTGGTAAGTT";
        String dna5 = "TTGATGTCCGGTAAGTT";
        System.out.println(dna1 + "\n" + findSimpleGene(dna1) + "\n");
        System.out.println(dna2 + "\n" + findSimpleGene(dna2) + "\n");
        System.out.println(dna3 + "\n" + findSimpleGene(dna3) + "\n");
        System.out.println(dna4 + "\n" + findSimpleGene(dna4) + "\n");
        System.out.println(dna5 + "\n" + findSimpleGene(dna5) + "\n");
    } 
}