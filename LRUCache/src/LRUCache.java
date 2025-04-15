import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {
    private final int capacity;
    private final Map<Integer, DLinkedNode> cache;
    private final DLinkedList list;
    private final ReentrantLock lock;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DLinkedList();
        this.lock = new ReentrantLock();
    }

    public Integer get(int key) {
        lock.lock();
        try {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return null;
            }
            list.removeNode(node);
            list.addFirst(node);
            return node.getValue();
        } finally {
            lock.unlock();
        }
    }

    public void put(int key, int value) {
        lock.lock();
        try {
            DLinkedNode node = cache.get(key);
            if (node != null) {
                node.setValue(value);
                list.removeNode(node);
                list.addFirst(node);
            } else {
                if (cache.size() == capacity) {
                    DLinkedNode last = list.removeLast();
                    if (last != null) {
                        cache.remove(last.getKey());
                    }
                }
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode);
                list.addFirst(newNode);
            }
        } finally {
            lock.unlock();
        }
    }
}
