package example.domain.model;

import example.base.CommonUtil;

import java.sql.*;
import java.util.ArrayList;

public class ArticleMySQLRepository implements Repository {
    private static final String URL = "jdbc:mysql://localhost:3306/t2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @Override
    public void makeTestData() {

    }

    @Override
    public ArrayList<Article> findArticleByKeyword(String keyword) {
        ArrayList<Article> resultList = new ArrayList<>();
        String sql = "SELECT * FROM Article WHERE title LIKE ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setTitle(rs.getString("title"));
                    article.setBody(rs.getString("body"));
                    article.setHit(rs.getInt("hit"));
                    article.setRegDate(String.valueOf(rs.getTimestamp("regDate").toLocalDateTime()));
                    resultList.add(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;    }

    @Override
    public Article findArticleById(int id) {
        String sql = "SELECT * FROM Article WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setTitle(rs.getString("title"));
                    article.setBody(rs.getString("body"));
                    article.setHit(rs.getInt("hit"));
                    article.setRegDate(String.valueOf(rs.getTimestamp("regDate").toLocalDateTime()));
                    return article;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteArticle(Article article) {
        String sql = "DELETE FROM Article WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, article.getId());
            pstmt.executeUpdate();
            System.out.println("게시물이 수정되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateArticle(Article article, String newTitle, String newBody) {
        CommonUtil commonUtil = new CommonUtil();
        String sql = "UPDATE Article SET title = ?, body = ?, regDate = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setString(2, newBody);
            pstmt.setString(3, commonUtil.getCurrentDateTime());
            pstmt.setInt(4, article.getId());

            pstmt.executeUpdate();
            System.out.println("게시물이 수정되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Article> findAll() {
        return null;
    }

    @Override
    public Article saveArticle(String title, String body) {
        CommonUtil commonUtil = new CommonUtil();
        String sql = "INSERT INTO Article ( title, body, hit, regDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, body);
            pstmt.setInt(3, 0);
            pstmt.setString(4, commonUtil.getCurrentDateTime());

            // 실행
            pstmt.executeUpdate();
            System.out.println("데이터가 저장되었습니다");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
