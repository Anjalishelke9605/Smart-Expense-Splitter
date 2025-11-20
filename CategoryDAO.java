import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class CategoryDAO {


public Map<Integer, String> listCategories() {
String sql = "SELECT category_id, name FROM categories ORDER BY name";
Map<Integer, String> map = new LinkedHashMap<>();


try (Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement(sql);
ResultSet rs = ps.executeQuery()) {


while (rs.next()) {
map.put(rs.getInt("category_id"), rs.getString("name"));
}


} catch (SQLException e) {
e.printStackTrace();
}
return map;
}


public String findNameById(int id) {
String sql = "SELECT name FROM categories WHERE category_id = ?";


try (Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement(sql)) {


ps.setInt(1, id);
try (ResultSet rs = ps.executeQuery()) {
if (rs.next()) return rs.getString("name");
}


} catch (SQLException e) {
e.printStackTrace();
}
return null;
}
}