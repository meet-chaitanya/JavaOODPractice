import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HuffmanCompressor {



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
        FrequencyTable frequencyTable = new FrequencyTable();
        frequencyTable.fromInputStream(inputFile);

        inputFile.close();

        // Build HuffmanTree from frequency table
        HuffTree huffmanTree = frequencyTable.buildHuffmanTree();

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
