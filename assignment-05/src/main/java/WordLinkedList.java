// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/22/2024
// Purpose: Scrape a text file and create a search index for each word in the file.

public class WordLinkedList {
    private WordNode head;
    private WordNode tail;
    private int size = 0;

    public WordLinkedList() {}

    public int getSize() {
        return size;
    }

    public void addBack(WordData data) {
        size++;
        if (head == null) {
            head = new WordNode(data, null, null);
            tail = head;
        } else {
            WordNode newNode = new WordNode(data, null, tail);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
    }

    public void addFront(WordData data) {
        size++;
        if (head == null) {
            head = new WordNode(data, null, null);
            tail = head;
        } else {
            WordNode newNode = new WordNode(data, head, null);
            this.head.setPrev(newNode);
            this.head = newNode;
        }
    }
}
