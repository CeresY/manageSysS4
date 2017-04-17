package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestOther {
	public static void main(String[] args) {
		formatterLongDate();
	}
	
	public static void formatterLongDate() {
		long longtime = 1468858517000L;
		Date d1 = new Date(longtime);
		String str = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(d1);
		System.out.println(str);
	}
}
