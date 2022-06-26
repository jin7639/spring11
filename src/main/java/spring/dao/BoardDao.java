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
                System.out.println("연동성공");
            }catch (Exception e) {
                System.out.println("DB Connect error : " + e);
            }
        }
    public boolean save(BoardDto boardDto){
        try{
            String sql = "insert into board(btitle,bcontent) values(?,?)";
            ps=con.prepareStatement(sql);

            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("save error : "+ e);
        }
        return false;
    }
    public ArrayList<BoardDto> list(){
        ArrayList<BoardDto> boardlist = new ArrayList<BoardDto>();
        try{
            String sql = "select * from board";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                BoardDto boardDto = new BoardDto(
                        rs.getInt(1),rs.getString(2)
                        ,rs.getString(3)
                );
                boardlist.add(boardDto);
            }
            return boardlist;
        }catch(Exception e){
            System.out.println("list error : "+ e);
        }
        return null;
    }


    public BoardDto board( int bno ) {
        String sql ="select * from board where bno="+bno;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if( rs.next() ) {
                BoardDto boardDto = new BoardDto(
                        rs.getInt(1),rs.getString(2),
                        rs.getString(3)
                );
                return boardDto;
            }
        }catch (Exception e) {
            System.out.println("board error : "+ e);
        } return null;


    }

    public boolean update(int bno,String btitle,String bcontent){
        try{
            String sql ="update board set btitle="+btitle+", bcontent="+bcontent+" where bno="+bno;
            ps= con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            System.out.println("udpate error : "+ e);
        }
        return false;
    }

    public boolean delete(int bno){
        try{
            String sql ="delete from board where bno="+bno;
            ps= con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            System.out.println("delete error : "+ e);
        }
        return false;
    }

}
