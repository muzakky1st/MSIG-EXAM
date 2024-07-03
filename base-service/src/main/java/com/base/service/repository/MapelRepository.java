package com.base.service.repository;

import com.base.service.model.MapelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapelRepository extends JpaRepository<MapelModel, Long> {
    Optional<MapelModel> findByMataPelajaran(String mataPelajaran);
    List<MapelModel> findByKelas (String kelas);
}
