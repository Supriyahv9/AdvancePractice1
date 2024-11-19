package com.comcast.crm.generic.utility;

import java.text.SimpleDateFormat;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random = new Random();
		int rnum = random.nextInt(1000);
		return rnum;
	}
	
	public void getSystemEndDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		
	}
	
	

}
