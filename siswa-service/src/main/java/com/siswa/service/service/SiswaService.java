package com.siswa.service.service;

import com.siswa.service.dto.SiswaParamDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface SiswaService {
    HttpStatus addSiswa(SiswaParamDto request);

    SiswaParamDto getDetail(Long id);

    HttpStatus update(SiswaParamDto request);

    HttpStatus delete(Long id);

    List<SiswaParamDto> list();
}
