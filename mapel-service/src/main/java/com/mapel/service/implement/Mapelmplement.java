package com.mapel.service.implement;

import com.base.service.errorhandler.ErrorHandler;
import com.base.service.model.MapelModel;
import com.base.service.repository.MapelRepository;
import com.mapel.service.dto.MapelParamDto;
import com.mapel.service.service.MapelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Mapelmplement implements MapelService {
    private final MapelRepository mapelRepository;

    @Override
    public HttpStatus addMapel(MapelParamDto request) {
        log.info("check method addMapel");
        var checkDataMapel = mapelRepository.findByMataPelajaran(request.getMataPelajaran());
        if (checkDataMapel.isPresent()) {
            throw new ErrorHandler("Data Sudah Ada", HttpStatus.CONFLICT);
        }
        mapelRepository.save(MapelModel.builder()
                .kelas(request.getKelas())
                .mataPelajaran(request.getMataPelajaran())
                .build());
        return HttpStatus.OK;
    }

    @Override
    public MapelParamDto getDetail(Long id) {
        log.info("check method getDetail");
        var finalResult = mapelRepository.findById(id);
        if (finalResult.isEmpty()) {
            throw new ErrorHandler("Data Tidak Ditemukan", HttpStatus.NOT_FOUND);
        }
        return MapelParamDto.builder()
                .id(finalResult.get().getId())
                .kelas(finalResult.get().getKelas())
                .mataPelajaran(finalResult.get().getMataPelajaran())
                .build();
    }

    @Override
    public HttpStatus update(MapelParamDto request) {
        log.info("check method update");
        var checkDataMapel = mapelRepository.findById(request.getId())
                .orElseThrow(() -> new ErrorHandler("Data Tidak Ditemukan", HttpStatus.NOT_FOUND));
        mapelRepository.save(MapelModel.builder()
                .id(request.getId())
                .kelas(request.getKelas())
                .mataPelajaran(request.getMataPelajaran())
                .build());
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus delete(Long id) {
        log.info("check method delete");
        var finalResult = mapelRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler("Data Tidak Ditemukan", HttpStatus.NOT_FOUND));
        mapelRepository.delete(finalResult);
        return HttpStatus.OK;
    }

    @Override
    public List<MapelParamDto> list() {
        log.info("check method list");
        List<MapelModel> mapelList = mapelRepository.findAll();
        return mapelList.stream()
                .map(mapel -> MapelParamDto.builder()
                        .id(mapel.getId())
                        .kelas(mapel.getKelas())
                        .mataPelajaran(mapel.getMataPelajaran())
                        .build())
                .toList();
    }
}
