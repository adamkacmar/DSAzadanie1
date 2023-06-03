public class HashOpenAddressing {
    int size = 8, amountOfItems;
    String[] hashTable;

    HashOpenAddressing() {
        this.hashTable = new String[this.size];
    }
    int hashFunction(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            hashValue += value.charAt(i);
        }
        return (727 * hashValue) % size;
    }

    void expandTable() {
        this.size *=4;
        String[] oldTable = hashTable;
        String temp;
        hashTable = new String[this.size];
        for (String s : oldTable) {
            temp = s;
            if (temp != null) {
                insert(temp);
            }
        }
    }

    void shrinkTable() {
        this.size /= 2;
        String[] oldTable = hashTable;
        String temp;
        hashTable = new String[this.size];
        for (String s : oldTable) {
            temp = s;
            if (temp != null) {
                insert(temp);
            }
        }
    }

    ///////////////////////////////////////////////////////

    void search(String value) {
        int hashIndex = hashFunction(value);
        String temp = this.hashTable[hashIndex];
        if (temp != null) {
            while (!hashTable[hashIndex].equals(temp) || hashTable[hashIndex] == null) {
                hashIndex = (hashIndex + 1) % this.size;
            }
        }
    }

    void insert(String value) {
        int hashIndex = hashFunction(value);
        amountOfItems++;
        if (amountOfItems >= 0.75*size) {
            expandTable();
        }
        if (hashTable[hashIndex] != null) {
            while (hashTable[hashIndex] != null) {
                hashIndex = (hashIndex + 1) % this.size;
            }
        }
        hashTable[hashIndex] = value;
    }

    void delete(String value) {
        int hashIndex = hashFunction(value);
        amountOfItems--;
        if (amountOfItems <= 0.25*size) {
            shrinkTable();
        }
        while (hashTable[hashIndex] != null) {
            if (hashTable[hashIndex].equals(value)) {
                hashTable[hashIndex] = null;
            }
            else {
                hashIndex = (hashIndex + 1) % this.size;
            }
        }
    }

    void print() {
        for (int i = 0; i < this.hashTable.length; i++) {
            System.out.println(i + ": " + hashTable[i]);
        }
    }
}
