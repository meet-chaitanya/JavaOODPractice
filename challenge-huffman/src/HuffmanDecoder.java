import java.io.*;

public class HuffmanDecoder {

    public static void decode(String inputFilename, String outputFileName) throws IOException {
       try (DataInputStream in = new DataInputStream(new FileInputStream(inputFilename))) {
           FrequencyTable table = new FrequencyTable();
           table.deserialize(in);

           HuffTree huffmanTree = table.buildHuffmanTree();

           in.readByte(); // Special Marker

           StringBuilder compressedBits = new StringBuilder();
           int c;
           while((c = in.read()) != -1) {
               String binaryString = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
               compressedBits.append(binaryString);
           }

           HuffBaseNode root = huffmanTree.getRoot();
           HuffBaseNode current = root;
           StringBuilder decodedText = new StringBuilder();

           for (int i = 0; i < compressedBits.length(); i++) {
               if (current.isLeaf()) {
                   decodedText.append(((HuffLeafNode) current).getElement());
                   current = root;
               }
               if (compressedBits.charAt(i) == '0') {
                   current = ((HuffInternalNode) current).getLeft();
               } else {
                   current = ((HuffInternalNode) current).getRight();
               }
           }

           try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
               writer.write(decodedText.toString());
           }
       }

        System.out.println("Decompression completed and written to " + outputFileName);
    }
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java HuffmanDecoder <inputFileName> <outputFileName>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        decode(inputFileName, outputFileName);
    }
}
