package example.Test;

import example.base.CommonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBtest {
    private static final String URL = "jdbc:mysql://localhost:3306/t2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        CommonUtil commonUtil = new CommonUtil();
        String sql = "INSERT INTO Article ( title, body, hit, regDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "제목1");
            pstmt.setString(2, "내용1");
            pstmt.setInt(3, 0);
            pstmt.setString(4, commonUtil.getCurrentDateTime());

            // 실행
            pstmt.executeUpdate();
            System.out.println("데이터가 저장되었습니다");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
