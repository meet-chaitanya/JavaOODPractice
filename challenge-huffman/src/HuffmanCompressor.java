import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompressor {

    static class FrequencyTable {
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

        static FrequencyTable fromInputStream(InputStream inputStream) throws IOException {
            FrequencyTable table = new FrequencyTable();
            int c;
            while ((c = inputStream.read()) != -1) {
                table.addCharacter((char) c);
            }
            return table;
        }
    }

    private static HuffTree buildHuffmanTree(FrequencyTable frequencyTable) {

        PriorityQueue<HuffTree> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry: frequencyTable.frequencies.entrySet()) {
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

    private static void generateHuffmanCodes(HuffBaseNode node, String prefix, Map<Character, String> huffmanCodes) {
        if (node.isLeaf()) {
            HuffLeafNode leafNode = (HuffLeafNode) node;
            huffmanCodes.put(leafNode.getElement(), prefix);
        } else {
            HuffInternalNode internalNode = (HuffInternalNode) node;
            generateHuffmanCodes(internalNode.getLeft(), prefix + "0", huffmanCodes);
            generateHuffmanCodes(internalNode.getRight(), prefix + "1", huffmanCodes);
        }
    }

    public static void compress(String inputFileName, String outputFileName) throws IOException{
        // Build Frequency table
        FileInputStream inputFile = new FileInputStream(inputFileName);
        FrequencyTable frequencyTable = FrequencyTable.fromInputStream(inputFile);

        inputFile.close();

        // Build HuffmanTree from frequency table
        HuffTree huffmanTree = buildHuffmanTree(frequencyTable);

        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(huffmanTree.getRoot(), "", huffmanCodes);

        // Write the frequency table as a header and then compressed data.
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFileName))) {
            frequencyTable.serialize(out); // header

            out.writeByte(0xFF); // Special Marker

            //compressed data

            FileInputStream inputStream = new FileInputStream(inputFileName);
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = inputStream.read()) != -1) {
                char character = (char) c;
                String code = huffmanCodes.get(character);
                sb.append(code);
            }

            inputStream.close();

            int index = 0;
            while(index + 8 <= sb.length()) {
                String byteString = sb.substring(index, index + 8);
                out.writeByte(Integer.parseInt(byteString, 2));
                index += 8;
            }

            if (index < sb.length()) {
                String remainingString = sb.substring(index);
                out.writeByte(Integer.parseInt(remainingString,2));
            }
        }

        System.out.println("Compression completed and written to "+ outputFileName);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java HuffmanCompressor <inputFileName> <outputFileName>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        // compress input file stream and write to outputFile
        compress(inputFileName, outputFileName);
    }
}
