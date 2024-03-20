package example.domain.model;

public class Article {
    private int id; // 번호
    private String title; // 제목
    private String body; // 내용
    private int hit; // 조회수
    private String regDate; // 등록날짜
    private String comments;
    private int commentId;
//    private ArrayList<String> comments;

    public Article() {
        this.comments = getComments();
        this.commentId = getCommentId();
    }
    public Article(int id, String title, String body, int hit, String regDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.hit = hit;
        this.regDate = regDate;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void increaseHit() {
        this.hit++;
    }
    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
