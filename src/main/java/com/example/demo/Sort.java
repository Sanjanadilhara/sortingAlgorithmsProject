package com.example.demo;

import java.util.ArrayList;

public class Sort {

    static public ArrayList<DataSet.SingleDataFormat> shellSort(ArrayList<DataSet.SingleDataFormat> temDataSet){
        int gap = temDataSet.size() / 2;
        while(gap > 0){
            for(int i = gap; i < temDataSet.size(); i++){
                DataSet.SingleDataFormat temp = temDataSet.get(i);
                int j = i;
                while(j >= gap && temDataSet.get(j - gap).value > temp.value){
                    temDataSet.set(j, temDataSet.get(j - gap));
                    j -= gap;
                }
                temDataSet.set(j, temp);
            }
            gap = gap / 2;
        }
        return temDataSet;
    }

    static public ArrayList<DataSet.SingleDataFormat> mergeSort(ArrayList<DataSet.SingleDataFormat> temDataSet) {
        return mergeSortHelper(temDataSet);
    }

    static private ArrayList<DataSet.SingleDataFormat> mergeSortHelper(ArrayList<DataSet.SingleDataFormat> list) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        ArrayList<DataSet.SingleDataFormat> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<DataSet.SingleDataFormat> right = new ArrayList<>(list.subList(mid, list.size()));
        return merge(mergeSortHelper(left), mergeSortHelper(right));
    }

    static private ArrayList<DataSet.SingleDataFormat> merge(ArrayList<DataSet.SingleDataFormat> left, ArrayList<DataSet.SingleDataFormat> right) {
        ArrayList<DataSet.SingleDataFormat> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).value <= right.get(j).value) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    static public ArrayList<DataSet.SingleDataFormat> quickSort(ArrayList<DataSet.SingleDataFormat> temDataSet) {
        quickSortHelper(temDataSet, 0, temDataSet.size() - 1);
        return temDataSet;
    }

    static private void quickSortHelper(ArrayList<DataSet.SingleDataFormat> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSortHelper(list, low, pi - 1);
            quickSortHelper(list, pi + 1, high);
        }
    }

    static private int partition(ArrayList<DataSet.SingleDataFormat> list, int low, int high) {
        double pivot = list.get(high).value;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).value <= pivot) {
                i++;
                DataSet.SingleDataFormat temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        DataSet.SingleDataFormat temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    static public ArrayList<DataSet.SingleDataFormat> heapSort(ArrayList<DataSet.SingleDataFormat> temDataSet) {
        int n = temDataSet.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(temDataSet, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            DataSet.SingleDataFormat temp = temDataSet.get(0);
            temDataSet.set(0, temDataSet.get(i));
            temDataSet.set(i, temp);
            heapify(temDataSet, i, 0);
        }
        return temDataSet;
    }

    static private void heapify(ArrayList<DataSet.SingleDataFormat> list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && list.get(left).value > list.get(largest).value) {
            largest = left;
        }
        if (right < n && list.get(right).value > list.get(largest).value) {
            largest = right;
        }
        if (largest != i) {
            DataSet.SingleDataFormat swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);
            heapify(list, n, largest);
        }
    }

    static public ArrayList<DataSet.SingleDataFormat> insertionSort(ArrayList<DataSet.SingleDataFormat> temDataSet) {
        for (int i = 1; i < temDataSet.size(); i++) {
            DataSet.SingleDataFormat key = temDataSet.get(i);
            int j = i - 1;
            while (j >= 0 && temDataSet.get(j).value > key.value) {
                temDataSet.set(j + 1, temDataSet.get(j));
                j--;
            }
            temDataSet.set(j + 1, key);
        }
        return temDataSet;
    }
}
