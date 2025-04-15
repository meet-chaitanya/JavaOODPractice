public class DLinkedList {
    private final DLinkedNode head;
    private final DLinkedNode tail;

    public DLinkedList() {
        this.head = new DLinkedNode(-1, -1);
        this.tail = new DLinkedNode(-1, -1);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void addFirst(DLinkedNode node) {
        node.setNext(head.getNext());
        node.setPrev(head);
        head.getNext().setPrev(node);
        head.setNext(node);
    }

    public void removeNode(DLinkedNode node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    public DLinkedNode removeLast() {
        if (head.getNext() == tail) {
            return null;
        }
        DLinkedNode last = tail.getPrev();
        removeNode(last);
        return last;
    }
}
