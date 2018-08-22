import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {// class name: "Loan" not "loan" must start with capital letter
	
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int myId;// variable name : "myId;" not "ID;" - all uppercase is used for constants only
	private Book myBook;
	// use meaningful name ,camelBack so "Book myBook;" not "book B;"
	//class Name "Book" not "book" starts with capital letter - The file book.java must be updated to reflect this fact 
	private Member myMember;
	// use meaningful name ,camelBack so "Member myMember;" not "member M;"
	//class Name "Member" not "member" starts with capital letter - The file member.java must be updated to reflect this fact
	private Date myDueDate;  
	// use meaningful name ,camelBack so "Date myDueDate;" not "Date D;"
	private LOAN_STATE state;


	
	public Loan(int loanId, Book book, Member member, Date dueDate) {
	
	// constructor is the same class name - starts with capital letter 
	// each argument of a class type - the type is also must start with capital letter camelBack
        
		this.myId    =loanID;// Variable name must be meaningful - camelBack style
		this.myBook  =book;   // Variable name must be meaningful - camelBack style
		this.myMember=member; //Variable name must be meaningful - camelBack style
		this.myDueDate=dueDate;//Variable name must be meaningful - camelBack style
		this.state = LOAN_STATE.CURRENT;
	}

	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(myDueDate))) {
	//"Calendar.getInstance().Date().after(myDueDate)" rather "Calendar.getInstance().Date().after(D)"
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}


	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer getId() {
		return this.myId;//"return this.myId;" not "return ID;"/* Variable name must be meaningful - camelBack style*/
	}


	public Date getDueDate() {
		return this.myDueDate;//"return this.myDueDate" not "return D;"/* Variable name must be meaningful - camelBack style*/
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.getId()).append(" : ")
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.Title()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
