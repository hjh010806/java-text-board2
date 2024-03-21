package example.domain.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.base.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class ArticleFileRepository implements Repository {
    private int latestId = 1;
    CommonUtil commonUtil = new CommonUtil();

    private ArrayList<Article> articleList = new ArrayList<>();


    public void makeTestData() {}

    public ArticleFileRepository() {
        this.articleList = loadPostsFromFile("article.json");
        if (articleList.size() == 0) {
            latestId = 0;
            return;
        }
        int index = this.articleList.size() - 1; // 개수 -1. 마지막 인덱스
        Article article = articleList.get(index);
        latestId = article.getId();
    }

    private ArrayList<Article> loadPostsFromFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 파일로부터 Post 객체 리스트를 읽어옵니다.
            return mapper.readValue(new File(filePath), new TypeReference<ArrayList<Article>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
            // 파일 읽기 실패 시 빈 리스트를 반환합니다.
            return new ArrayList<>();
        }
    }

    public Article saveArticle(String title, String body) {
        // 번호는 latestId, 제목이 title, 내용이 body, 조회수 0, 등록날짜 현재시간인 게시물을
        // json 파일로 저장하기
        CommonUtil commonUtil = new CommonUtil();

        latestId++;

        Article article = new Article(latestId, title, body, 0, commonUtil.getCurrentDateTime());
        articleList.add(article);
        ObjectMapper Mapper = new ObjectMapper();

        try {
            Mapper.writeValue(new File("article.json"), articleList);
            System.out.println("객체가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return article;
    }

    public ArrayList<Article> findAll() {
        ArrayList<Article> arrayList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            ArrayList<Article> articleList = mapper.readValue(new File("article.json"), new TypeReference<ArrayList<Article>>() {
            });

            // 전체 게시물 목록 출력
            return articleList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public Article findArticleById(int id) {
//        id에 해당하는 게시물(article) 반환
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public ArrayList<Article> findArticleByKeyword(String keyword) {
        ArrayList<Article> searchedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }

        return searchedList;
    }

    public void deleteArticle(Article article) {

        articleList.remove(article);

        commonUtil.mapper();
    }

    public void updateArticle(Article article, String title, String body) {

        Article target = findArticleById(article.getId());

        if (target != null) {
            article.setTitle(title);
            article.setBody(body);
        }
        commonUtil.mapper();

    }

}
