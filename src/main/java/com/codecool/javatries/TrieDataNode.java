package com.codecool.javatries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieDataNode {

    private char data;
    private LinkedList<TrieDataNode> children;
    private TrieDataNode parent;
    boolean isEnd;

    /**
     * Initializes a TrieDataNode with its data
     * @param data The data in this node
     */

    public TrieDataNode(char data) {
        this.data = data;
        this.children = new LinkedList<>();
        this.isEnd = false;
    }


    public char getData() {
        return data;
    }

    public TrieDataNode getChild(char c) {
        if (children != null)
            for (TrieDataNode eachChild : children)
                if (Character.toLowerCase(eachChild.data) == Character.toLowerCase(c))
                    return eachChild;
        return null;
    }

    protected List<String> getWords() {
        List<String> list = new ArrayList<>();
        if (isEnd) {
            list.add(toString());
        }

        if (children != null) {
            for (int i=0; i< children.size(); i++) {
                if (children.get(i) != null) {
                    list.addAll(children.get(i).getWords());
                }
            }
        }
        return list;
    }

    public TrieDataNode getParent() {
        return parent;
    }

    public LinkedList<TrieDataNode> getChildren() {
        return children;
    }

    public void setParent(TrieDataNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        if (parent == null) {
            return "";
        } else {
            return parent.toString() + new String(new char[] {data});
        }
    }

}
