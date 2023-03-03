package com.zjj;

import java.util.Arrays;

public class QuickSort {
    public static void quikSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = partion(nums, left, right);
            quikSort(nums, left, mid - 1);
            quikSort(nums, mid + 1, right);
        }
    }


    public static int partion(int[] nums, int left, int right) {
        int base = nums[left];
        int l = left, r = right;
        while(l < r) {
            while(r > l && nums[r] > base) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && nums[l] <= base) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = base;
        return l;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{11,3,2,1,34324};
        quikSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
