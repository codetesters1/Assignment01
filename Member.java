import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Checked By Moderator
//Checked by Moderator - Bhanuka
@SuppressWarnings("serial")
public class Member implements Serializable { //version 3.0 - class name: "Member" not "member" must start with capital letter

	private String lastName; //version 1.0 variable name : "LastName;" not "LN;" - all uppercase is used for constants onlye' 
	private String firstName; //version 1.0 variable name : "firstName;" not "FN;" - all uppercase is used for constants only
	private String eMail; //version 1.0 variable name : "eMail;" not "EM;" - all uppercase is used for constants only
	private int phoneNo; //version 1.0 variable name : "phoneNo;" not "PN;" - all uppercase is used for constants only
	private int idNo; //version 1.0 variable name : "idNo;" not "ID;" - all uppercase is used for constants only
	private double fineAmount; //version 1.0 variable name : "fineAmount;" not "FINES;" - all uppercase is used for constants only
	
	private Map<Integer, Loan> loanNo; //version 1.0 variable name : "fineAmount;" not "FINES;" - all uppercase is used for constants only
	//version 2.0 -class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
	
	public Member(String lastName, String firstName, String eMail, int phoneNo, int idNo) { // version 1.0 changed variables acoordingly, email-eMail, id-idNo
		//Should be Member - Class starts with capital - Edited by Moderator (Bhanuka)
		this.lastName = lastName;//version 1.0 Variable name must be meaningful - camelBack style
		this.firstName = firstName;//version 1.0 Variable name must be meaningful - camelBack style
		this.eMail = eMail;//version 1.0 Variable name must be meaningful - camelBack style
		this.phoneNo = phoneNo;//version 1.0 Variable name must be meaningful - camelBack style
		this.idNo = idNo;//version 1.0 Variable name must be meaningful - camelBack style
		
		this.loanNo = new HashMap<>();// Variable name must be meaningful - camelBack style
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(idNo).append("\n") //version 2.0 Variable name must be meaningful - camelBack style
		  .append("  Name:  ").append(lastName).append(", ").append(FN).append("\n") //version 2.0 Variable name must be meaningful - camelBack style
		  .append("  Email: ").append(eMail).append("\n") //version 2.0 Variable name must be meaningful - camelBack style
		  .append("  Phone: ").append(phoneNo) //version 2.0 Variable name must be meaningful - camelBack style
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fineAmount)) //version 2.0 Variable name must be meaningful - camelBack style
		  .append("\n");
		
		for (Loan currentLoan : loanNo.values()) { //version 2.0 Variable name must be meaningful - camelBack style
			//version 2.0 -class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
			//version 2.0- "Loan currentLoan" not "loan loan" as the class type must start with capital letter and meaningful
			sb.append(currentLoan).append("\n"); //version 2.0 -not  sb.append(loan).append("/n"); rather sb.append(currentLoan).append("/n");
		}		  
		return sb.toString();
	}

	
	public int getId() {
		return idNo; //version 1.0 Variable name must be meaningful - camelBack style
	}

	
	public List<Loan> getLoans() { //version 2.0 -class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
		return new ArrayList<loan>(loanNo.values());//version 1.0 Variable name must be meaningful - camelBack style
	}

	
	public int getNumberOfCurrentLoans() {
		return loanNo.size();//version 1.0 Variable name must be meaningful - camelBack style
	}

	
	public double getFinesOwed() {
		return fineAmount; //version 1.0 Variable name must be meaningful - camelBack style
	}

	
	public void takeOutLoan(Loan loanAmt) { //version 3.0 -class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
		//version 3.0 Variable name must be meaningful - camelBack style
		if (!loanNo.containsKey(loanAmt.getId())) { //version 3.0 Variable name must be meaningful - camelBack style
			loanNo.put(loanAmt.getId(), loan); //version 3.0 Variable name must be meaningful - camelBack style
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		return lastName; //version 1.0 Variable name must be meaningful - camelBack style
	}

	
	public String getFirstName() {
		return firstName; //version 1.0 Variable name must be meaningful - camelBack style
	}
	
	public String getEmail() { //singletons must have get instance
		return eMail; 
	}
	
	public String getPhoneNo() { //singletons must have get instance
		return phoneNo; 
	} 
	
	public void addFine(double fineAmt) { //version 3.0  Variable name must be meaningful - camelBack style
		fineAmount += fineAmt; //version 1.0 Variable name must be meaningful - camelBack style
		//version 3.0  Variable name must be meaningful - camelBack style - fineAMt - not fine
	}
	
	public double payFine(double payAmount) { //version 3.0 Variable name must be meaningful - camelBack style - not amount must be payAmount
		if (payAmount < 0) { //version 3.0 Variable name must be meaningful - camelBack style
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double changeAmt = 0; //version 3.0 Variable name must be meaningful - camelBack style - not change must be be changeAmt
		if (payAmount > fineAmount) { //version 1.0 Variable name must be meaningful - camelBack style
			//version 3.0 Variable name must be meaningful - camelBack style - payAmount
			change = payAmount - fineAmount; //version 1.0 Variable name must be meaningful - camelBack style
			//version 3.0 Variable name must be meaningful - camelBack style -payAmount
			fineAmount = 0; //version 1.0 Variable name must be meaningful - camelBack style
		}
		else {
			fineAmount -= payAmount; //version 1.0 Variable name must be meaningful - camelBack style
			//version 3.0 Variable name must be meaningful - camelBack style- payAmount
		}
		return changeAmt; //version 3.0 Variable name must be meaningful - camelBack style - not change must be be changeAmt
	}


	public void dischargeLoan(Loan loanAmt) { //version 3.0 -class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
		//version 3.0 Variable name must be meaningful - camelBack style loanAmt -not loan
		if (loanNo.containsKey(loanAmt.getId())) { //version 1.0 changed LNS to loanNo
			//version 3.0 Variable name must be meaningful - camelBack style loanAmt -not loan
			loanNo.remove(loanAmt.getId()); //version 1.0 changed LNS to loanNo
			//version 3.0 Variable name must be meaningful - camelBack style loanAmt -not loan
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
