package com.oneline.utils;

public class Sort {
	//冒泡排序,相邻的从左往右比较，右边比左边一个大，则交换
	 int number[] = {1,5,6,33,4};
	 /*
	  * 1,5,6,4,33,
	  * 1,5,4,6,
	  * 1,4,5
	  * 1,4
	  * 1
*/
	 int temp;
	public  void BubbleSort(){
		for (int i = 0; i < number.length -1; i++) {//循环多少轮
			for (int j = 0; j < number.length -1 -i; j++) {//每轮循环多少次
				if(number[j] > number[j+1]) {//如果第一个数>第二个数，那么交换，因为从左到右，从小到大比较
					temp = number[j];
					number[j] = number[j+1];
					number[j+1] = temp;
				}
			}
			
		}
		for (int i = 0; i < number.length; i++) {
			System.out.println("=="+number[i]);
		}
		SelectionSort();
	}
	
	//选择排序，假定第一个最小，每一个都与第一个比较，如果有比第一个小的，则交换位置，比完了，下一轮则用第二个数与待比较元素比较，有比它小的，则交换位置
	int numberChange[] = {2,5,18,3,23,89,8};
	/* 先比当前第一个: 2,5,18,3,23,89,8
	 * 再比当前第二个: 2,3,18,5,23,89,8
	 * 再比当前第三个: 2,3,5,18,23,89,8
	 * 再比当前第4个: 2,3,5,8,23,89,18
	 * 再比当前第5个: 2,3,5,8,18,89,23
	 * 再比当前第6个: 2,3,5,8,18,23,89
	 * 
*/
	
	public void SelectionSort(){
		int minIndex = 0;//最小数的索引
		int temp = 0;//临时交换用
		for (int i = 0; i < numberChange.length - 1; i++) {//6轮，要比较的轮数
			minIndex = i;
			for(int j = i + 1;j<numberChange.length;j++){
				
				if(numberChange[j]<numberChange[minIndex]) {//如果当前的数字比第一个还小，把当前index作为最小值的index
					minIndex = j;
				}
			}
			
			if(minIndex != i) {//如果当前的index和第一个数字的index不一致，那么把筛选出来的那个最小的值与第一个交换位置
				temp = numberChange[minIndex];
				numberChange[minIndex] = numberChange[i];
				numberChange[i] = temp;
			}
			
		}
		
		for (int i = 0; i < numberChange.length; i++) {
			int j = numberChange[i];
			System.out.println("=="+j);
			
		}
	}
}
