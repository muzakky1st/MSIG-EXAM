package com.data.kelas.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataKelasResponseNamaSiswa {
    private String namaSiswa; //
    private String namaGuru; //
    private String kelas; //
    private List<String> mataPelajaran;
}
