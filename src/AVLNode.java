public class AVLNode {
    int key, height;
    String value;
    AVLNode left, right;

    public AVLNode(int key, String value) {
        left = null;
        right = null;
        this.key = key;
        this.value = value;
        height = 1;
    }
}