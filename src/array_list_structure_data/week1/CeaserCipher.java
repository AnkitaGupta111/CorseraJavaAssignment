package array_list_structure_data.week1;


import array_list_structure_data.week1.caesar_breaker_assigment.CaesarBreaker;
import edu.duke.FileResource;

public class CeaserCipher {
    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();

        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);

            if (idx != -1) {
                sb.setCharAt(i, shiftedAlphabet.charAt(idx));
            }

        }
        return sb.toString();
    }

    String sliceString(String str,int start)
    {
        StringBuilder strB=new StringBuilder(str);
        for (int i=start;i<str.length();i=i+2)
        {
            strB.append(str.charAt(i));
        }
        return strB.toString();
    }
    public String encryptWithTwoKeys(String input, int key1, int key2) {

        String str1=encrypt(sliceString(input,0),key1);
        String str2=encrypt(sliceString(input,1),key2);
StringBuilder encryptMessage=new StringBuilder();
char ch;
for (int i=0;i<input.length();i++)
        {if(i%2==0)
            ch=str1.charAt(i/2);
        else
            ch=str2.charAt((i-1)/2);
           encryptMessage.append(ch) ;
        }
return encryptMessage.toString();
    }

    public static void testCaeser() {
        FileResource fr = new FileResource();
       String message = fr.asString();
        CeaserCipher cc=new CeaserCipher();
        int key = 3;
        String encrypted =cc.encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);

        String enTwoKeys=cc.encryptWithTwoKeys(encrypted,3,4);

        CaesarBreaker cb=new CaesarBreaker();
        System.out.println(cb.decryptTwoKeys(enTwoKeys));
        System.out.println( cb.decrypt(encrypted));
    }

    public static void main(String[] args) {
        testCaeser();
    }

}