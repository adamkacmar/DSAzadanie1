import java.io.*;
import java.util.Random;

public class Test {
    static int[] Samples = {1000, 5000, 10000, 50000, 100000, 250000, 500000, 750000, 1000000};
    static int sampleRoof = 2000000;
    static int testRunsTree = 500000;
    static int testRunsHash = 10000;
    static Integer[] keyData;
    static String[] valueData;
    static String[] tableData;

    static void generateBSTDataset() throws IOException {
        File fileBST = new File("C:\\Users\\adamk\\IdeaProjects\\DSA-avl\\integerplusstring.txt");
        BufferedReader bufferBST = new BufferedReader(new FileReader(fileBST));

        keyData = new Integer[sampleRoof];
        valueData = new String[sampleRoof];

        String line;
        int number;

        for (int i = 0; i < sampleRoof*2; i++) {
            line = bufferBST.readLine();
            if (i % 2 == 0) {
                number = Integer.parseInt(line);
                keyData[i/2] = number;
            }
            else {
                valueData[i/2] = line;
            }
        }
    }

    static void generateHashDataset() throws IOException {
        File fileHash = new File("C:\\Users\\adamk\\IdeaProjects\\DSA-avl\\random_strings.txt");
        BufferedReader bufferHash = new BufferedReader(new FileReader(fileHash));

        tableData = new String[sampleRoof];

        String line;

        for (int i = 0; i < sampleRoof; i++) {
            line = bufferHash.readLine();
            tableData[i] = line;
        }
    }
    public static void main(String[] args) throws IOException {
        AVLTree avlTree = new AVLTree();
        SplayTree splayTree = new SplayTree();

        HashChaining hashChain = new HashChaining(16);
        HashOpenAddressing hashOA = new HashOpenAddressing();

        generateBSTDataset();
        generateHashDataset();


        System.out.println("//------------- AVL Tree -------------//");

        System.out.println();

        for (int sample : Samples) {
            long timeInsert = 0;
            long timeSearch = 0;
            long timeDelete = 0;
            long timeInsertSearch = 0;
            long timeDeleteSearch = 0;
            long timeInsertSearchDelete = 0;
            Random random = new Random();
            long start, end, elapsed;

            System.out.println("------------ " + sample + " ------------");

            for (int i = 0; i < sample; i++) {
                avlTree.insert(keyData[i], valueData[i]);
            }

            for (int i = 0; i < testRunsTree; i++) {
                int index = random.nextInt(sampleRoof - sample) + sample;

                // insert measurement
                start = System.nanoTime();
                avlTree.insert(keyData[index], valueData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsert += elapsed;

                // search measurement
                start = System.nanoTime();
                avlTree.search(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeSearch += elapsed;

                // delete measurement
                start = System.nanoTime();
                avlTree.delete(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDelete += elapsed;

                // insert + search measurement
                start = System.nanoTime();
                avlTree.insert(keyData[index], valueData[index]);
                avlTree.search(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearch += elapsed;

                // delete + search measurement
                start = System.nanoTime();
                avlTree.delete(keyData[index]);
                avlTree.search(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDeleteSearch += elapsed;

                // insert + search + delete measurement
                start = System.nanoTime();
                avlTree.insert(keyData[index], valueData[index]);
                avlTree.search(keyData[index]);
                avlTree.delete(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearchDelete += elapsed;
            }

            System.out.println("Insert: " + (timeInsert/testRunsTree));
            System.out.println("Search: " + (timeSearch/testRunsTree));
            System.out.println("Delete: " + (timeDelete/testRunsTree));
            System.out.println("Insert + Search: " + (timeInsertSearch/testRunsTree));
            System.out.println("Delete + Search: " + (timeDeleteSearch/testRunsTree));
            System.out.println("Insert + Search + Delete: " + (timeInsertSearchDelete/testRunsTree));

            for (int i = 0; i < sample; i++) {
                avlTree.delete(keyData[i]);
            }
        }

        System.out.println("//------------- Splay Tree -------------//");

        System.out.println();

        for (int sample : Samples) {
            long timeInsert = 0;
            long timeSearch = 0;
            long timeDelete = 0;
            long timeInsertSearch = 0;
            long timeDeleteSearch = 0;
            long timeInsertSearchDelete = 0;
            Random random = new Random();
            long start, end, elapsed;

            System.out.println("------------ " + sample + " ------------");

            for (int i = 0; i < sample; i++) {
                splayTree.insert(keyData[i], valueData[i]);
            }

            for (int i = 0; i < testRunsTree; i++) {
                int index = random.nextInt(sampleRoof - sample) + sample;

                // insert measurement
                start = System.nanoTime();
                splayTree.insert(keyData[index], valueData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsert += elapsed;

                // search measurement
                start = System.nanoTime();
                splayTree.search(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeSearch += elapsed;

                // delete measurement
                start = System.nanoTime();
                splayTree.delete(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDelete += elapsed;

                // insert + search measurement
                start = System.nanoTime();
                splayTree.insert(keyData[index], valueData[index]);
                splayTree.search(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearch += elapsed;

                // delete + search measurement
                start = System.nanoTime();
                splayTree.delete(keyData[index]);
                splayTree.search(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDeleteSearch += elapsed;

                // insert + search + delete measurement
                start = System.nanoTime();
                splayTree.insert(keyData[index], valueData[index]);
                splayTree.search(keyData[index]);
                splayTree.delete(keyData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearchDelete += elapsed;
            }

            System.out.println("Insert: " + (timeInsert/testRunsTree));
            System.out.println("Search: " + (timeSearch/testRunsTree));
            System.out.println("Delete: " + (timeDelete/testRunsTree));
            System.out.println("Insert + Search: " + (timeInsertSearch/testRunsTree));
            System.out.println("Delete + Search: " + (timeDeleteSearch/testRunsTree));
            System.out.println("Insert + Search + Delete: " + (timeInsertSearchDelete/testRunsTree));

            for (int i = 0; i < sample; i++) {
                splayTree.delete(keyData[i]);
            }
        }

        System.out.println("//------------- Hash Chaining -------------//");

        System.out.println();

        for (int sample : Samples) {
            long timeInsert = 0;
            long timeSearch = 0;
            long timeDelete = 0;
            long timeInsertSearch = 0;
            long timeDeleteSearch = 0;
            long timeInsertSearchDelete = 0;
            Random random = new Random();
            long start, end, elapsed;

            System.out.println("------------ " + sample + " ------------");

            for (int i = 0; i < sample; i++) {
                hashChain.insert(tableData[i]);
            }

            for (int i = 0; i < testRunsHash; i++) {
                int index = random.nextInt(sampleRoof - sample) + sample;

                // insert measurement
                start = System.nanoTime();
                hashChain.insert(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsert += elapsed;

                // search measurement
                start = System.nanoTime();
                hashChain.search(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeSearch += elapsed;

                // delete measurement
                start = System.nanoTime();
                hashChain.delete(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDelete += elapsed;

                // insert + search measurement
                start = System.nanoTime();
                hashChain.insert(tableData[index]);
                hashChain.search(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearch += elapsed;

                // delete + search measurement
                start = System.nanoTime();
                hashChain.delete(tableData[index]);
                hashChain.search(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDeleteSearch += elapsed;

                // insert + search + delete measurement
                start = System.nanoTime();
                hashChain.insert(tableData[index]);
                hashChain.search(tableData[index]);
                hashChain.delete(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearchDelete += elapsed;
            }

            System.out.println("Insert: " + (timeInsert/testRunsHash));
            System.out.println("Search: " + (timeSearch/testRunsHash));
            System.out.println("Delete: " + (timeDelete/testRunsHash));
            System.out.println("Insert + Search: " + (timeInsertSearch/testRunsHash));
            System.out.println("Delete + Search: " + (timeDeleteSearch/testRunsHash));
            System.out.println("Insert + Search + Delete: " + (timeInsertSearchDelete/testRunsHash));

            for (int i = 0; i < sample; i++) {
                hashChain.delete(tableData[i]);
            }
        }

        System.out.println("//------------- Hash Open Addressing -------------//");

        System.out.println();

        for (int sample : Samples) {
            long timeInsert = 0;
            long timeSearch = 0;
            long timeDelete = 0;
            long timeInsertSearch = 0;
            long timeDeleteSearch = 0;
            long timeInsertSearchDelete = 0;
            Random random = new Random();
            long start, end, elapsed;

            System.out.println("------------ " + sample + " ------------");

            for (int i = 0; i < sample; i++) {
                hashOA.insert(tableData[i]);
            }

            for (int i = 0; i < testRunsHash; i++) {
                int index = random.nextInt(sampleRoof - sample) + sample;

                // insert measurement
                start = System.nanoTime();
                hashOA.insert(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsert += elapsed;

                // search measurement
                start = System.nanoTime();
                hashOA.search(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeSearch += elapsed;

                // delete measurement
                start = System.nanoTime();
                hashOA.delete(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDelete += elapsed;

                // insert + search measurement
                start = System.nanoTime();
                hashOA.insert(tableData[index]);
                hashOA.search(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearch += elapsed;

                // delete + search measurement
                start = System.nanoTime();
                hashOA.delete(tableData[index]);
                hashOA.search(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeDeleteSearch += elapsed;

                // insert + search + delete measurement
                start = System.nanoTime();
                hashOA.insert(tableData[index]);
                hashOA.search(tableData[index]);
                hashOA.delete(tableData[index]);
                end = System.nanoTime();
                elapsed = end - start;
                timeInsertSearchDelete += elapsed;
            }

            System.out.println("Insert: " + (timeInsert/testRunsHash));
            System.out.println("Search: " + (timeSearch/testRunsHash));
            System.out.println("Delete: " + (timeDelete/testRunsHash));
            System.out.println("Insert + Search: " + (timeInsertSearch/testRunsHash));
            System.out.println("Delete + Search: " + (timeDeleteSearch/testRunsHash));
            System.out.println("Insert + Search + Delete: " + (timeInsertSearchDelete/testRunsHash));

            for (int i = 0; i < sample; i++) {
                hashOA.delete(tableData[i]);
            }
        }
    }
}
