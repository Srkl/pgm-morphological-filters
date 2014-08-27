package srkl.morphological.filters.image.pgm.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RegionCounter {

    public int count(int[][] data) {
        int w = data.length;
        int h = data[0].length;
        int maxLabel = w*h;
        int[] parent = new int[maxLabel];
        Arrays.fill(parent, 0);
        

        int[][] labels = new int[w][h];
        for (int[] is : labels) {
            Arrays.fill(is, 0);
        }

        int label = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (data[i][j] != 0) {
                    List<Pixel> neighbors = getNeighbors(data, i, j);
                    List<Integer> labeled = new LinkedList<>();

                    for (Pixel element : neighbors) {
                        if (labels[element.getX()][element.getY()] != 0) {
                            labeled.add(labels[element.getX()][element.getY()]);
                        }
                    }

                    if (labeled.isEmpty()) {
                        labels[i][j] = label;
                        label++;
                    } else {
                        Collections.sort(labeled);
                        labels[i][j] = labeled.get(0);
                        for (Integer integer : labeled) {
                           parent = union(labels[i][j], integer, parent);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                labels[i][j] = find(labels[i][j], parent);
            }
        }

        return countUnique(labels);
    }

    private int[] union(int x, int y, int[] parent) {
        int j = x;
        int k = y;
        while (parent[j] != 0) {
            j = parent[j];
        }
         while (parent[k] != 0) {
            k = parent[k];
        }
         
         if (j != k) {
             parent[k] = j;
         }   
         return parent;
    }
    
    private int countUnique(int[][] labels) {
        List<Integer> set = new LinkedList<>();
        for (int[] is : labels) {
            for (int i : is) {
                if (!set.contains(i)) {
                    set.add(i);
                }
            }
        }
        return set.size();
    }
    
    private int find(int x, int[] parent) {
        int j = x;
        while (parent[j] != 0) {
            j = parent[j];
        }
        return j;
    }
    
    private List<Pixel> getNeighbors(int[][] data, int i, int j) {
        List<Pixel> neighbors = new LinkedList<>();

        int iMinus = i - 1, jMinus = j - 1, iPlus = i + 1, jPlus = j + 1;

        int height = data[0].length;
        int width = data.length;

        if (iPlus < width) {
            checkBounds(data, iPlus, j, i, neighbors, jMinus, jPlus, height);
        }
        if (jMinus >= 0) {
            addElement(data, i, jMinus, i, j, neighbors);
        }

        if (jPlus < height) {
            addElement(data, i, jPlus, i, j, neighbors);
        }

        if (iMinus >= 0) {
            checkBounds(data, iMinus, j, i, neighbors, jMinus, jPlus, height);
        }

        return neighbors;
    }

    private void checkBounds(int[][] data, int iMinus, int j, int i, List<Pixel> neighbors, int jMinus, int jPlus, int height) {
        addElement(data, iMinus, j, i, j, neighbors);
        if (jMinus >= 0) {
            addElement(data, iMinus, jMinus, i, j, neighbors);
        }
        if (jPlus < height) {
            addElement(data, iMinus, jPlus, i, j, neighbors);
        }
    }

    private void addElement(int[][] data, int i, int j, int x, int y, List<Pixel> neighbors) {
        int position;
        if (data[x][y] == data[i][j]) {
            position = data[i][j];
            neighbors.add(new Pixel(i, j, position));
        }
    }

}
