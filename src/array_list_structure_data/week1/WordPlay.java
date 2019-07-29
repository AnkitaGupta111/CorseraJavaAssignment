package array_list_structure_data.week1;

public  class WordPlay {

    public  boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
            return true;
        return false;
    }

    public String replaceVowels(String phrase, char ch) {

        char[] res = phrase.toCharArray();

        for (int i = 0; i < res.length; i++) {
            if (isVowel(res[i]))
                res[i] = ch;

        }
        return res.toString();
    }

    public String emphasize(String phrase, char ch) {
        char res[] = phrase.toCharArray();

        for (int i = 0; i < res.length; i++) {
            if (res[i] == ch) {
                if ((i + 1) % 2 == 0)
                    res[i] = '+';
                else res[i] = '*';

            }

        }
        return res.toString();

    }
}
