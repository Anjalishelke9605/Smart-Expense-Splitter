import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public void addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (category_id, amount, note, expense_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, expense.getCategoryId());
            ps.setDouble(2, expense.getAmount());
            ps.setString(3, expense.getNote());
            ps.setDate(4, Date.valueOf(expense.getDate()));

            ps.executeUpdate();
            System.out.println("✔ Expense added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses ORDER BY expense_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Expense exp = new Expense();
                exp.setId(rs.getInt("expense_id"));
                exp.setCategoryId(rs.getInt("category_id"));
                exp.setAmount(rs.getDouble("amount"));
                exp.setNote(rs.getString("note"));
                exp.setDate(rs.getDate("expense_date").toLocalDate());

                list.add(exp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
