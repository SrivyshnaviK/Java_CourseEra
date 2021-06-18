package com.Vyshnavi.Assignments.Assignment1;

import edu.duke.FileResource;

public class CeaserCipher {
    public String encrypt(String input,int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetLowerCase = alphabet.toLowerCase().substring(key)+ alphabet.toLowerCase().substring(0,key);
        String shiftedAlphabetUpperCase = alphabet.substring(key)+ alphabet.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i),newChar=encrypted.charAt(i);
            if(Character.isLowerCase(currChar)) {
                int idx=alphabet.toLowerCase().indexOf(currChar);
                if(idx!=-1)
                newChar = shiftedAlphabetLowerCase.charAt(idx);
            }
            else{
                int idx=alphabet.indexOf(currChar);
                if(idx!=-1)
                newChar=shiftedAlphabetUpperCase.charAt(idx);
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input,int key1,int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i),newChar= encrypted.charAt(i);
            if(i%2==0){
            if(Character.isLowerCase(currChar)) {
                int idx=alphabet.toLowerCase().indexOf(currChar);
                if(idx!=-1)
                    newChar = shiftedAlphabet1.toLowerCase().charAt(idx); }
            else{
                int idx=alphabet.indexOf(currChar);
                if(idx!=-1)
                    newChar=shiftedAlphabet1.charAt(idx);
            }}
            else{
                if(Character.isLowerCase(currChar)) {
                    int idx=alphabet.toLowerCase().indexOf(currChar);
                    if(idx!=-1)
                        newChar = shiftedAlphabet2.toLowerCase().charAt(idx); }
                else{
                    int idx=alphabet.indexOf(currChar);
                    if(idx!=-1)
                        newChar=shiftedAlphabet2.charAt(idx);
                }
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }
    public void testCaesar(){
        FileResource fr = new FileResource("src/com/Vyshnavi/message1.txt");
        String message = fr.asString();
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println("key is " + 23 + "\n" + encrypted);
    }
    public static void main(String[] args) {
        CeaserCipher ceaserCipher =new CeaserCipher();
        ceaserCipher.testCaesar();
        System.out.println(ceaserCipher.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
}
