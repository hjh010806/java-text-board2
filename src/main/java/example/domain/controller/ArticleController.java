package example.domain.controller;

import example.base.CommonUtil;
import example.domain.model.Article;
import example.domain.model.ArticleRepository;
import example.domain.view.ArticleView;

import java.util.ArrayList;
import java.util.Scanner;

// Mode - Controller - View

public class ArticleController {   // Model + Controller

    CommonUtil commonUtil = new CommonUtil();
    ArticleView articleView = new ArticleView();
    ArticleRepository articleRepository = new ArticleRepository();

    Scanner scan = commonUtil.getScanner();
    int WRONG_VALUE = -1;


    public void search() {
        // 검색어를 입력
        System.out.println("검색 키워드를 입력해주세요 :");
        String keyword = scan.nextLine();
        ArrayList<Article> searchedList = articleRepository.findArticleByKeyword(keyword);

        articleView.printArticleList(searchedList);
    }

    public void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = articleRepository.findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        article.increaseHit();
        articleView.printArticleDetail(article);
        detailMenu(inputId);
        articleView.printDetailComment(article);

    }
    public void delete() {

        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = articleRepository.findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        articleRepository.deleteArticle(article);
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }

    public void update() {
        System.out.print("수정할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = articleRepository.findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();

        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();

        articleRepository.updateArticle(article, newTitle, newBody);
        System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
    }

    public void list() {
        ArrayList<Article> articleList = articleRepository.findAll();
        articleView.printArticleList(articleList); // 전체 출력 -> 전체 저장소 넘기기
    }

    public void add() {

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        articleRepository.saveArticle(title, body);
        System.out.println("게시물이 등록되었습니다.");

    }

    private int getParamAsInt(String param, int defaultValue) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return defaultValue;
        }
    }
    public void detailMenu(int articleId) {
        System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
        String cmd = scan.nextLine();

        switch (cmd) {
            case "1":
                System.out.print("댓글 내용 : ");
                String newComment = scan.nextLine();
                articleRepository.saveComment(articleId, newComment); // 현재 상세보기 중인 게시물의 번호를 전달
                break;
            // 다른 기능들의 처리
        }

    }
}