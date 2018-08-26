import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//CHecked by Facilitator - Bhanuka

@SuppressWarnings("serial")
public class Library implements Serializable { //changed class name's first letter to uppercase
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double macFinesOwned = 5.0;
	private static final double damageFee = 2.0;
	
	private static Library self;// updated by reviewer - Class name from library to Library
	private int bookId;
	private int memberId;
	private int loanId;
	private Date loadDate;
	
	private Map<Integer, Book> bookCatalog; // Modetared by Moderator (Hijas)replaced a meaningful variable name as bookCatalog 
	private Map<Integer, Member> members;
	private Map<Integer, Loan> loans;
	private Map<Integer, Loan> currentLoans;
	private Map<Integer, Book> damagedBooks;
	//Updated by reviewer-Dushan - above class names should be updated with first letter Capital

	private Library() { //Updated by reviewer- Class name constructor must be Library not library
		bookCatalog = new HashMap<>(); // Modetared by Moderator (Hijas)replaced a meaningful variable name as bookCatalog 
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	public static synchronized Library INSTANCE() {		//updated by reviewer library to Library
		if (self == null) {
			Path path = Paths.get(LIBRARY_FILE);			
			if (Files.exists(path)) {	
				try (ObjectInputStream lof = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					self = (library) lof.readObject();
					Calendar.getInstance().setDate(self.loadDate);
					lof.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library();
		}
		return self;
	}

	
	public static synchronized void saveLibrary() { //changed method name to meet guidelines
		//updated by reviewer- method name from save to saveLibrary
		if (self != null) {
			self.loadDate = Calendar.getInstance().Date();
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				lof.writeObject(self);
				lof.flush();
				lof.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int bookID() { //changed method name to meet guidelines
		return bookId;
	}
	
	
	public int memberID() { //changed method name to meet guidelines
		return memberId;
	}
	
	
	private int nextBookId() {
		return bookId++;
	}

	
	private int nectMemberId() {
		return memberId++;
	}

	
	private int nextLoanId() {
		return loanId++;
	}

	
	public List<Member> listMembers() {	//changed method name to meet guidelines	
		//changed by reviewer- class name Member
		return new ArrayList<Member>(members.values()); 
	}


	public List<Book> listBooks() {	//changed method name to meet guidelines
		//changed by reviewer- class name Book
		return new ArrayList<Book>(catalog.values()); 
	}


	public List<Loan> currentLoans() { //changed method name to meet guidelines
		return new ArrayList<Loan>(currentLoans.values());
	}


	public member addItem(String lastName, String firstName, String email, int phoneNo) {	//changed method name to meet guidelines	
		Member newMember = new Member(lastName, firstName, email, phoneNo, nextMemberId());
		//updated by reviewer - Class name from member to Member
		newMember.put(newMember.getId(), newMember);		
		return member;
	}

	
	public book addBook(String bookOne, String bookTwo, String bookThree) {	//changed method name to meet guidelines	
		Book newBook = new book(bookOne, bookTwo, bookThree, nextBookId());//updated by reviewer -variables from a,t,c to bookOne,bookTwo,bookThree
		catalog.put(newBook.ID(), newBook);		
		return newBook;
	}

	
	public Member getMember(int memberId) {
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public Book book(int bookId) { //changed first letter to lowercase
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int loanLimit() {
		return LOAN_LIMIT;
	}

	
	public boolean memberCanBorrow(Member member) {		//updated by reviewer -chaged from member to Member
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT ) 
			return false;
				
		if (member.getFinesOwed() >= MAX_FINES_OWED) 
			return false;
				
		for (loan loan : member.getLoans()) 
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {		
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	public loan issueLoan(book book, member member) {
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (Loan loan : currentLoans.values()) { //updated by reviewer from loan to Loan
			loan.checkOverDue();
		}		
	}


	public void repairBook(Book currentBook) { //updated by reviewer book to Book
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
