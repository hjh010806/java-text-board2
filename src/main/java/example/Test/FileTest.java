package example.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.base.CommonUtil;
import example.domain.model.Article;
import java.io.File;
import java.io.IOException;

public class FileTest {


    public static void main(String[] args) {
        CommonUtil commonUtil = new CommonUtil();
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, commonUtil.getCurrentDateTime());
        // 저장할 객체 생성

        // 객체를 JSON 파일로 저장
        saveObjectToJsonFile1(a1, "object.json");

        // JSON 파일에서 객체 읽어오기
        Article a2 = loadObjectFromJsonFile("object.json");

        // 읽어온 객체 출력
        System.out.println("제목 : " + a2.getTitle());
        System.out.println("내용 : " + a2.getBody());


    }

    // 객체를 JSON 파일로 저장하는 메서드
    public static void saveObjectToJsonFile1(Object object, String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), object);
            System.out.println("객체가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JSON 파일에서 객체를 읽어오는 메서드
    public static Article loadObjectFromJsonFile(String fileName) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fileName), Article.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}





