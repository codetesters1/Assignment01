import java.util.ArrayList;
import java.util.List;

// author Hijas Ahamed

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private Library library; // version 1.00 the (object reference variable)first letter of the library replaced capital "Library" and varible name L replaced to library.
	private Member member;   // version 1.01 the first letter of the member replaced capital "Member" and varible name M replaced to member.
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	private book book; // version 1.02 variable name B replaced to Book
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE(); // version 1.03 varible name L replaced library as a parameter
		state = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.setState(BorrowBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

		
	public void Swiped(int memberId) {  // version 1.04 the method name Swiped replaced to swiped due to lowerCamelCase 
		if (!state.equals(CONTROL_STATE.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = library.getMember(memberId); //version 1.05 variable name L replaced to library  as a parameter
		if (M == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (library.memberCanBorrow(M)) {  //version 1.06 variable name L replaced to library  as a parameter
			PENDING = new ArrayList<>();
			ui.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void Scanned(int bookId) { // version 1.07 the method name first letter of the Scanned replaced to scanned due to lowerCamelCase 
		book= null; // // version 1.08 variable name B replaced to Book
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.Book(bookId); // version 1.09 variable name L replaced to library  as a parameter
		if (book== null) {  // version 1.10 changed variable used as a parameter
			ui.display("Invalid bookId");
			return;
		}
		if (!book.Available()) {  // version 1.10 above replaced variable used as a parameter
			ui.display("Book cannot be borrowed");
			return;
		}
		PENDING.add(book);  // version 1.11 above replaced variable used as a parameter
		for (book book : PENDING) {  // version 1.12 above replaced variable used as a parameter
			ui.display(book.toString()); // version 1.13 above replaced variable used as a parameter
		}
		if (L.loansRemainingForMember(M) - PENDING.size() == 0) {
			ui.display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void complete() { // version 1.14 method name first letter of Complete replaced to complete due to lowerCamelCase
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (book book : PENDING) { // version 1.15 above replaced variable used as a parameter
				ui.display(b.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UI_STATE.FINALISING);
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book b : PENDING) {
			loan loan = L.issueLoan(b, M);
			COMPLETED.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}
	
	
}
