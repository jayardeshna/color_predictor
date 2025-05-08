import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/color_predictor";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Preference> getPreferences(int customerId) throws SQLException {
        List<Preference> preferences = new ArrayList<>();

        String query = "SELECT rgb, rating FROM preferences WHERE customer_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int[] rgb = new int[]{rs.getInt("rgb[0]"), rs.getInt("rgb[1]"), rs.getInt("rgb[2]")};
                    int rating = rs.getInt("rating");
                    preferences.add(new Preference(customerId, rgb, rating));
                }
            }
        }
        return preferences;
    }

}
