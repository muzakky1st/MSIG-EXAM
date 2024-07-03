package com.guru.service.service;

import com.guru.service.dto.GuruParamDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface GuruService {
    HttpStatus add (GuruParamDto request);
    GuruParamDto getDetail (Long id);
    HttpStatus update (GuruParamDto request);
    HttpStatus delete (Long id);
    List<GuruParamDto> list ();
}
