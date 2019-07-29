package array_list_structure_data.week1.caesar_breaker_assigment;

import array_list_structure_data.week1.CeaserCipher;

public class CaesarBreaker {
    int[] countLetters(String encrypted) {

        int count[] = new int[26];
        encrypted = encrypted.toLowerCase();
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            if (Character.isLetter(ch))
                count[ch - 'a']++;
        }
        return count;
    }

    int maxIndex(int[] count) {
        int max = 0;
        int in = 0;
        for (int i = 0; i < 26; i++) {

            if (max < count[i]) {
                max = count[i];
                in = i;
            }
        }
        return in;
    }

    String halfOfString(String message, int start) {
        String res = "";
        for (int i = start; i < message.length(); i += 2) {
            res += message.charAt(i);
        }
        return res;
    }

    int getKey(String encrypted) {
        int count[] = countLetters(encrypted);
        int key = maxIndex(count);
        int dkey = key - 4;
        if (dkey < 0)
            dkey += 26;
        return dkey;
    }

  public   String decryptTwoKeys(String encrypted) {
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
        int key1 = getKey(half1);
        int key2 = getKey(half2);
        System.out.println("the two keys are " + (26 - key1) + " " + (26 - key2));
        CeaserCipher cc = new CeaserCipher();
        String msg = cc.encryptWithTwoKeys(encrypted, 26 - key1, 26 - key2);
        return msg;
    }

   public String decrypt(String encrypted) {
        CeaserCipher cc = new CeaserCipher();
        int dkey = getKey(encrypted);
        String message = cc.encrypt(encrypted, 26 - dkey);
        return message;

    }

}