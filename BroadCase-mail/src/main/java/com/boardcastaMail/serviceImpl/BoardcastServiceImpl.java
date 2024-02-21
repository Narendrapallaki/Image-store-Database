package com.boardcastaMail.serviceImpl;

import com.boardcastaMail.exception.InvalidFileExtensionException;
import com.boardcastaMail.service.BoardcastService;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class BoardcastServiceImpl implements BoardcastService {


    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;



    @Value("${files.storage}")
    public String folderLocation;



    @Override
    public Map<String, Object> uploadExcel(MultipartFile file,String subject) throws Exception {

        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        log.info(fileExtension);
        ArrayList<String> list = new ArrayList<>();
        list.add("xls");
        list.add("xlsx");
        list.add("csv");
        if (!list.contains(fileExtension)) {
        	log.error("error occured due to InvalidFileExtension");
            throw new InvalidFileExtensionException("please provide excel file");

        }
      List<String> emailList=new ArrayList<>();
        String nameCell=null;
        String emailCell=null;
       try{
           XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
           XSSFSheet sheet= workbook.getSheetAt(0);
           for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
           {
               XSSFRow row= sheet.getRow(i);
               if(row!=null)
               {
                   nameCell= String.valueOf(row.getCell(1));
                     emailCell= String.valueOf(row.getCell(2));
                     if(nameCell!=null && emailCell!=null)
                     {
                         sendingMailsOneByOne(nameCell,emailCell,subject);
                     }
               }
               emailList.add(emailCell);
           }
       }
       catch(IOException ex)
       {
           throw new RuntimeException(ex);
       }
       // sendingMails(emailList,subject);
        Map<String,Object> map=new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("message","Mail sent successfully");
        
        return map;
    }

    private boolean sendingMails(List<String> emailList,String subject) throws Exception {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail, true);
        for (String email : emailList) {
            mimeMessageHelper.addTo(email);
        }
        String processedTemplate = readingMailTemplateFromText("warningMail.html");
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("[Boardcast]  "+subject);
        mimeMessageHelper.setText(processedTemplate, true);
        javaMailSender.send(mail);
        return true;
    }


    private boolean sendingMailsOneByOne(String name,String emails,String subject) throws Exception {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail, true);

        mimeMessageHelper.addTo(emails);
        String template=readingMailTemplateFromText("warningMail.html");
        String processedTemplate =template.replace("@empName", name);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("[Boardcast] "+subject);
        mimeMessageHelper.setText(processedTemplate, true);
        javaMailSender.send(mail);
        return true;
    }


    private String readingMailTemplateFromText(String fileName) throws Exception {

        try {
            String fullFileName = folderLocation+"templates/" + fileName;
            File file = ResourceUtils.getFile(fullFileName);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();

            throw new Exception("Mail not sent");
        }
    }



}
