package com.mapel.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapelParamDto {
    private Long id;
    private String mataPelajaran;
    private String kelas;
}