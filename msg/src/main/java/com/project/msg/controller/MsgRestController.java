package com.project.msg.controller;

import com.project.msg.dto.FileInfoDto;
import com.project.msg.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MsgRestController {

    private final GenerateControllerSource generateControllerSource;
    private final GenerateServiceSource generateServiceSource;
    private final GenerateXmlSource generateXmlSource;

    private final GenerateDtoSource generateDtoSource;

    private final GenerateMybatisSource generateMybatisSource;
    //generateAll() 함수도 추후 추가

    @GetMapping("generate")
    public ResponseEntity<List<String>> generateSource(FileInfoDto fileInfoDto) {

        //ResponseEntity 정보 : 소스파일 + 메타정보 ?

        log.info(fileInfoDto.toString());

        List<String> fileList = new ArrayList<>();

        String controller = generateControllerSource.generate(fileInfoDto);
        String service = generateServiceSource.generate(fileInfoDto);
        String xml = generateXmlSource.generate(fileInfoDto);
        String dto = generateDtoSource.generate(fileInfoDto);
        String config = generateMybatisSource.generate(fileInfoDto);

        fileList.add(controller);
        fileList.add(xml);
        fileList.add(service);
        fileList.add(dto);
        fileList.add(config);

        log.info(fileList.toString());

        return new ResponseEntity<List<String>>(fileList, HttpStatus.OK);
    }
}
