//Fatih AKGÜNDÜZ    150117032
//Yasin Enes POLAT  150117015

import java.util.*;
import java.io.*;
class main {

    static long start, end;
    static int arr_size, count;
    static int[] Heap; 
    static int size; 
    static int maxsize;

    public static void main (String args[]) throws FileNotFoundException {
        
        count = 0;
        File input = new File("inputs.txt");
        Scanner scan = new Scanner(input);
        int[][] avg_mm = new int[10][100];
        int[][] avg_q = new int[10][100];
        int[][] avg_m = new int[10][100];
        int[][] avg_h = new int[10][100];
        int[][] avg_i = new int[10][100];
        int[] a1 = new int[1000];
        int[] totarr = new int[10];
        int k = 0;
        while(scan.hasNextLine()) {
            String array = scan.nextLine();
            String[] arrString = array.split(" ");
            int[] arrayInt = new int[arrString.length];
            for (int j = 0; j < arrString.length; j++) {
                arrayInt[j] = Integer.parseInt(arrString[j]);
            }

            //QUICK SELECT
            count = 0;
            int[] q_a = new int[arrayInt.length];
            System.arraycopy(arrayInt, 0, q_a, 0, arrayInt.length);
            kthSmallest_qs1(q_a, 0, q_a.length - 1, q_a.length / 2);
            
            //If we want to get results from either random size of inputs or specific numbers of size, we should  comment the other line.
            
            //We uncomment below line for calculating count number for inputs which have size of 100, 200, ..., 1000.
            avg_q[q_a.length / 100 - 1][k % 100] += count;
            
            //We use below line for calculating count number for inputs which have random sizes.
            a1[k] = count;
            
            
            //HEAP
            count = 0;
            int[] h_a = new int[arrayInt.length];
            System.arraycopy(arrayInt, 0, h_a, 0, arrayInt.length);
            maxsize = arrayInt.length + 1;
            size = 0;
            Heap = new int[maxsize + 1];
            Heap[0] = Integer.MAX_VALUE;
            count = 0;
            fillHeap(h_a);
            for (int i = 0; i < arrayInt.length / 2; i++) {
                extractMax();
            }
            //System.out.println("HEAP = " + count);
            //avg_h[h_a.length / 100 - 1][k % 100] += count;
            
            
            
            //INSERTION
            count = 0;
            int[] i_a = new int[arrayInt.length];
            System.arraycopy(arrayInt, 0, i_a, 0, arrayInt.length);
            insertion_sort(i_a);
            //System.out.println("INSERTION = " + count);
            //avg_i[i_a.length / 100 - 1][k % 100] += count;
            

            //MERGE
            count = 0;
            int[] m_a = new int[arrayInt.length];
            System.arraycopy(arrayInt, 0, m_a, 0, arrayInt.length);
            merge_sort(m_a, 0, m_a.length - 1);
            //System.out.println("MERGE = " + count);
            //avg_m[m_a.length / 100 - 1][k % 100] += count;
            

            //MEDIAN OF MEDIANS
            count = 0;
            int[] MoM_a = new int[arrayInt.length];
            System.arraycopy(arrayInt, 0, MoM_a, 0, arrayInt.length);
            kthSmallest(MoM_a, 0, MoM_a.length - 1, MoM_a.length / 2);
            //System.out.println("MED OF MEDS = " + count);
            //avg_mm[MoM_a.length / 100 - 1][k % 100] += count;
            
            k++;           
        }

        //printArr2D(avg_q);
        //array();
        printArr(a1);
        
    }

