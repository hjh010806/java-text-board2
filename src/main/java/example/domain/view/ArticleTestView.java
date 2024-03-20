package example.domain.view;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.domain.model.Article;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static example.Test.FileTest.loadObjectFromJsonFile;

public class ArticleTestView {
    public void printTestList(ArrayList<Article> targetList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Article> loadedList = mapper.readValue(new File("article.json"), new TypeReference<ArrayList<Article>>() {
            });

            System.out.println("===================");
            for (Article article : loadedList) {
                // 각 Article 객체를 출력
                System.out.println("번호 : " + article.getId());
                System.out.println("제목 : " + article.getTitle());
                System.out.println("내용 : " + article.getBody());
                System.out.println("조회수 : " + article.getHit());
                System.out.println("등록일 : " + article.getRegDate());
                System.out.println("===================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




