package com.data.kelas.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataKelasResponseByKelas {
    private List<String> namaSiswa; //
    private List<String> namaGuru; //
    private String kelas; //
    private List<String> mataPelajaran;
}
