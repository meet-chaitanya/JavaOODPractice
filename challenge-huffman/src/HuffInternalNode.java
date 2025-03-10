public class HuffInternalNode implements HuffBaseNode{
    private int weight;
    private HuffBaseNode left;
    private HuffBaseNode right;

    public HuffInternalNode(int weight, HuffBaseNode l, HuffBaseNode r) {
        this.weight = weight;
        this.left = l;
        this.right = r;
    }

    public HuffBaseNode getLeft() {
        return left;
    }

    public HuffBaseNode getRight() {
        return right;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
