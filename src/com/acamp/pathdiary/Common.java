package com.acamp.pathdiary;

import com.acamp.pathdiary.utils.Utils;

public class Common {
	public static String selectedDate = Utils.getToday();
	public static void setNextDate(){
		selectedDate = Utils.getNextDate(selectedDate);
	}
	
	public static void setPrevDate(){
		selectedDate = Utils.getPrevDate(selectedDate);
	}
}
