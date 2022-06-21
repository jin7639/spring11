package spring.dao;

import org.springframework.stereotype.Component;
import spring.dto.BoardDto;

import java.sql.*;
@Component
public class BaordDao {

    public static boolean save(BoardDto boardDto) throws ClassNotFoundException, SQLException {
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul","root","1234");
            System.out.println("Connection");

            String sql = "insert into board(btitle,bcontent)values(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println("DB Connect error : " + e);
        }
        return false;
    }
}
