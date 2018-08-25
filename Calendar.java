import java.util.Date;
import java.util.concurrent.TimeUnit;

// checked by Moderator
public class Calendar {
	
	private static Calendar self;
	private static java.util.Calendar calendarNow; //Variable name should be meaningful and camelCase
	
	
	private Calendar() {
		calendarNow = java.util.Calendar.getInstance(); //Variable name should be meaningful and camelCase
	}
	
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self; // COMMENT
	}
	
	public void incrementDate(int days) {
		calendarNow.add(java.util.Calendar.DATE, days);		 //Variable name should be meaningful and camelCase
	}
	
	public synchronized void setDate(Date date) {
		try {
			calendarNow.setTime(date);
	        calendarNow.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendarNow.set(java.util.Calendar.MINUTE, 0);  
	        calendarNow.set(java.util.Calendar.SECOND, 0);  
	        calendarNow.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calendarNow.set(java.util.Calendar.HOUR_OF_DAY, 0);  //Variable name should be meaningful and camelCase
	        calendarNow.set(java.util.Calendar.MINUTE, 0);  
	        calendarNow.set(java.util.Calendar.SECOND, 0);  
	        calendarNow.set(java.util.Calendar.MILLISECOND, 0);
			return calendarNow.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) {
		Date now = Date();
		calendarNow.add(java.util.Calendar.DATE, loanPeriod); //Variable name should be meaningful and camelCase
		Date dueDate = calendarNow.getTime();
		calendarNow.setTime(now);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
