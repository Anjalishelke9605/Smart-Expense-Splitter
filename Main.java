import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static CategoryDAO categoryDAO = new CategoryDAO();
    private static ExpenseService expenseService = new ExpenseService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> {
                    System.out.println("✔ Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void addExpense() {

        System.out.println("\n--- Available Categories ---");

        Map<Integer, String> categories = categoryDAO.listCategories();
        categories.forEach((id, name) -> System.out.println(id + ". " + name));

        System.out.print("Enter Category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Note: ");
        String note = scanner.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        Expense expense = new Expense(categoryId, amount, note, date);
        expenseService.addExpense(expense);
    }

    private static void viewExpenses() {

        System.out.println("\n--- All Expenses ---");

        List<Expense> list = expenseService.getAllExpenses();

        if (list.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        for (Expense e : list) {
            String categoryName = categoryDAO.findNameById(e.getCategoryId());

            System.out.println(
                    "ID: " + e.getId() +
                    " | Category: " + categoryName +
                    " | Amount: ₹" + e.getAmount() +
                    " | Note: " + e.getNote() +
                    " | Date: " + e.getDate()
            );
        }
    }
}
