import java.util.ArrayList;
import java.util.List;

// author Hijas Ahamed
//Updated by Reviewer - Bhanuka 

public class BorrowBookControl {
	
	private BorrowBookUI uiBook; //updated by moderator(Dushan) -variable name must be camelBack and meaningful( ui to uiBook)
	
	private Library library; // version 1.00 the (object reference variable)first letter of the library replaced capital "Library" and varible name L replaced to library.
	private Member member;   // version 1.01 the first letter of the member replaced capital "Member" and varible name M replaced to member.
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE stateCon;//updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state
	
	private List<Book> PENDING; //updated by moderator(Dushan) Class name first letter should be Capital letter-should be Book, not book
	private List<Loan> COMPLETED; //updated by moderator(Dushan) Class name first letter should be Capital letter-should be Loan not loan
	private Book book; // version 1.02 variable name B replaced to Book
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE(); // version 1.03 varible name L replaced library as a parameter
		stateCon = CONTROL_STATE.INITIALISED; //updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state
	}
	

	public void setUI(BorrowBookUI uiBook) { //updated by moderator(Dushan) -variable name must be camelBack
		if (!state.equals(CONTROL_STATE.INITIALISED)) { 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.uiBook = uiBook; //updated by moderator(Dushan) -variable name must be camelBack and meaningful( ui to uiBook)
		uiBook.setState(BorrowBookUI.UI_STATE.READY); //updated by moderator(Dushan) -variable name must be camelBack and meaningful( ui to uiBook)
		stateCon = CONTROL_STATE.READY;	//updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state 	
	}

		
	public void Swiped(int memberId) {  // version 1.04 the method name Swiped replaced to swiped due to lowerCamelCase 
		if (!state.equals(CONTROL_STATE.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId); //version 1.05 variable name L replaced to library  as a parameter and M to Member
		if (member == null) {
			uiBook.display("Invalid memberId");//updated by moderator(Dushan) -variable name must be camelBack and meaningful
			return;
		}
		if (library.memberCanBorrow(M)) {  //version 1.06 variable name L replaced to library  as a parameter
			PENDING = new ArrayList<>();
			uiBook.setState(BorrowBookUI.UI_STATE.SCANNING); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			stateCon = CONTROL_STATE.SCANNING; } //updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state
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
			uiBook.display("Invalid bookId"); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			return;
		}
		if (!book.Available()) {  // version 1.10 above replaced variable used as a parameter
			uiBook.display("Book cannot be borrowed"); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			return;
		}
		PENDING.add(book);  // version 1.11 above replaced variable used as a parameter
		for (Book book : PENDING) {  // version 1.12 above replaced variable used as a parameter
			uiBook.display(book.toString()); // version 1.13 above replaced variable used as a parameter
			//updated by moderator(Dushan) -variable name must be camelBack and meaningful
		}
		if (L.loansRemainingForMember(M) - PENDING.size() == 0) {
			uiBook.display("Loan limit reached"); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			Complete();
		}
	}
	
	
	public void completeBook() { // version 1.14 method name first letter of Complete replaced to complete due to lowerCamelCase
		//updated by moderator(Dushan) -variable name must be camelBack and meaningful complete to completeBook
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			uiBook.display("\nFinal Borrowing List"); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			for (Book book : PENDING) { // version 1.15 above replaced variable used as a parameter
				uiBook.display(book.toString()); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			}
			COMPLETED = new ArrayList<loan>();
			uiBook.setState(BorrowBookUI.UI_STATE.FINALISING); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			stateCon = CONTROL_STATE.FINALISING; //updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (Book bookName : PENDING) { ////updated by moderator(Dushan) -Should use Book not book and bookName variable not b
			Loan newloan = library.issueLoan(bookName, member); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
			COMPLETED.add(newLoan); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
		}
		uiBook.display("Completed Loan Slip"); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
		for (Loan newLoan : COMPLETED) { //updated by moderator(Dushan) -variable name must be camelBack and meaningful and class name first letter should be capital - not loan should be Loan
			uiBook.display(newLoan.toString()); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
		}
		uiBook.setState(BorrowBookUI.UI_STATE.COMPLETED); //updated by moderator(Dushan) -variable name must be camelBack and meaningful
		stateCon = CONTROL_STATE.COMPLETED; //updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state
	}

	
	public void cancelBook() { //updated by moderator(Dushan) -method name should be camelback star from simple letter - not cancel-should be cancelBook
		uiBook.setState(BorrowBookUI.UI_STATE.CANCELLED);//updated by moderator(Dushan) -variable name must be camelBack and meaningful
		stateCon = CONTROL_STATE.CANCELLED; //updated by moderator(Dushan) variable must be cambelBack start from simple letter-should be stateCon not state
	}
	
	
}
