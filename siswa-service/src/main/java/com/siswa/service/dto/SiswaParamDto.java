package com.siswa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiswaParamDto {
    private Long id;
    private String nomorIndukSiswa;
    private String namaSiswa;
    private Boolean status;
    private String kelas;
    private String waliKelas;
}
