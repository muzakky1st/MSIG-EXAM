package com.base.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TABLE_GURU")
public class GuruModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 5192796830556250130L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "nama_guru")
    private String namaGuru;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "mata_pelajaran")
    private String mataPelajaran;
}
