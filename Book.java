

import java.io.Serializable;
//Updated by Moderator- Dushan
//Updated by Reviewer - Bhanuka 



@SuppressWarnings("serial")
public class Book implements Serializable {   // version_1.00 changed class name first letter into capital
	
	private String title;   // version_1.01 T replaced to tilte
	private String author;  // version_1.02 A replaced to author
	private String callNo;  // version_1.03 C replaced to callNo
	private int bookId;     // version_1.04 ID replaced to bookId
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public Book(String author, String title, String callNo, int id) { //changed class name first letter into capital
		this.author = author;   // version_1.05 A replaced to author
		this.title= title;      // version_1.06 T replaced to tilte
		this.callNo = callNo;   // version_1.07 C replaced to callNo
		this.bookId = id;       // version_1.08 ID replaced to bookId
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(bookId).append("\n")      / version_1.09 ID replaced to bookId
		  .append("  Title:  ").append(title).append("\n")   // version_1.10 T replaced to tilte
		  .append("  Author: ").append(author).append("\n")  // version_1.11 A replaced to author
		  .append("  CallNo: ").append(callNo).append("\n")  // version_1.12 C replaced to callNo
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer getId() {   // version 1.13 the method name ID replaced to getId
		return BookID;     // version_1.14 ID replaced to bookId
	}

	public String getTitle() { // method name Title replaced to gETtITLE 
		return title;       // version_1.15 T replaced to tilte
	}
	
	public Integer getAuthor() {   //Updated by Moderator -Dushan -singletons must have get instance
		return author;     
	}
	
	public Integer getCallNo() {   //Updated by Moderator -Dushan -singletons must have get instance
		return callNo;     
	}

	
	public boolean isAvailable() { // version 1.16 method name Availbale replaced to isAvailable 
		return state == STATE.AVAILABLE;
	}

	
	public boolean isOn_loan() {  //version 1.17 method name On_loan replaced to isOnLoan
		return state == STATE.ON_LOAN;
	}

	
	public boolean isDamaged() { //version 1.18 method name Damaged replaced to isDamaged
		return state == STATE.DAMAGED;
	}

	
	public void borrowBook() {    // version 1.19 method name replaced to borrowBook
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void returnBook(boolean DAMAGED) {   // version 1.20 method name replaced to returnBook 
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void repairBook() {      // version 1.21 method name Repair replaced to repairBook
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
