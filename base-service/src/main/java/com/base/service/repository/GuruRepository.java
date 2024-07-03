package com.base.service.repository;

import com.base.service.model.GuruModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuruRepository extends JpaRepository<GuruModel, Long> {
    Optional<GuruModel> findByNamaGuruAndStatus(String namaGuru, Boolean status);
    Optional<GuruModel> findByIdAndStatus(Long id, Boolean status);
    List<GuruModel> findByMataPelajaran (String mataPelajaran);
    List<GuruModel> findByNamaGuru(String namaGuru);
}
