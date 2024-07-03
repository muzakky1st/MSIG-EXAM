package com.base.service.repository;

import com.base.service.model.SiswaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiswaRepository extends JpaRepository<SiswaModel, Long> {
    Optional<SiswaModel> findByIdAndStatus(Long id, Boolean status);
    Optional<SiswaModel> findByNomorIndukSiswa(String nomorIndukSiswa);
    Optional<SiswaModel> findByNamaSiswa (String namaSiswa);
    List<SiswaModel> findByKelas (String kelas);
    SiswaModel findByKelasAndStatus(String kelas, Boolean status);
    List<SiswaModel> findByWaliKelas(String waliKelas);
}
