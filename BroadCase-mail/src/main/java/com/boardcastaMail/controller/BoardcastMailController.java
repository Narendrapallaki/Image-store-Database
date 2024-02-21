//package com.boardcastaMail.controller;
//
//import com.boardcastaMail.service.BoardcastService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Map;
//@Slf4j
//@RestController
//@RequestMapping("api/v1/boardcast")
//public class BoardcastMailController {
//    @Autowired
//    private BoardcastService boardcastService;
//@PostMapping("/upload")
//    public ResponseEntity<Map<String,Object>> uploadExcel(@RequestParam MultipartFile file,String subject) throws Exception {
//    log.info("request sent to serviceImpl class");
//        Map<String,Object> map=this.boardcastService.uploadExcel(file,subject);
//        return ResponseEntity.ok().body(map);
//    }
//}
