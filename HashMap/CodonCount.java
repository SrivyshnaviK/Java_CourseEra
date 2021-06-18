package com.Vyshnavi.HashMap;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String ,Integer> hashMap;
    CodonCount(){
        hashMap=new HashMap<>();
    }
    public void buildCodon(int start,String dna){
        hashMap.clear();
        for(int i=start;i<dna.length()-3;i+=3){
            String codon=dna.substring(i,i+3);
            int count=1;
            if(hashMap.containsKey(codon))
                hashMap.replace(codon,count,count+1);
            else
                hashMap.put(codon,count);

        }
    }
    public String getMostCommonCodon(){
        String maxCodon="";
        int maxCodonValue=Integer.MIN_VALUE;
        for (String string:hashMap.keySet()) {
            int value=hashMap.get(string);
            if(value>maxCodonValue){
                maxCodonValue=value;
                maxCodon=string;
            }
        }
        return maxCodon;
    }
    public void printCodonCounts(int start,int end){
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:" );
        for(String string:hashMap.keySet()){
            int value=hashMap.get(string);
            if(value>=start && value<=end)
                System.out.println(string+" "+value);
        }
    }
    public static void main(String[] args) {
        CodonCount codonCount=new CodonCount();
        codonCount.buildCodon(1,"CGTTCAAGTTCAA");
        System.out.println(codonCount.getMostCommonCodon());
        codonCount.printCodonCounts(1,5);
    }
}
