package com.guru.service.controller;

import com.guru.service.dto.GuruParamDto;
import com.guru.service.service.GuruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guru")
@RequiredArgsConstructor
public class GuruController {

    private final GuruService guruService;

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus addGuru(@RequestBody GuruParamDto request) {
        return guruService.add(request);
    }

    @GetMapping("/getDetail")
    @ResponseBody
    public GuruParamDto getDetail(Long id) {
        return guruService.getDetail(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public HttpStatus update(@RequestBody GuruParamDto request) {
        return guruService.update(request);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(Long id) {
        return guruService.delete(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<GuruParamDto> list() {
        return guruService.list();
    }

}
