import java.time.LocalDate;


public class Expense  {


private int id;
private int categoryId;
private double amount;
private String note;
private LocalDate date;


public Expense() {}


public Expense(int categoryId, double amount, String note, LocalDate date) {
this.categoryId = categoryId;
this.amount = amount;
this.note = note;
this.date = date;
}


public int getId() { return id; }
public void setId(int id) { this.id = id; }


public int getCategoryId() { return categoryId; }
public void setCategoryId(int categoryId) { this.categoryId = categoryId; }


public double getAmount() { return amount; }
public void setAmount(double amount) { this.amount = amount; }


public String getNote() { return note; }
public void setNote(String note) { this.note = note; }


public LocalDate getDate() { return date; }
public void setDate(LocalDate date) { this.date = date; }
}