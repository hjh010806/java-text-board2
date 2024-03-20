package example.domain.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.base.CommonUtil;
import example.domain.model.Article;
import example.domain.model.ArticleFileRepository;
import example.domain.view.ArticleTestView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticleTestController {
    CommonUtil commonUtil = new CommonUtil();
    ArticleTestView articleTestView = new ArticleTestView();
    ArticleFileRepository articleFileRepository = new ArticleFileRepository();

    Scanner scan = commonUtil.getScanner();
    int WRONG_VALUE = -1;


    public void add() {

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        articleFileRepository.saveObjectToJsonFile(title, body);
        System.out.println("게시물이 등록되었습니다.");

    }
    public void list() {

        ArrayList<Article> articleList = articleFileRepository.findAll();
        articleTestView.printTestList(articleList); // 전체 출력 -> 전체 저장소 넘기기
    }
    public Article findArticleById(int id) {

        return articleFileRepository.findById(id);

    }



}
