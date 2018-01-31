package br.com.jmsstudio.tddJava.palindrome;

public class Palindrome {

    public boolean isPalindrome(String phrase) {
        String filteredPhrase = phrase.toUpperCase().replace(" ", "").replace("-", "");

        for (int i = 0; i < filteredPhrase.length(); i++) {
            if (filteredPhrase.charAt(i) !=
                    filteredPhrase.charAt(filteredPhrase.length() - (i + 1))) {
                return false;
            }
        }

        return true;
    }
}
