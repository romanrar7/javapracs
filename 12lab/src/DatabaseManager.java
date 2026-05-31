import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseManager(String configPath) throws IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(configPath);
        props.load(in);
        in.close();
        url = props.getProperty("db.url");
        user = props.getProperty("db.user");
        password = props.getProperty("db.password");
    }

    public void insertClothes(Clothes clothes) {
        String sql = "INSERT INTO clothes_item(type,name,brand,price,size,color,extra1,extra2) VALUES(?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setString(1, clothes.getClass().getSimpleName().toUpperCase());
            ps.setString(2, clothes.getName());
            ps.setString(3, clothes.getBrand());
            ps.setDouble(4, clothes.getPrice());
            ps.setString(5, clothes.getSize());
            ps.setString(6, clothes.getColor());
            ps.setString(7, extra1(clothes));
            ps.setString(8, extra2(clothes));
            ps.executeUpdate();
            System.out.println("Запис у БД збережено.");
        } catch (SQLException e) {
            System.out.println("Помилка БД: " + e.getMessage());
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException ignored) { }
            if (conn != null) try { conn.close(); } catch (SQLException ignored) { }
        }
    }

    private String extra1(Clothes c) {
        if (c instanceof Pants) return String.valueOf(((Pants) c).getWaist());
        if (c instanceof Shirt) return ((Shirt) c).getCollarType();
        if (c instanceof Jacket) return ((Jacket) c).isWaterproof() ? "так" : "ні";
        if (c instanceof Skirt) return String.valueOf(((Skirt) c).getLengthCm());
        return "";
    }

    private String extra2(Clothes c) {
        if (c instanceof Pants) return String.valueOf(((Pants) c).getLength());
        if (c instanceof Shirt) return ((Shirt) c).getSleeveLength();
        if (c instanceof Jacket) return ((Jacket) c).getLining();
        if (c instanceof Skirt) return ((Skirt) c).getCut();
        return "";
    }
}
