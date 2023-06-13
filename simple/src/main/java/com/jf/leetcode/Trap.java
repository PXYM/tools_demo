package com.jf.leetcode;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/6/11 22:31
 */
public class Trap {

    public static void main(String[] args) {
        System.out.println(new Trap().trap(new int[]{4,2,0,3,2,5}));
    }

    public int trap(int[] height) {
        int area = 0;
        int[] status = new int[]{0, 0};  // [oldStatus, newStatus]
        int l1 = 0;
        for (int left = 1; left < height.length; left++) {
            int l2 = l1;
            if (status[0] == 0 && status[1] == 0 && height[left] > height[left - 1]) {
                continue;
            } else if (height[left] < height[left - 1]) {  // 递减
                if (status[1] != -1 ){
                    l1 = left - 1;
                }
                status[0] = status[1];
                status[1] = -1;
            } else if (height[left] > height[left - 1]) {  // 递增
                status[0] = status[1];
                status[1] = 1;
            }

            if (status[0] == 1 && status[1] == -1 ){
                area += rangeArea(l2, left - 1, height);
            } else if (left == height.length - 1 && status[0] == -1 && status[1] == 1){
                area += rangeArea(l2, height.length - 1, height);
            }
        }
        return area;
    }

    int rangeArea(int i, int j, int[] height) {
        int sum = 0;
        int left = Math.min(height[i], height[j]);
        for (++i; i < j; i++) {
            sum += (left - height[i]);
        }
        return sum;
    }
}
