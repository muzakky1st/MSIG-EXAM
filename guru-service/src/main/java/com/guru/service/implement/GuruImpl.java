package com.guru.service.implement;

import com.base.service.errorhandler.ErrorHandler;
import com.base.service.model.GuruModel;
import com.base.service.repository.GuruRepository;
import com.guru.service.dto.GuruParamDto;
import com.guru.service.service.GuruService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class GuruImpl implements GuruService {
    private final GuruRepository guruRepository;

    @Override
    public HttpStatus add(GuruParamDto request) {
        log.info("check method add Guru...");
        var checkDataGuru = guruRepository.findByNamaGuruAndStatus(request.getNamaGuru(), Boolean.FALSE);
        if (checkDataGuru.isPresent()) {
            throw new ErrorHandler("Data Sudah Ada", HttpStatus.CONFLICT);
        }
        guruRepository.save(GuruModel.builder()
                .namaGuru(request.getNamaGuru())
                .status(request.getStatus())
                .mataPelajaran(request.getMataPelajaran())
                .build());
        return HttpStatus.OK;
    }

    @Override
    public GuruParamDto getDetail(Long id) {
        log.info("check method getDetail Guru");
        var hasilCheck = checkDataGuru(id);
        return GuruParamDto.builder()
                .id(hasilCheck.getId())
                .namaGuru(hasilCheck.getNamaGuru())
                .status(hasilCheck.getStatus())
                .mataPelajaran(hasilCheck.getMataPelajaran())
                .build();
    }

    @Override
    public HttpStatus update(GuruParamDto request) {
        log.info("check method update Guru");
        checkDataGuru(request.getId());
        guruRepository.save(GuruModel.builder()
                .id(request.getId())
                .namaGuru(request.getNamaGuru())
                .status(request.getStatus())
                .mataPelajaran(request.getMataPelajaran())
                .build());
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus delete(Long id) {
        var dataCheck = checkDataGuru(id);
        guruRepository.delete(dataCheck);
        return HttpStatus.OK;
    }

    @Override
    public List<GuruParamDto> list() {
        log.info("check method list Guru");
        List<GuruModel> guruList = guruRepository.findAll();
        return guruList.stream()
                .map(guru -> GuruParamDto.builder()
                        .id(guru.getId())
                        .namaGuru(guru.getNamaGuru())
                        .status(guru.getStatus())
                        .mataPelajaran(guru.getMataPelajaran())
                        .build())
                .toList();
    }

    private GuruModel checkDataGuru(Long id) {
        log.info("check method checkDataGuru");
        return guruRepository.findByIdAndStatus(id, Boolean.FALSE)
                .orElseThrow(() -> new ErrorHandler("Data Tidak Ditemukan", HttpStatus.NOT_FOUND));
    }
}
