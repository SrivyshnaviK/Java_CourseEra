package com.firstAssignment;


public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String endCodon)
    {
        if(dna.charAt(0)>=65 && dna.charAt(0)<=91) {
            dna = dna.toUpperCase();
            startCodon=startCodon.toUpperCase();
            endCodon=endCodon.toUpperCase();
        }
        else {
            dna = dna.toLowerCase();
            startCodon=startCodon.toLowerCase();
            endCodon=endCodon.toLowerCase();
        }
        int index1=dna.indexOf(startCodon);
        if(index1==-1)
            return "";
        else
        {
            int index2=dna.indexOf(endCodon,index1+1);
            if(index2==-1)
                return "";
            else{
                String subString=dna.substring(index1,index2+3);
                //System.out.println(subString);
                if(subString.length()%3==0)
                    return subString;
            }
        }
        return "";
    }
    public void testSimpleGene() {
        System.out.println(findSimpleGene("attagtagg", "atg", "taa"));
        System.out.println(findSimpleGene("aatgggtaga", "atg", "taa"));
        System.out.println(findSimpleGene("aaatttgggtaa", "atg", "taa"));
        System.out.println(findSimpleGene("aatgaatggtaa", "atg", "taa"));
        System.out.println(findSimpleGene("aatgaattggtaaa", "atg", "taa"));
        System.out.println(findSimpleGene("AATGAATTGGTAAA", "ATG", "TAA"));
    }
    public static void main(String[] args) {
        Part2 part2=new Part2();
        part2.testSimpleGene();
    }
}
