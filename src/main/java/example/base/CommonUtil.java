package example.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.domain.model.Article;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CommonUtil {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Article> articleList = new ArrayList<>();


    public Scanner getScanner() {
        return scan;
    }

    // 모든 곳에서 자주 사용되는 것은 여기에 모아두는 것이 좋습니다.
    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        // 날짜와 시간의 형식을 지정합니다. 여기서는 연-월-일 시:분:초 형식을 사용합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        // 지정한 형식으로 날짜와 시간을 출력합니다.
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    public void mapper() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("article.json"), articleList);
            System.out.println("JSON파일이 업데이트 되었습니다");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 저장 중 오류가 발생했습니다" + e.getMessage());
        }
    }
}
