import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String lastName; //version 1.0 changed variable 'LN' to 'lastName'
	private String firstName; //version 1.0 changed variable 'FN' to 'firstName'
	private String eMail; //version 1.0 changed variable 'EM' to 'eMail'
	private int phoneNo; //version 1.0 changed variable 'PN' to 'phoneNo'
	private int idNo; //version 1.0 changed variable 'ID' to 'idNo'
	private double fineAmount; //version 1.0 changed variable 'FINES' to 'fineAmount'
	
	private Map<Integer, loan> loanNo;

	
	public member(String lastName, String firstName, String eMail, int phoneNo, int idNo) { // version 1.0 changed variables acoordingly, email-eMail, id-idNo
		this.lastName = lastName;//changed variable name
		this.firstName = firstName;//changed variable name
		this.eMail = eMail;//changed variable and parameter name
		this.phoneNo = phoneNo;//changed variable name
		this.idNo = idNo;//changed variable and parameter name
		
		this.loanNo = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(ID).append("\n")
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		for (loan loan : LNS.values()) {
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() {
		return idNo; //changed variable and parameter name
	}

	
	public List<loan> getLoans() {
		return new ArrayList<loan>(loanNo.values());//version 1.0 changed LNS to loanNo
	}

	
	public int getNumberOfCurrentLoans() {
		return loanNo.size();//version 1.0 changed LNS to loanNo
	}

	
	public double getFinesOwed() {
		return fineAmount; //version 1.0 changed parameter 'FINES' to 'fineAmount'
	}

	
	public void takeOutLoan(loan loan) {
		if (!loanNo.containsKey(loan.getId())) { //version 1.0 changed LNS to loanNo
			loanNo.put(loan.getId(), loan); //version 1.0 changed LNS to loanNo
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		return lastName; //changed parameter name LN to lastName;
	}

	
	public String getFirstName() {
		return firstName; //changed parameter name FN to firstName;
	}


	public void addFine(double fine) {
		fineAmount += fine; //version 1.0 changed variable 'FINES' to 'fineAmount'
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fineAmount) { //version 1.0 changed variable 'FINES' to 'fineAmount'
			change = amount - fineAmount; //version 1.0 changed variable 'FINES' to 'fineAmount'
			fineAmount = 0; //version 1.0 changed variable 'FINES' to 'fineAmount'
		}
		else {
			fineAmount -= amount; //version 1.0 changed variable 'FINES' to 'fineAmount'
		}
		return change;
	}


	public void dischargeLoan(loan loan) {
		if (loanNo.containsKey(loan.getId())) { //version 1.0 changed LNS to loanNo
			loanNo.remove(loan.getId()); //version 1.0 changed LNS to loanNo
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
