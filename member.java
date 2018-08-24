import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String lastName; //version 1.0 variable name : "LastName;" not "LN;" - all uppercase is used for constants onlye' 
	private String firstName; //version 1.0 variable name : "firstName;" not "FN;" - all uppercase is used for constants only
	private String eMail; //version 1.0 variable name : "eMail;" not "EM;" - all uppercase is used for constants only
	private int phoneNo; //version 1.0 variable name : "phoneNo;" not "PN;" - all uppercase is used for constants only
	private int idNo; //version 1.0 variable name : "idNo;" not "ID;" - all uppercase is used for constants only
	private double fineAmount; //version 1.0 variable name : "fineAmount;" not "FINES;" - all uppercase is used for constants only
	
	private Map<Integer, Loan> loanNo; //version 1.0 variable name : "fineAmount;" not "FINES;" - all uppercase is used for constants only
	//version 2.0 -class Name "Loan" not "loan" starts with capital letter - The file loan.java must be updated to reflect this fact
	
	public member(String lastName, String firstName, String eMail, int phoneNo, int idNo) { // version 1.0 changed variables acoordingly, email-eMail, id-idNo
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

	
	public void takeOutLoan(loan loan) {
		if (!loanNo.containsKey(loan.getId())) { //version 1.0 Variable name must be meaningful - camelBack style
			loanNo.put(loan.getId(), loan); //version 1.0 Variable name must be meaningful - camelBack style
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


	public void addFine(double fine) {
		fineAmount += fine; //version 1.0 Variable name must be meaningful - camelBack style
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fineAmount) { //version 1.0 Variable name must be meaningful - camelBack style
			change = amount - fineAmount; //version 1.0 Variable name must be meaningful - camelBack style
			fineAmount = 0; //version 1.0 Variable name must be meaningful - camelBack style
		}
		else {
			fineAmount -= amount; //version 1.0 Variable name must be meaningful - camelBack style
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
