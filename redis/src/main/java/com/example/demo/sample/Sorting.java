package com.example.demo.sample;

public class Sorting {


    public static void selectedSort(int[] kkk) {
        for (int i = 0; i < kkk.length; i++) {
            for (int ii = i + 1; ii < kkk.length; ii++) {
                if (kkk[i] > kkk[ii]) {
                    int a = kkk[i];
                    int b = kkk[ii];
                    kkk[i] = b;
                    kkk[ii] = a;
                }
            }
        }
        for (int i : kkk) {
            System.out.println("L:" + i);
        }
    }


    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int standard = arr[i];  // 기준
            int aux = i - 1;   // 비교할 대상

            while (aux >= 0 && standard < arr[aux]) {
                arr[aux + 1] = arr[aux];   // 비교대상이 큰 경우 오른쪽으로 밀어냄
                aux--;
            }

            arr[aux + 1] = standard;  // 기준값 저장

        }
        for (int i : arr) {
            System.out.println("insertSort:" + i);
        }
    }


    public static void bubbleSort(int[] arr) {
        int lastOrder = arr.length - 1;
        int position = 0;

        while (lastOrder != 0 && position > lastOrder) {
            if (arr[position] > arr[position + 1]) {
                int o = arr[position];
                arr[position] = arr[position + 1];
                arr[position + 1] = o;
            }
            if (lastOrder == position - 1) {
                lastOrder--;
                position = 0;
            } else {
                position++;
            }
        }
        for (int i : arr) {
            System.out.println("insertSort:" + i);
        }
    }

    public static void main(String[] args) {
        int[] kkk = {1, 3, 1, 2, 2, 4, 5, 6, 3, 20, 30, 26, 26};
        Sorting.selectedSort(kkk);
        System.out.println("**************************");
        Sorting.insertSort(kkk);
        System.out.println("**************************");
        Sorting.bubbleSort(kkk);
        System.out.println("**************************");
        Sorting.quicksort(kkk, 0, kkk.length);

        int[] eee={1,5,3,2,10,15,4};

    }

    public static void quicksort(int[] data, int l, int r) {
        int left = l;
        int right = r;
        int pivot = data[(int) Math.round(l + r) / 2];
        do {
            while (data[left] < pivot) left++;
            while (data[right] > pivot) right--;
            if (left <= right) {
                int temp = data[left];
                data[left] = data[right];
                data[right] = data[left];
                left++;
                right--;
            }
        } while (left <= right);

        if(l < right) quicksort(data, l, right);
        if(r > left) quicksort(data, left, r);

    }

    public void sort(int[] data, int l, int r) {
        int left = l;
        int right = r;
        int pivot = data[(int) Math.round(l + r) / 2];


        do {
            while (data[left] < pivot) left++;
            while (data[right] > pivot) right--;
            if (left <= right) {
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
        } while (left <= right);

        if (l < right) sort(data, l, right);
        if (r > left) sort(data, left, r);

    }
}
