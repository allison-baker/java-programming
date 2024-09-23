// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/22/2024
// Purpose: Create a node class for each word data object.

public class WordNode {
    private WordNode next;
    private WordNode prev;
    private WordData data;

    public WordNode(WordData word, WordNode next, WordNode prev) {
        data = word;
        this.next = next;
        this.prev = prev;
    }

    public void setData(WordData word) {
        data = word;
    }

    public WordData getData() {
        return data;
    }

    public void setNext(WordNode next) {
        this.next = next;
    }

    public WordNode getNext() {
        return next;
    }

    public void setPrev(WordNode prev) {
        this.prev = prev;
    }

    public WordNode getPrev() {
        return prev;
    }
}
