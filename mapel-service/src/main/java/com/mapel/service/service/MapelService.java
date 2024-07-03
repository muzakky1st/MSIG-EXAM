package com.mapel.service.service;

import com.mapel.service.dto.MapelParamDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface MapelService {
    HttpStatus addMapel(MapelParamDto request);
    MapelParamDto getDetail(Long id);
    HttpStatus update(MapelParamDto request);
    HttpStatus delete (Long id);
    List<MapelParamDto> list();
}
