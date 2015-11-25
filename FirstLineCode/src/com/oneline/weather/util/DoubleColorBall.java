package com.oneline.weather.util;

import java.util.Random;

import android.util.Log;

import com.oneline.bean.CountBall;
import com.oneline.bean.SysBall;
import com.oneline.bean.UserBall;



public class DoubleColorBall {
	private static final String TAG = DoubleColorBall.class.getSimpleName();
	int sysRedBall[] = new int[6];//系统红球
	int sysBlueBall = 0;//系统篮球
	int userRedBall[] = new int[6];//用户红球
	int userBlueBall = 0;//用户篮球
	int redCount ;//正确的红球个数
	int blueCount ;//正确的篮球个数
	
	int redBall[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
	int index = 0;
	Random random = new Random();
	
	//生成系统号码
	//系统红球
	public SysBall addSysBall(){
		SysBall sysBall = new SysBall();
		
		for (int i = 0; i < sysRedBall.length; i++) {
			while(true) {
				index = random.nextInt(redBall.length);//随机生成一个数的下标 	
				if(redBall[index] != -1) {//不为-1就保存，第一次肯定不为-1，保存了就设置为-1，下一次再循环到了就不存放了
					sysRedBall[i] = redBall[index];
					redBall[i] = -1;
					break;
				}
			}
			
		}
		//系统蓝球
		sysBlueBall = random.nextInt(16)+1;
		//result
		sysBall.setSysRedBall(sysRedBall);
		sysBall.setSysBlueBall(sysBlueBall);
		return sysBall;
	}
	
	
	//接收用户选号
	
	public UserBall addUserBall(){
		UserBall userBall = new UserBall();
		//用户红球
		for (int i = 0; i < userRedBall.length; i++) {
			userRedBall[i] = random.nextInt(33)+1;;
		}
		//用户蓝球
		userBlueBall = random.nextInt(16)+1;
		//result
		userBall.setUserRedBall(userRedBall);
		userBall.setUserBlueBall(userBlueBall);
		return userBall;
	}
	//验证是否中奖
	public CountBall checkWinCount(){
		redCount = 0;
		blueCount = 0;
		CountBall countBall = new CountBall();
		for (int i = 0; i < sysRedBall.length; i++) {
			if(sysRedBall[i] == userRedBall[i]) {
				redCount++;
				break;
			}
			
			
		}
		
		if(sysBlueBall == userBlueBall) {
			blueCount ++;
		}
		countBall.setWinRedBall(redCount);
		countBall.setWinBlueBall(blueCount);
		return countBall;
	}
}
