package com.mapel.service.controller;

import com.mapel.service.dto.MapelParamDto;
import com.mapel.service.service.MapelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mataPelajaran")
@RequiredArgsConstructor
public class MapelController {
    private final MapelService mapelService;

    @PostMapping("/add")
    @ResponseBody
    public HttpStatus addMapel(@RequestBody MapelParamDto request) {
        return mapelService.addMapel(request);
    }
    @GetMapping("/getDetail")
    @ResponseBody
    public MapelParamDto getDetail(Long id){
        return mapelService.getDetail(id);
    }
    @PutMapping("/update")
    @ResponseBody
    public HttpStatus update (@RequestBody MapelParamDto request){
        return mapelService.update(request);
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete (Long id){
        return mapelService.delete(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<MapelParamDto> list(){
        return mapelService.list();
    }
}
