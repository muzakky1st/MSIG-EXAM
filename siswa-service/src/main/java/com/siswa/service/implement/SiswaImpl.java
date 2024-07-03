package com.siswa.service.implement;

import com.base.service.errorhandler.ErrorHandler;
import com.base.service.model.SiswaModel;
import com.base.service.repository.SiswaRepository;
import com.siswa.service.dto.SiswaParamDto;
import com.siswa.service.service.SiswaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiswaImpl implements SiswaService {
    private final SiswaRepository siswaRepository;

    @Override
    public HttpStatus addSiswa(SiswaParamDto request) {
        log.info("check method addSiswa");
        var checkDataSiswa = siswaRepository.findByNomorIndukSiswa(request.getNomorIndukSiswa());
        if (checkDataSiswa.isPresent()) {
            throw new ErrorHandler("Data Sudah Ada", HttpStatus.CONFLICT);
        }

        siswaRepository.save(SiswaModel.builder()
                .nomorIndukSiswa(request.getNomorIndukSiswa())
                .status(request.getStatus())
                .namaSiswa(request.getNamaSiswa())
                .kelas(request.getKelas())
                .waliKelas(request.getWaliKelas())
                .build());
        return null;
    }

    @Override
    public SiswaParamDto getDetail(Long id) {
        log.info("check method get Detail Siswa");
        var finalResult = checkDataSiswa(id);
        return SiswaParamDto.builder()
                .id(finalResult.getId())
                .nomorIndukSiswa(finalResult.getNomorIndukSiswa())
                .namaSiswa(finalResult.getNamaSiswa())
                .status(finalResult.getStatus())
                .kelas(finalResult.getKelas())
                .waliKelas(finalResult.getWaliKelas())
                .build();
    }

    @Override
    public HttpStatus update(SiswaParamDto request) {
        log.info("check method update Siswa");
        var resultCheckData = checkDataSiswa(request.getId());
        siswaRepository.save(SiswaModel.builder()
                .id(resultCheckData.getId())
                .nomorIndukSiswa(request.getNomorIndukSiswa())
                .namaSiswa(request.getNamaSiswa())
                .status(request.getStatus())
                .kelas(request.getKelas())
                .waliKelas(request.getWaliKelas())
                .build());
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus delete(Long id) {
        log.info("check method delete Siswa");
        var finalResult = checkDataSiswa(id);
        siswaRepository.delete(finalResult);
        return HttpStatus.OK;
    }

    @Override
    public List<SiswaParamDto> list() {
        log.info("check method list");
        List<SiswaModel> siswaList = siswaRepository.findAll();
        return siswaList.stream()
                .map(siswa -> SiswaParamDto.builder()
                        .id(siswa.getId())
                        .kelas(siswa.getKelas())
                        .namaSiswa(siswa.getNamaSiswa())
                        .nomorIndukSiswa(siswa.getNomorIndukSiswa())
                        .status(siswa.getStatus())
                        .waliKelas(siswa.getWaliKelas())
                        .build())
                .toList();
    }

    private SiswaModel checkDataSiswa(Long id) {
        return siswaRepository.findByIdAndStatus(id, Boolean.FALSE)
                .orElseThrow(() -> new ErrorHandler("Data tidak ditemukan", HttpStatus.NOT_FOUND));
    }
}
