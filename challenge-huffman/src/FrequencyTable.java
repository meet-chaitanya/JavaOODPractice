import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencyTable {
    Map<Character, Integer> frequencies;

    public FrequencyTable() {
        frequencies = new HashMap<>();
    }

    public void addCharacter(char c) {
        frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeInt(frequencies.size());

        for (Map.Entry<Character, Integer> entry: frequencies.entrySet()) {
            out.writeChar(entry.getKey());
            out.writeInt(entry.getValue());
        }
    }

    public void deserialize(DataInputStream in) throws IOException {
        int size = in.readInt();

        for (int i = 0; i < size; i++) {
            char ch = in.readChar();
            int freq = in.readInt();
            frequencies.put(ch, freq);
        }
    }

    public void fromInputStream(InputStream inputStream) throws IOException {
        int c;
        while ((c = inputStream.read()) != -1) {
            addCharacter((char) c);
        }
    }

    public HuffTree buildHuffmanTree() {

        PriorityQueue<HuffTree> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry: frequencies.entrySet()) {
            pq.add(new HuffTree(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffTree tree1 = pq.poll();
            HuffTree tree2 = pq.poll();

            HuffTree mergeTree = new HuffTree(tree1.weight() + tree2.weight(), tree1.getRoot(), tree2.getRoot());
            pq.add(mergeTree);
        }

        return pq.poll();
    }
}
