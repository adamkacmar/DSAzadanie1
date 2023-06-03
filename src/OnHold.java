////import HashTable.HashTableChaining;
////import HashTable.HashTableOpenAddressing;
////import Tree.AVLTree;
////import Tree.SplayTree;
//
//
//import java.util.*;
//
//public class OnHold {
//
//    static int[] datasetSizes = { 10, 50, 100, 500, 1000, 5000,  10_000, 50_000, 100_000, 500_000 , 1_000_000 };
//
//    static int maxSize = datasetSizes[datasetSizes.length - 1]  + 1_000_000;
//
//    static Integer[] treeDataset;
//    static String[] tableDataset;
//
//    static long timeTracker;
//
//    public static void main(String[] args){
//        AVLTree avlTree = new AVLTree();
//        SplayTree splayTree = new SplayTree();
//
//        HashOpenAddressing hashTableOE = new HashOpenAddressing();
//        HashChaining hashTableCH = new HashChaining(10);
//
//        generateTreeDataset();
//        generateTableDataset();
//
//
//        int testRunsTrees = 10_000_000;
//        int testRunsTables = 10_000;
//        int sample = 500;
//
//
//
//        // A V L
//        System.out.println("A V L");
//        for(int datasetSize : datasetSizes){
//
//            // AVL TREES
//            long sumInsert = 0;
//            long sumSearch = 0;
//            long sumDelete = 0;
//
//            //insertnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                avlTree.insert(treeDataset[i], "");
//            }
//
//            System.out.print(datasetSize + "\t");
//
//            Random random = new Random();
//
//
//            for(int i = 0; i < testRunsTrees ; i++) {
//                int randomIndexToInsertAndDelete = random.nextInt(maxSize - datasetSize) + datasetSize;
//                int randomIndexToSearch = random.nextInt(datasetSize);
//
//
//                timeTracker = System.nanoTime();
//                avlTree.insert(treeDataset[randomIndexToInsertAndDelete], "");
//                timeTracker = System.nanoTime() - timeTracker;
//                sumInsert += timeTracker;
//
//
//                timeTracker = System.nanoTime();
//                avlTree.search(treeDataset[randomIndexToSearch]);
//                timeTracker = System.nanoTime() - timeTracker;
//                sumSearch += timeTracker;
//
//
//                timeTracker = System.nanoTime();
//                avlTree.delete(treeDataset[randomIndexToInsertAndDelete]);
//                timeTracker = System.nanoTime() - timeTracker;
//                sumDelete += timeTracker;
//            }
//
//            System.out.println(sumInsert/testRunsTrees + "\t" + sumSearch/testRunsTrees + "\t" + sumDelete/testRunsTrees);
//
//
//            //deletnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                avlTree.delete(treeDataset[i]);
//            }
//        }
//
//
//
//        // S P L A Y
//        System.out.println("S P L A Y");
//        for(int datasetSize : datasetSizes){
//
//            // SPLAY TREES
//            long sumInsert = 0;
//            long sumSearch = 0;
//            long sumDelete = 0;
//
//            //insertnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                splayTree.insert(treeDataset[i], "");
//            }
//
//            System.out.print(datasetSize + "\t");
//
//            Random random = new Random();
//
//
//            for(int i = 0; i < testRunsTrees ; i++) {
//                int randomIndexToInsertAndDelete = random.nextInt(maxSize - datasetSize) + datasetSize;
//                int randomIndexToSearch = random.nextInt(datasetSize);
//
//                timeTracker = System.nanoTime();
//                splayTree.insert(treeDataset[randomIndexToInsertAndDelete], "");
//                timeTracker = System.nanoTime() - timeTracker;
//                sumInsert += timeTracker;
//
//                timeTracker = System.nanoTime();
//                splayTree.search(treeDataset[randomIndexToSearch]);
//                timeTracker = System.nanoTime() - timeTracker;
//                sumSearch += timeTracker;
//
//                timeTracker = System.nanoTime();
//                splayTree.delete(treeDataset[randomIndexToInsertAndDelete]);
//                timeTracker = System.nanoTime() - timeTracker;
//                sumDelete += timeTracker;
//            }
//
//            System.out.println(sumInsert/testRunsTrees + "\t" + sumSearch/testRunsTrees + "\t" + sumDelete/testRunsTrees);
//
//
//            //deletnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                splayTree.delete(treeDataset[i]);
//            }
//        }
//
//
//
//        // H A S H   O P E N   A D D R E S S I N G
//        System.out.println("H A S H   O P E N   A D D R E S S I N G");
//        for(int datasetSize : datasetSizes){
//
//            // HASH TABLES
//            long sumInsert = 0;
//            long sumSearch = 0;
//            long sumDelete = 0;
//
//            //insertnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                hashTableOE.insert(tableDataset[i]);
//            }
//
//            System.out.print(datasetSize + "\t");
//
//            Random random = new Random();
//            for(int i = 0; i < testRunsTables ; i++) {
//
//                timeTracker = System.nanoTime();
//                for (int j = 0 ; j < sample ; j++) {
//                    hashTableOE.insert(tableDataset[datasetSize + j]);
//                }
//                timeTracker = System.nanoTime() - timeTracker;
//                sumInsert += timeTracker / sample;
//
//                timeTracker = System.nanoTime();
//                for (int j = 0 ; j < sample ; j++) {
//                    hashTableOE.search(tableDataset[datasetSize + j]);
//                }
//                timeTracker = System.nanoTime() - timeTracker;
//                sumSearch += timeTracker / sample;
//
//                timeTracker = System.nanoTime();
//                for (int j = 0 ; j < sample ; j++) {
//                    hashTableOE.delete(tableDataset[datasetSize + j]);
//                }
//                timeTracker = System.nanoTime() - timeTracker;
//                sumDelete += timeTracker / sample;
//            }
//
//            System.out.println(sumInsert/testRunsTables + "\t" + sumSearch/testRunsTables + "\t" + sumDelete/testRunsTables);
//
//
//            //deletnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                hashTableOE.delete(tableDataset[i]);
//            }
//        }
//
//
//        // H A S H   C H A I N I N G
//        System.out.println("H A S H   C H A I N I N G");
//        for(int datasetSize : datasetSizes){
//
//            // HASH TABLES
//            long sumInsert = 0;
//            long sumSearch = 0;
//            long sumDelete = 0;
//
//            //insertnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                hashTableCH.insert(tableDataset[i]);
//            }
//
//            System.out.print(datasetSize + "\t");
//
//            Random random = new Random();
//            for(int i = 0; i < testRunsTables ; i++) {
//
//                timeTracker = System.nanoTime();
//                for (int j = 0 ; j < sample ; j++) {
//                    hashTableCH.insert(tableDataset[datasetSize + j]);
//                }
//                timeTracker = System.nanoTime() - timeTracker;
//                sumInsert += timeTracker / sample;
//
//
//                timeTracker = System.nanoTime();
//                for (int j = 0 ; j < sample ; j++) {
//                    hashTableCH.search(tableDataset[datasetSize + j]);
//                }
//                timeTracker = System.nanoTime() - timeTracker;
//                sumSearch += timeTracker / sample;
//
//
//                timeTracker = System.nanoTime();
//                for (int j = 0 ; j < sample ; j++) {
//                    hashTableCH.delete(tableDataset[datasetSize + j]);
//                }
//                timeTracker = System.nanoTime() - timeTracker;
//                sumDelete += timeTracker / sample;
//
//            }
//
//            System.out.println(sumInsert/testRunsTables + "\t" + sumSearch/testRunsTables + "\t" + sumDelete/testRunsTables);
//
//            //deletnem vsetko
//            for (int i = 0; i < datasetSize; i++){
//                hashTableCH.delete(tableDataset[i]);
//            }
//        }
//
//
//    }
//
//
//    static void generateTreeDataset() {
//        treeDataset = new Integer[maxSize];
//
//        for (int i = 0; i < maxSize; i++)
//            treeDataset[i] = i + 1;
//
//        List<Integer> treeDatasetAsList = Arrays.asList(treeDataset);
//        Collections.shuffle(treeDatasetAsList);
//        treeDatasetAsList.toArray(treeDataset);
//    }
//
//    static void generateTableDataset() {
//        tableDataset = new String[maxSize];
//
//        for (int i = 0; i < maxSize; i++) {
//            tableDataset[i] = UUID.randomUUID().toString();
//        }
//    }
//}
//
//
////import com.sun.source.tree.Tree;
////
////import java.io.*;
////import java.sql.SQLOutput;
////import java.util.Scanner;
////
////public class Main {
////    public static void main(String[] args) throws IOException {
////        AVLTree strom = new AVLTree();
////
////        File file = new File("C:\\Users\\adamk\\IdeaProjects\\DSA-avl\\random_numbers.txt");
////        BufferedReader buffer = new BufferedReader(new FileReader(file));
////        Scanner input = new Scanner(System.in);
////
////        System.out.println("Enter number of items to insert: ");
////        int sample = input.nextInt();
////
////        String line;
////        int number;
////        long start = System.currentTimeMillis();
////        for (int i = 1; i <= sample; i++) {
////            line = buffer.readLine();
////            number = Integer.parseInt(line);
////            strom.insert(number);
//////            if (i % 25000 == 0) {
//////                long current = System.currentTimeMillis();
//////                System.out.println("Time between start and " + i + " inserted nodes is: " + (current-start));
//////            }
////        }
////        long finish = System.currentTimeMillis();
////        long timeElapsed = finish - start;
////        System.out.println("Insertion Time Elapsed: " + timeElapsed);
////
////        start = System.currentTimeMillis();
////        for (int i = 1; i <= sample/2; i++) {
////            line = buffer.readLine();
////            number = Integer.parseInt(line);
////            strom.search(number);
////        }
////        finish = System.currentTimeMillis();
////        timeElapsed = finish - start;
////        System.out.println("Search Time Elapsed: " + timeElapsed);
////
////        start = System.currentTimeMillis();
////        for (int i = 1; i <= sample/2; i++) {
////            line = buffer.readLine();
////            number = Integer.parseInt(line);
////            strom.delete(number);
////        }
////        finish = System.currentTimeMillis();
////        timeElapsed = finish - start;
////        System.out.println("Deletion Time Elapsed: " + timeElapsed);
////
////    }
////}