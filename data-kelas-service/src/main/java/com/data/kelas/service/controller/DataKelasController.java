package com.data.kelas.service.controller;

import com.data.kelas.service.dto.*;
import com.data.kelas.service.service.DataKelasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dataKelas")
@RequiredArgsConstructor
public class DataKelasController {
    private final DataKelasService dataKelasService;

    @GetMapping("/findByNamaSiswa")
    @ResponseBody
    public DataKelasResponseNamaSiswa findByNamaSiswa(@RequestBody String namaSiswa) {
        return dataKelasService.findByNamaSiswa(namaSiswa);
    }

    @GetMapping("/findByNomorIndukSiswa")
    @ResponseBody
    public DataKelasResponseNIS findByNomorIndukSiswa(@RequestBody String nomorIndukSiswa) {
        return dataKelasService.findByNomorIndukSiswa(nomorIndukSiswa);
    }

    @GetMapping("/findByKelas")
    @ResponseBody
    public DataKelasResponseByKelas findByKelas(@RequestBody String kelas) {
        return dataKelasService.findByKelas(kelas);
    }

    @GetMapping("/findByMataPelajaran")
    @ResponseBody
    public DataKelasResponseByMataPelajaran findByMataPelajaran(@RequestBody String mataPelajaran) {
        return dataKelasService.findByMataPelajaran(mataPelajaran);
    }

    @GetMapping("/findByNamaGuru")
    @ResponseBody
    public DataKelasResponseByNamaGuru findByNamaGuru(@RequestBody String namaGuru) {
        return dataKelasService.findByNamaGuru(namaGuru);
    }
}
