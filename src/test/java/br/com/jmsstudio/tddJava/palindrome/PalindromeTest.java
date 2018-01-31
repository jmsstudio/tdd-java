package br.com.jmsstudio.tddJava.palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTest {

    @Test
    public void checkIfPalindromePhraseWithDashIsPalindrome() {
        final String phrase = "Socorram-me subi no onibus em marrocos";

        assertTrue(new Palindrome().isPalindrome(phrase));
    }

    @Test
    public void checkIfPalindromePhraseIsPalindrome() {
        final String phrase = "Anotaram a data da maratona";

        assertTrue(new Palindrome().isPalindrome(phrase));
    }

    @Test
    public void checkIfNoPalindromePhraseIsPalindrome() {
        final String phrase = "Weird";

        assertFalse(new Palindrome().isPalindrome(phrase));
    }
}
