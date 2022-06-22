package spring.dao;

import org.springframework.stereotype.Repository;
import spring.dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDao {
        protected Connection con;
        protected PreparedStatement ps;
        protected ResultSet rs;

        public BoardDao(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul","root","1234");
                System.out.println("Connection");
            }catch (Exception e) {
                System.err.println("DB Connect error : " + e);
            }
        }
    public boolean save(BoardDto boardDto) {
        try {
            String sql = "insert into board(btitle,bcontent)values(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("save error : " + e);
        }
        return false;
    }

    public ArrayList<BoardDto> boardDtoList (){
            ArrayList<BoardDto> boardList = new ArrayList<>();
            String sql = "select * from board order by bno desc";
            try {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                    BoardDto board = new BoardDto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3)
                    );
                    boardList.add(board);
                }
                return boardList;
            } catch (SQLException e) {
                System.out.println("boardlist error : " + e);
            }
        return null;
    }


}
