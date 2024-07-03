package com.siswa.service.controller;

import com.siswa.service.dto.SiswaParamDto;
import com.siswa.service.service.SiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/siswa")
@RequiredArgsConstructor
public class SiswaController {
    private final SiswaService siswaService;

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus addSiswa(@RequestBody SiswaParamDto request) {
        return siswaService.addSiswa(request);
    }

    @GetMapping("/getDetail")
    @ResponseBody
    public SiswaParamDto getDetail(Long id) {
        return siswaService.getDetail(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public HttpStatus update(@RequestBody SiswaParamDto request) {
        return siswaService.update(request);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(Long id) {
        return siswaService.delete(id);
    }

    @GetMapping("/list")
    public List<SiswaParamDto> list() {
        return siswaService.list();
    }
}
