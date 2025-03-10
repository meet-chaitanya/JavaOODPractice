public class HuffLeafNode implements HuffBaseNode{
    char element;
    int weight;

    public HuffLeafNode(char el, int wt) {
        this.element = el;
        this.weight = wt;
    }

    public char getElement() {
        return element;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
