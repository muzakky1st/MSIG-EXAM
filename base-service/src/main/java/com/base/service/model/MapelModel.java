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
@Entity
@Builder
@Table(name = "MATA_PELAJARAN")
public class MapelModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 5193518430556250130L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "mata_pelajaran")
    private String mataPelajaran;

    @Column(name = "kelas")
    private String kelas;
}
