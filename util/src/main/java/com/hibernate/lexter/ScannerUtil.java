package com.hibernate.lexter;

/**
 * Hello world!
 *
 */

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.StringUtils;

public class ScannerUtil 
{
	private Scanner scanner;

	public ScannerUtil() {
		scanner = new Scanner(System.in);
	}

	public ScannerUtil(Scanner scanner) {
		this.scanner = scanner;
	}

	public String getInputString(String message) {
		String input;
		System.out.println(message);
		input = scanner.nextLine();
		input = StringUtils.capitalize(input);
		return input;
	}

	public boolean getInputBoolean(String message) {
		String input = "";
		boolean bool = false;
		boolean valid = false;
		System.out.println(message);
		while(valid == false) {
			input = scanner.nextLine();
			if(input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
				bool = Boolean.parseBoolean(input);
				valid = true;
			} else {
				System.out.println("Please only enter 'true' or 'false'");
				valid = false;
			}
		}
		return bool;	
	}

	public int getInputInt(String message) {
		int input = 0;
		boolean valid = false;
		System.out.println(message);
		while(valid == false) {
			try {
				input = Integer.parseInt(scanner.nextLine());
				valid = true;
			} catch(NumberFormatException e) {
				System.out.println("Please only enter integer!");
				valid = false;
			}
		}
		return input;
	}

	public double getInputDouble(String message) {
		double input = 0;
		boolean valid = false;
		System.out.println(message);
		while(valid == false) {
			try {
				input = Double.parseDouble(scanner.nextLine());
				valid = true;
			} catch(NumberFormatException e) {
				System.out.println("Please only enter double!");
				valid = false;
			}
		}
		return input;
	}

	public Date parseDate(String message) {
		System.out.println(message);
		Calendar myCal = Calendar.getInstance();
		int month = getInputInt("Please Input Month:(1 - 12)");
		month -= 1;
		myCal.set(Calendar.MONTH, month);
		int day = getInputInt("Please Input Day:(1 - 31)");
		myCal.set(Calendar.DAY_OF_MONTH, day);
		int year = getInputInt("Please Input Year:");
		myCal.set(Calendar.YEAR, year);

		// Date date = new GregorianCalendar(year, month, day).getTime();
		Date date = new GregorianCalendar(year, month, day).getTime();

	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	    String dateFormatted = fmt.format(date);
	    Date newDate = null;
	    try {
		    newDate = fmt.parse(fmt.format(date));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return newDate;
  }
}
