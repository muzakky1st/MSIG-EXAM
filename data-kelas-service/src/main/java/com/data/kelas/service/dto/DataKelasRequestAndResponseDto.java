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
public class DataKelasRequestAndResponseDto {
    private String namaSiswa; //
    private List<String> namaGuru; //
    private List<String> kelas; //
    private List<String> mataPelajaran;
}
