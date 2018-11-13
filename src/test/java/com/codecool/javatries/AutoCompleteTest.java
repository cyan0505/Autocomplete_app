package com.codecool.javatries;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutoCompleteTest {

    @Test
    void add_oneWord_fullSearch() {
        Trie ac = new Trie();
        ac.addWord("test");

        List<String> results = ac.autoComplete("test");
        List<String> expected = Arrays.asList("test");
        assertIterableEquals(expected, results);
    }

    @Test
    void add_oneWord_partialSearch() {
        Trie ac = new Trie();
        ac.addWord("aReallyLongWord");

        List<String> results = ac.autoComplete("aReally");
        List<String> expected = Arrays.asList("aReallyLongWord");
        assertIterableEquals(expected, results);
    }

    @Test
    void add_oneWord_wrongSearch() {
        Trie ac = new Trie();
        ac.addWord("aReallyLongWord");

        List<String> results = ac.autoComplete("Word");
        assertEquals(0, results.size());
    }

    @Test
    void add_oneWord_caseInsensitive() {
        Trie ac = new Trie();
        ac.addWord("aReallyLongWord");

        List<String> results = ac.autoComplete("AREALLY");
        List<String> expected = Arrays.asList("aReallyLongWord");
        assertIterableEquals(expected, results);
    }

    @Test
    void add_lotsOfWords() throws IOException {
        Path worldListPath = new File("assets/wordlist.txt").toPath();
        List<String> wordList = Files.readAllLines(worldListPath);
        Trie ac = new Trie();
        for (String str : wordList) {
            ac.addWord(str);
        }
        List<String> results = ac.autoComplete("spectro");
        List<String> expected = Arrays.asList("spectrogram", "spectrograph", "spectrographic",
                "spectrographically", "spectrometric", "spectrophotometer", "spectroscope",
                "spectroscopic", "spectroscopy");
        assertIterableEquals(expected, results);
    }

}