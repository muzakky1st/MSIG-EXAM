package com.data.kelas.service.service;

import com.data.kelas.service.dto.*;

import java.util.List;

public interface DataKelasService {
    DataKelasResponseNamaSiswa findByNamaSiswa(String namaSiswa);

    DataKelasResponseNIS findByNomorIndukSiswa(String nomorIndukSiswa);

    DataKelasResponseByKelas findByKelas(String kelas);

    DataKelasResponseByMataPelajaran findByMataPelajaran(String mataPelajaran);

    DataKelasResponseByNamaGuru findByNamaGuru(String namaGuru);
}
