package com.data.kelas.service.implement;

import com.base.service.errorhandler.ErrorHandler;
import com.base.service.model.GuruModel;
import com.base.service.model.MapelModel;
import com.base.service.model.SiswaModel;
import com.base.service.repository.GuruRepository;
import com.base.service.repository.MapelRepository;
import com.base.service.repository.SiswaRepository;
import com.data.kelas.service.dto.*;
import com.data.kelas.service.service.DataKelasService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataKelasImplement implements DataKelasService {
    private final SiswaRepository siswaRepository;
    private final GuruRepository guruRepository;
    private final MapelRepository mapelRepository;

    @Override
    public DataKelasResponseNamaSiswa findByNamaSiswa(String namaSiswa) {
        log.info("check method findByNamaSiswa DataKelas");
        var namaSiswaTmp = convertJsonDataToString(namaSiswa, "findByNamaSiswa");
        var checkDataSiswa = siswaRepository.findByNamaSiswa(namaSiswaTmp)
                .orElseThrow(() -> new ErrorHandler("Data Siswa Tidak Ditemukan", HttpStatus.NOT_FOUND));
        var checkDataGuru = findByDataGuru(checkDataSiswa.getWaliKelas());
        var checkMapel = findDataMapel(checkDataSiswa.getKelas());

        return DataKelasResponseNamaSiswa.builder()
                .namaSiswa(checkDataSiswa.getNamaSiswa())
                .kelas(checkDataSiswa.getKelas())
                .namaGuru(checkDataGuru.isPresent() ? checkDataGuru.get().getNamaGuru() : "Belum Ada Wali Kelas")
                .mataPelajaran(checkMapel.stream().map(MapelModel::getMataPelajaran).collect(Collectors.toList()))
                .build();
    }

    @Override
    public DataKelasResponseNIS findByNomorIndukSiswa(String nomorIndukSiswa) {
        log.info("check method findByNomorIndukSiswa DataKelas");
        var nisTmp = convertJsonDataToString(nomorIndukSiswa, "findByNomorIndukSiswa");
        var checkDataSiswa = siswaRepository.findByNomorIndukSiswa(nisTmp)
                .orElseThrow(() -> new ErrorHandler("Data NIS Tidak Ditemukan", HttpStatus.NOT_FOUND));

        var checkDataGuru = findByDataGuru(checkDataSiswa.getWaliKelas());
        var checkMapel = findDataMapel(checkDataSiswa.getKelas());
        return DataKelasResponseNIS.builder()
                .namaSiswa(checkDataSiswa.getNamaSiswa())
                .kelas(checkDataSiswa.getKelas())
                .namaGuru(checkDataGuru.isPresent() ? checkDataGuru.get().getNamaGuru() : "Belum Ada Wali Kelas")
                .mataPelajaran(checkMapel.stream().map(MapelModel::getMataPelajaran).collect(Collectors.toList()))
                .build();
    }

    public DataKelasResponseByKelas findByKelas(String kelas) {
        log.info("check method findByKelas DataKelas");
        var kelasTmp = convertJsonDataToString(kelas, "findByKelas");

        var checkDataSiswa = siswaRepository.findByKelas(kelasTmp);
        if (checkDataSiswa == null || checkDataSiswa.size() == 0) {
            throw new ErrorHandler("Data Kelas Tidak Ditemukan", HttpStatus.NOT_FOUND);
        }
        var dataMapel = mapelRepository.findByKelas(kelasTmp);
        DataKelasResponseByKelas dataSiswa = new DataKelasResponseByKelas();
        checkDataSiswa.forEach(siswa -> {
            dataSiswa.setKelas(siswa.getKelas());
            dataSiswa.setNamaSiswa(checkDataSiswa.stream().map(SiswaModel::getNamaSiswa).toList());
            dataSiswa.setNamaGuru(checkDataSiswa.stream().map(SiswaModel::getWaliKelas).toList().stream().distinct().toList());
            dataSiswa.setMataPelajaran(dataMapel.stream().map(MapelModel::getMataPelajaran).toList().stream().distinct().toList());
        });
        return dataSiswa;
    }

    @Override
    public DataKelasResponseByMataPelajaran findByMataPelajaran(String mataPelajaran) {
        log.info("check method findByMataPelajaran DataKelas");
        var mapelTmp = convertJsonDataToString(mataPelajaran, "findByMataPelajaran");
        var checkDataMapel = mapelRepository.findByMataPelajaran(mapelTmp)
                .orElseThrow(() -> new ErrorHandler("Data Mata Pelajaran Tidak Ditemukan", HttpStatus.NOT_FOUND));
        var dataSiswa = findDataSiswa(checkDataMapel.getKelas());
        var dataGuru = guruRepository.findByNamaGuruAndStatus(dataSiswa.get(0).getWaliKelas(),Boolean.FALSE);

        return DataKelasResponseByMataPelajaran.builder()
                .kelas(checkDataMapel.getKelas())
                .namaGuru(dataGuru.get().getNamaGuru())
                .namaSiswa(dataSiswa.stream().map(SiswaModel::getNamaSiswa).collect(Collectors.toList()))
                .mataPelajaran(checkDataMapel.getMataPelajaran())
                .build();
    }

    @Override
    public DataKelasResponseByNamaGuru findByNamaGuru(String namaGuru) {
        log.info("check method findByNamaGuru DataKelas");
        var namaGuruTmp = convertJsonDataToString(namaGuru,"findByNamaGuru");
        var checkDataGuru = guruRepository.findByNamaGuruAndStatus(namaGuruTmp, Boolean.FALSE)
                .orElseThrow(()-> new ErrorHandler("Data Guru Tidak Ditemukan", HttpStatus.NOT_FOUND));
        var dataSiswa = siswaRepository.findByWaliKelas(checkDataGuru.getNamaGuru());
        var dataMapel = mapelRepository.findByMataPelajaran(checkDataGuru.getMataPelajaran());
        return DataKelasResponseByNamaGuru.builder()
                .namaSiswa(dataSiswa.stream().map(SiswaModel::getNamaSiswa).collect(Collectors.toList()))
                .namaGuru(checkDataGuru.getNamaGuru())
                .kelas(dataMapel.stream().map(MapelModel::getKelas).collect(Collectors.toList()))
                .mataPelajaran(dataMapel.get().getMataPelajaran())
                .build();
    }

    private Optional<GuruModel> findByDataGuru(String waliKelas) {
        log.info("check method findDataGuru");
        return Optional.ofNullable(guruRepository.findByNamaGuruAndStatus(waliKelas, Boolean.FALSE)
                .orElseThrow(() -> new ErrorHandler("Data Guru Tidak Ditemukan", HttpStatus.NOT_FOUND)));
    }

    private List<MapelModel> findDataMapel(String kelas) {
        log.info("check method findDataMapel");
        return mapelRepository.findByKelas(kelas);
    }

    private List<SiswaModel> findDataSiswa(String kelas) {
        log.info("check method findDataKelas");
        return siswaRepository.findByKelas(kelas);
    }
    private String convertJsonDataToString (String jsonString, String methodName){
        log.info("check method convertJsonDataToString");
        ObjectMapper objectMapper = new ObjectMapper();
        String returnString = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (methodName.equals("findByNamaSiswa")){
                returnString = jsonNode.get("namaSiswa").asText();
            } else if (methodName.equals("findByNomorIndukSiswa")) {
                returnString = jsonNode.get("nomorIndukSiswa").asText();
            } else if (methodName.equals("findByKelas")){
                returnString = jsonNode.get("kelas").asText();
            } else if (methodName.equals("findByMataPelajaran")) {
                returnString = jsonNode.get("mataPelajaran").asText();
            }else{
                returnString = jsonNode.get("namaGuru").asText();
            }
        }catch (JsonProcessingException e){
            return String.valueOf(e.getStackTrace());
        }
        return returnString;
    }
}
