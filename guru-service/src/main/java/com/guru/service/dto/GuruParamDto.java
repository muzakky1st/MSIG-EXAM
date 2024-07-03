package com.guru.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuruParamDto {
    private Long id;
    private String namaGuru;
    private Boolean status;
    private String mataPelajaran;
}
