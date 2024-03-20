package example.domain.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.base.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ArticleFileRepository {
    ;
    private int latestId = 1;

    private ArrayList<Article> articleList = new ArrayList<>();

    public ArticleFileRepository(){
        this.articleList = loadPostsFromFile("article.json");
        if(articleList.size() == 0 ){
            latestId = 0;
            return;
        }
        int index = this.articleList.size() -1 ; // 개수 -1. 마지막 인덱스
        Article article = articleList.get(index);
        latestId = article.getId();
    }

    private ArrayList<Article> loadPostsFromFile(String filePath){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 파일로부터 Post 객체 리스트를 읽어옵니다.
            return mapper.readValue(new File(filePath), new TypeReference<ArrayList<Article>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
            // 파일 읽기 실패 시 빈 리스트를 반환합니다.
            return new ArrayList<>();
        }
    }

    public void saveObjectToJsonFile(String title, String body) {
        // 번호는 latestId, 제목이 title, 내용이 body, 조회수 0, 등록날짜 현재시간인 게시물을
        // json 파일로 저장하기
        CommonUtil commonUtil = new CommonUtil();

        latestId++;

        Article a1 = new Article(latestId, title, body, 0, commonUtil.getCurrentDateTime());
        articleList.add(a1);
        ObjectMapper Mapper = new ObjectMapper();

        try {
            Mapper.writeValue(new File("article.json"), articleList);
            System.out.println("객체가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Article> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Article> arrayList = new ArrayList<>();

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

    public Article findById(int id) {
//        id에 해당하는 게시물(article) 반환
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

}
