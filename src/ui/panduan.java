package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class panduan {
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){  
			if (!Character.isDigit(str.charAt(i))){
				return false;
				}
			}
		return true;
		}
public static int compare_date(String DATE1, String DATE2) {
        
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;//DATE1在DATE2后
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;//DATE1在DATE2前
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	}
