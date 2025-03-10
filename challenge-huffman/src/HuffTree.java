public class HuffTree implements Comparable{
    private HuffBaseNode root;

    public HuffTree(char el, int wt) {
        root = new HuffLeafNode(el, wt);
    }

    public HuffTree(int wt, HuffBaseNode l, HuffBaseNode r) {
        root = new HuffInternalNode(wt, l, r);
    }

    public HuffBaseNode getRoot() {
        return root;
    }

    public int weight() {
        return root.weight();
    }

    @Override
    public int compareTo(Object o) {
        HuffTree that = (HuffTree) o;
        return Integer.compare(this.weight(), that.weight());
    }
}
