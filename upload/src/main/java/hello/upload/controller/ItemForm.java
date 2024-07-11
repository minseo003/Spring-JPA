package hello.upload.controller;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ㅡModelAttribute에서 쓰일 저장용 폼 객체
 */
@Data
public class ItemForm {
    private Long itemId;
    private String itemName;
    private List<MultipartFile> imageFiles;
    private MultipartFile attachFile;
}
