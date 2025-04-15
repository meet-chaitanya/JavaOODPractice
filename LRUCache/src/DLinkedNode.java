public class DLinkedNode {
    private int key;
    private int value;
    private DLinkedNode next;
    private DLinkedNode prev;

    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DLinkedNode getNext() {
        return next;
    }

    public void setNext(DLinkedNode next) {
        this.next = next;
    }

    public DLinkedNode getPrev() {
        return prev;
    }

    public void setPrev(DLinkedNode prev) {
        this.prev = prev;
    }
}
