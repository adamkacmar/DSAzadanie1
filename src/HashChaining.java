
public class HashChaining {
    int size, amountOfItems;
    HashChainNode[] hashTable;

    HashChaining(int size) {
        this.size = size;
        this.hashTable = new HashChainNode[size];
    }

    int hashFunction(String value) {
        if (value == null) {
            return 0;
        }
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            hashValue += value.charAt(i);
        }
        return (31 * hashValue) % this.size;
    }

    void expandTable() {
        this.size *= 2;
        HashChaining newHashChain = new HashChaining(this.size);
        for (HashChainNode hashChainNode : this.hashTable) {
            HashChainNode node = hashChainNode;
            while (node != null) {
                newHashChain.insert(node.value);
                node = node.next;
            }
        }
        this.hashTable = newHashChain.hashTable;
    }

    void shrinkTable() {
        this.size /= 2;
        HashChaining newHashChain = new HashChaining(this.size);
        for (HashChainNode hashChainNode : this.hashTable) {
            HashChainNode node = hashChainNode;
            while (node != null) {
                newHashChain.insert(node.value);
                node = node.next;
            }
        }
        this.hashTable = newHashChain.hashTable;
    }
    /////////////////////////////////////////////////////
    void search(String value) {
        int hashIndex = hashFunction(value);
        HashChainNode temp = this.hashTable[hashIndex];
        while (temp != null) {
            if (temp.value == null)
                break;
            if (temp.value.equals(value)) {
                return;
            }
            temp = temp.next;
        }
    }
    void insert(String value) {
        int hashIndex = hashFunction(value);
        amountOfItems++;
        if (amountOfItems >= 0.75*size) {
            expandTable();
        }
        HashChainNode newNode = new HashChainNode(value);
        if (hashTable[hashIndex] == null) {
            hashTable[hashIndex] = newNode;
        }
        else {
            HashChainNode temp = hashTable[hashIndex];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    void delete(String value) {
        int hashIndex = hashFunction(value);
        amountOfItems--;
        if (amountOfItems <= 0.25*size) {
            shrinkTable();
        }
        if (hashTable[hashIndex] != null && hashTable[hashIndex].value != null) {
            if (hashTable[hashIndex].value.equals(value)) {
                hashTable[hashIndex].value = null;
                return;
            }
            HashChainNode temp = hashTable[hashIndex];
            while (temp.next != null) {
                if (temp.next.value.equals(value)) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    void print() {
        for (int i = 0; i < this.hashTable.length; i++) {
            System.out.print(i + ": ");
            HashChainNode temp = this.hashTable[i];
            if (temp == null) {
                System.out.println();
                continue;
            }
            System.out.print(hashTable[i].value);
            while (temp.next != null) {
                temp = temp.next;
                System.out.print(", " + temp.value);
            }
            System.out.print("\n");
        }
    }
}