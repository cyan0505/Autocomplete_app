package com.codecool.javatries;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private TrieDataNode root;

    /**
     * Starts a new Trie with dummy root data "-"
     */
    public Trie() {
        root = new TrieDataNode('-');
    }

    /**
     * Adds a word to the Trie.
     */
    public void addWord(String wordToAdd) {
        if (search(wordToAdd))
            return;

        TrieDataNode current = root;
        TrieDataNode pre ;
        for (char ch : wordToAdd.toCharArray()) {
            pre = current;
            TrieDataNode child = current.getChild(ch);
            if (child != null) {
                current = child;
                child.setParent(pre);
            } else {
                current.getChildren().add(new TrieDataNode(ch));
                current = current.getChild(ch);
                current.setParent(pre);
            }
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        TrieDataNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null)
                return false;
            else {
                current = current.getChild(ch);
            }
        }
        if (current.isEnd) {
            return true;
        }
        return false;
    }


    /**
     * Returns the possible completions of baseChars String from the Trie.
     * @param baseChars The String to autocomplete.
     * @return possible completions. An empty list if there are none.
     */
    public List<String> autoComplete(String baseChars) {
        TrieDataNode lastNode = root;
        for (int i = 0; i< baseChars.length(); i++) {
            lastNode = lastNode.getChild(baseChars.charAt(i));
            if (lastNode == null)
                return new ArrayList<>();
        }

        return lastNode.getWords();
    }

}
