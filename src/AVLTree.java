public class AVLTree {
    AVLNode root = null;

    int getValue(AVLNode node) {
        return node.key;
    }

    int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    void setHeight(AVLNode node) {
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    int balance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    AVLNode mostLeftInRightSubTree(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    ///////////////////////////////////////////////////////////////
    void search(int value) {
        AVLNode temp = root;
        while (temp != null) {
            if (getValue(temp) == value) {
                return;
            }
            else if (getValue(temp) < value) {
                temp = temp.right;
            }
            else {
                temp = temp.left;
            }
        }
    }

    void insert(int key, String value) {
        root = insert(root, key, value);
    }

    AVLNode insert(AVLNode node, int key, String value) {
        if (node == null) {
            node = new AVLNode(key, value);
            return node;
        }
        if (key < node.key) {
            node.left = insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
        } else {
            return node;
        }
        setHeight(node);
        int balanceFactor = balance(node);
        if (balanceFactor == 2) {
            if (key < node.left.key) {
                return rotateToRight(node);
            } else if (key > node.left.key) {
                node.left = rotateToLeft(node.left);
                return rotateToRight(node);
            }
        }
        else if (balanceFactor == -2) {
            if (key > node.right.key) {
                return rotateToLeft(node);
            } else if (key < node.right.key) {
                node.right = rotateToRight(node.right);
                return rotateToLeft(node);
            }
        }
        return node;
    }

    AVLNode rotateToLeft(AVLNode node) {
        if (node == null) {
            return null;
        }
        AVLNode nodeA = node.right;
        if (nodeA == null) {
            return node;
        }
        AVLNode nodeB = nodeA.left;
        nodeA.left = node;
        node.right = nodeB;
        setHeight(node);
        setHeight(nodeA);
        return nodeA;
    }

    AVLNode rotateToRight(AVLNode node) {
        if (node == null) {
            return null;
        }
        AVLNode nodeA = node.left;
        if (nodeA == null) {
            return node;
        }
        AVLNode nodeB = nodeA.right;
        nodeA.right = node;
        node.left = nodeB;
        setHeight(node);
        setHeight(nodeA);
        return nodeA;
    }

    void delete(int key) {
        delete(root, key);
    }

    AVLNode delete(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = delete(node.left, key);
        }
        else if (key > node.key) {
            node.right = delete(node.right, key);
        }
        else {
            if (node.left == null || node.right == null) {
                AVLNode temp = null;
                if (temp == node.left) {
                    temp = node.right;
                }
                else {
                    temp = node.left;
                }
                node = temp;
            }
            else {
                AVLNode temp = mostLeftInRightSubTree(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }
        if (node == null) {
            return node;
        }
        setHeight(node);
        int balanceFactor = balance(node);
        switch (balanceFactor) {
            case 2 -> {
                if (balance(node.left) < 0) {
                    node.left = rotateToLeft(node.left);
                }
                return rotateToRight(node);
            }
            case -2 -> {
                if (balance(node.right) > 0) {
                    node.right = rotateToLeft(node.right);
                }
                return rotateToLeft(node);
            }
        }
        return node;
    }
}