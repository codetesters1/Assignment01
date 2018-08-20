

import java.io.Serializable;



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

	public Integer ID() {
		return BookID;
	}

	public String Title() {
		return Title;
	}


	
	public boolean Available() {
		return state == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return state == STATE.ON_LOAN;
	}

	
	public boolean Damaged() {
		return state == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void Return(boolean DAMAGED) {
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

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
