import java.util.Date;
import java.util.concurrent.TimeUnit;
// Author by Waqas
// checked by Moderator
//Checked by Reviewer-Dushan
//CHecked by Documenter - Bhanuka
public class Calendar {
	
	private static Calendar selfCal; //updated by reviewer- self to selfCal
	private static java.util.Calendar calendarNow; //Variable name should be meaningful and camelCase
	
	
	private Calendar() {
		calendarNow = java.util.Calendar.getInstance(); //Variable name should be meaningful and camelCase
	}
	
	public static Calendar getInstance() {
		if (selfCal == null) { //updated by reviewer- self to selfCal
			selfCal = new Calendar(); //updated by reviewer- self to selfCal
		}
		return selfCal; 
	}
	
	public void incrementDate(int incDays) { // version 4.0 final comment by author(waqas) variable days changed - updated by reviewer-Dushan - days to incDays
		calendarNow.add(java.util.Calendar.DATE, incDays);		 //Variable name should be meaningful and camelCase
	}
	
	public synchronized void setDate(Date dateNow) { //updated by reviewer -Dushanp- variable name must be meaningful
		try {
			calendarNow.setTime(dateNow);
	        calendarNow.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendarNow.set(java.util.Calendar.MINUTE, 0);  
	        calendarNow.set(java.util.Calendar.SECOND, 0);  
	        calendarNow.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date dateNow() { //updated by reviewer Dushan - method name must be camelBack
		try {
	        calendarNow.set(java.util.Calendar.HOUR_OF_DAY, 0);  //version 4.0 update by author (Waqas)- Variable name should be meaningful and camelCase - variable name changed to calendarNow
	        calendarNow.set(java.util.Calendar.MINUTE, 0);  //version 4.0 update by author (Waqas)- Variable name should be meaningful and camelCase - variable name changed to calendarNow
	        calendarNow.set(java.util.Calendar.SECOND, 0);  //version 4.0 update by author (Waqas)- Variable name should be meaningful and camelCase - variable name changed to calendarNow
	        calendarNow.set(java.util.Calendar.MILLISECOND, 0);//version 4.0 update by author (Waqas)- Variable name should be meaningful and camelCase - variable name changed to calendarNow
			return calendarNow.getTime(); //version 4.0 update by author (Waqas)- Variable name should be meaningful and camelCase - variable name changed to calendarNow
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) {
		Date nowDate = Date();
		calendarNow.add(java.util.Calendar.DATE, loanPeriod); //version 4.0 update by author (Waqas)- Variable name should be meaningful and camelCase - variable name changed to calendarNow
		Date dueDate = calendarNow.getTime();
		calendarNow.setTime(now);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMilliSeconds = Date().getTime() - targetDate.getTime();  // Version 4.0 final update (Waqas)- Variable name should be meaningful diffMillis to diffMilliSeconds
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
