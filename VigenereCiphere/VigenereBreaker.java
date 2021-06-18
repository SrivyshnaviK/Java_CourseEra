package com.Vyshnavi.VigenereCipher;


import edu.duke.FileResource;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String string="";
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            string+=message.charAt(i);
        return string;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesarCracker=new CaesarCracker(mostCommon);
        for (int i=0;i<klength;i++){
            String string=sliceString(encrypted,i,klength);
            key[i]= caesarCracker.getKey(string);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fileResource=new FileResource();
        String string=fileResource.asString();
        int[] key=tryKeyLength(string,5,'e');
        VigenereCipher vigenereCipher=new VigenereCipher(key);
        System.out.println(vigenereCipher.decrypt("XNYGI TC. Febxrx. JYNYWX'X bhyxp."));
    }

    public static void main(String[] args) {
        VigenereBreaker vigenereBreaker=new VigenereBreaker();
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 4, 5));
        vigenereBreaker.breakVigenere();
    }
    
}
