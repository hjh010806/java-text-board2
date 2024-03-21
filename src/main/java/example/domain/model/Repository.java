package example.domain.model;

import java.util.ArrayList;

public interface Repository {


      void makeTestData();

      ArrayList<Article> findArticleByKeyword(String keyword);

      Article findArticleById(int id);

      void deleteArticle(Article article);

      void updateArticle(Article article, String newTitle, String newBody);

      ArrayList<Article> findAll();

      Article saveArticle(String title, String body);
}