    static void array () {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print((i + 1) * 100 + " ");
            }
        }
    }

    static void printArr2D (int[][] arr) {
        /*System.out.print("Y = ");
        for(int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }*/
        
        for(int i = 0; i < arr.length ; i++) {
            //System.out.print("Y for size " + (i+1) * 100 + " = ");
            for(int j = 0; j < 100 ; j++) {
                if (arr[i][j] != 0)
                    System.out.print(arr[i][j] + " ");
            }
            //System.out.println("\n");
        }
        
    }

    static void calculateAvg (int[] avg, int[] totarr) {
        for (int i = 0; i < avg.length; i++)
            avg[i] = avg[i] / totarr[i];

        printArr(avg);
        System.out.println();
    }

    static void fillRandom (int[] arr) {
        for(int i = 0; i < arr.length ; i++) {
            arr[i] = (int)(Math.random() * arr_size);
        }
    }

    static void printArr (int[] arr) {
        for(int i = 0; i < arr.length ; i++) {
            if (arr[i] != 0)
                System.out.print(arr[i] + " ");
        }
    }
      
    //////////////////////////////////////////////////////////////////////////7

    static void fillHeap (int[] a) {
        for (int i = 0; i < a.length; i++)
            insert(a[i]);
    }

    // Returns position of parent 
    static private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Below two functions return left and 
    // right children. 
    static private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
    static private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Returns true of given node is leaf 
    static private boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    static private void swap(int fpos, int spos) 
    { 
        count++;
        int tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
  
    // A recursive function to max heapify the given 
    // subtree. This function assumes that the left and 
    // right subtrees are already heapified, we only need 
    // to fix the root. 
    static private void maxHeapify(int pos) 
    { 
        if (isLeaf(pos)) 
            return; 
  
        if (Heap[pos] < Heap[leftChild(pos)] ||  
            Heap[pos] < Heap[rightChild(pos)]) { 
  
            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) { 
                swap(pos, leftChild(pos)); 
                maxHeapify(leftChild(pos)); 
            } 
            else { 
                swap(pos, rightChild(pos)); 
                maxHeapify(rightChild(pos)); 
            } 
        } 
    } 
  
    // Inserts a new element to max heap 
    static public void insert(int element) 
    { 
        Heap[++size] = element; 
  
        // Traverse up and fix violated property 
        int current = size; 
        while (Heap[current] > Heap[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
  
    static public void print() 
    { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + 
                      Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]); 
            System.out.println(); 
        } 
    } 
  
    // Remove an element from max heap 
    static public int extractMax() 
    { 
        int popped = Heap[1]; 
        Heap[1] = Heap[size--]; 
        maxHeapify(1); 
        return popped; 
    }

    //INSERTION SORT

    static void insertion_sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) {
                count++;
                arr[j + 1] = arr[j]; 
                j = j - 1;
            } 
            arr[j + 1] = key;
            count++;
        } 
    }

    // Merges two subarrays of arr[]. 
    // First subarray is arr[l..m] 
    // Second subarray is arr[m+1..r] 
    static void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) {
            L[i] = arr[l + i]; 
            count++;
        }
        for (int j=0; j<n2; ++j) {
            R[j] = arr[m + 1+ j]; 
            count++;
        }
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            count++;
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            count++;
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            count++;
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    static void merge_sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            merge_sort(arr, l, m); 
            merge_sort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    }

    //MEDIAN OF MEDIANS QUICK SELECT

    // This function returns k'th smallest element in arr[l..r] using 
    // QuickSort based method. ASSUMPTION: ALL ELEMENTS IN ARR[] ARE DISTINCT 
    static int kthSmallest(int arr[], int l, int r, int k) { 
        // If k is smaller than number of elements in array 
        if (k > 0 && k <= r - l + 1) { 
            // Partition the array around last element and get 
            // position of pivot element in sorted array 
            int pos = randomPartition(arr, l, r); 

            // If position is same as k 
            if (pos - l == k - 1) { 
                return arr[pos]; 
            } 
            if (pos - l > k - 1) // If position is more, recur for left subarray 
            { 
                return kthSmallest(arr, l, pos - 1, k); 
            } 

            // Else recur for right subarray 
            return kthSmallest(arr, pos + 1, r, k - pos + l - 1); 
        } 

        // If k is more than number of elements in array 
        return Integer.MAX_VALUE; 
    } 

    static void swap(int[] arr, int i, int j) { 
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
        count++;
    } 

    // Standard partition process of QuickSort(). It considers the last 
    // element as pivot and moves all smaller element to left of it 
    // and greater elements to right 
    static int partition_MoM(int arr[], int l, int r) { 
        int x = arr[r], i = l; 
        for (int j = l; j <= r - 1; j++) { 
            if (arr[j] <= x) { 
                swap(arr, i, j); 
                i++; 
            } 
        } 
        swap(arr, i, r); 
        return i; 
    } 

    static int randomPartition(int arr[], int l, int r) { 
        int n = r - l + 1; 
        int pivot = new Random().nextInt(1); 
        swap(arr, l + pivot, r); 
        return partition_MoM(arr, l, r); 
    }

    //QUICK SELECT
    public static int partition_qs (int[] arr, int low, int high) 
                                 
    { 
        int pivot;
        count++;
    	if ((arr[low] > arr[high]) != (arr[low] > arr[low+((high-low)/2)])) 
            pivot=arr[low];
        else if ((arr[high] > arr[low]) != (arr[high] > arr[low+((high-low)/2)])) 
        	pivot=arr[high];
        else
        	pivot=arr[low+((high-low)/2)];
        int pivotloc = low; 
        for (int i = low; i <= high; i++) 
        { 
            // inserting elements of less value  
            // to the left of the pivot location 
        	if(arr[i]==pivot) { 
                count++;
                arr[i] = arr[high]; 
                arr[high] = pivot;
        	}
            if(arr[i] < pivot) 
            { 
                count++;
                int temp = arr[i]; 
                arr[i] = arr[pivotloc]; 
                arr[pivotloc] = temp; 
                pivotloc++;
                
            } 
        } 
        // swapping pivot to the final pivot location 
        int temp = arr[high]; 
        arr[high] = arr[pivotloc]; 
        arr[pivotloc] = temp;
        return pivotloc; 
    } 
      
    // finds the kth position (of the sorted array)  
    // in a given unsorted array i.e this function  
    // can be used to find both kth largest and  
    // kth smallest element in the array.  
    // ASSUMPTION: all elements in arr[] are distinct 
    public static int kthSmallest_qs(int[] arr, int low,  
                                  int high, int k) 
    { 
        // find the partition  
        int partition = partition_qs(arr,low,high); 
  
        // if partition value is equal to the kth position,  
        // return value at k. 
        if(partition == k) 
            return arr[partition];     
              
        // if partition value is less than kth position, 
        // search right side of the array. 
        else if(partition < k ) 
            return kthSmallest_qs(arr, partition + 1, high, k ); 
              
        // if partition value is more than kth position,  
        // search left side of the array. 
        else
            return kthSmallest_qs(arr, low, partition-1, k );          
    }

    //QUICK SELECT - FIRST ELEMENT PIVOT
    public static int partition_qs1 (int[] arr, int low, int high) 
                                 
    { 
        int pivot=arr[low];
        int pivotloc = low+1; 
        for (int i = low+1; i <= high; i++) 
        { 
            // inserting elements of less value
            // to the left of the pivot location 
            count++;
            if(arr[i] < pivot) 
            { 
                int temp = arr[i]; 
                arr[i] = arr[pivotloc]; 
                arr[pivotloc] = temp; 
                pivotloc++;

            } 
        } 
        // swapping pivot to the final pivot location 

        arr[low] = arr[pivotloc-1]; 
        arr[pivotloc-1] = pivot;
        return pivotloc-1;  
    } 
      
    // finds the kth position (of the sorted array)  
    // in a given unsorted array i.e this function  
    // can be used to find both kth largest and  
    // kth smallest element in the array.  
    // ASSUMPTION: all elements in arr[] are distinct 
    public static int kthSmallest_qs1(int[] arr, int low,  
                                  int high, int k) 
    { 
        // find the partition  
        int partition = partition_qs1(arr,low,high); 
  
        // if partition value is equal to the kth position,  
        // return value at k. 
        if(partition == k) 
            return arr[partition];     
              
        // if partition value is less than kth position, 
        // search right side of the array. 
        else if(partition < k ) 
            return kthSmallest_qs1(arr, partition + 1, high, k ); 
              
        // if partition value is more than kth position,  
        // search left side of the array. 
        else
            return kthSmallest_qs1(arr, low, partition-1, k );          
    }
}