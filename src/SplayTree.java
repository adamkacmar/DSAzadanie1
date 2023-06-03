public class SplayTree {
    SplayNode root = null;

    void insert(int key, String value) {
        root = insert(root, key, value);
    }

    SplayNode insert(SplayNode root, int key, String value) {
        if (root == null) {
            return new SplayNode(key, value);
        }
        root = splay(root, key);
        if (root.key == key) {
            return root;
        }
        SplayNode node = new SplayNode(key, value);
        if (root.key > key) {
            node.right = root;
            node.left = root.left;
            root.left = null;
        }
        else {
            node.left = root;
            node.right = root.right;
            root.right = null;
        }
        return node;
    }

    void search(int key) {
        search(root, key);
    }

    void search(SplayNode node, int key) {
        if (node == null) {
            return;
        }
        if (node.key > key) {
            search(node.left, key);
        }
        else if (node.key < key) {
            search(node.right, key);
        }
        else {
            root = splay(root, key);
        }
    }

    void delete(int key) {
        if (root == null) {
            return;
        }
        root = splay(root, key);
        if (root.key == key) {
            if (root.left == null) {
                root = root.right;
            }
            else {
                SplayNode temp = root.right;
                root = root.left;
                splay(root, key);
                root.right = temp;
            }
        }
    }

    SplayNode splay(SplayNode root, int key) {
        if (root == null || root.key == key)
            return root;
        if (root.key > key) {
            if (root.left == null) {
                return root;
            }
            if (root.left.key > key) {
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            }
            else if (root.left.key < key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) {
                    root.left = rotateLeft(root.left);
                }
            }
            if (root.left != null) {
                return rotateRight(root);
            }
            else {
                return root;
            }
        }
        else {
            if (root.right == null) {
                return root;
            }
            if (root.right.key > key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) {
                    root.right = rotateRight(root.right);
                }
            }
            else if (root.right.key < key) {
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            }
            if (root.right != null) {
                return rotateLeft(root);
            }
            else {
                return root;
            }
        }
    }

    SplayNode rotateRight(SplayNode node) {
        SplayNode temp = node.left;
        if (temp == null) {
            return node;
        }
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    SplayNode rotateLeft(SplayNode node) {
        SplayNode temp = node.right;
        if (temp == null) {
            return node;
        }
        node.right = temp.left;
        temp.left = node;
        return temp;
    }
}