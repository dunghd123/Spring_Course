package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.ChuDe;
import com.example.jpa_final.repo.IBaivietRep;
import com.example.jpa_final.repo.IChudeRep;
import com.example.jpa_final.repo.ITaikhoanRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BaivietServices {
    @Autowired
    IBaivietRep baivietRep;
    @Autowired
    IChudeRep chudeRep;
    @Autowired
    ITaikhoanRep taikhoanRep;

    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();

    public void themBaiViet(BaiViet baiViet){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        baiViet.setThoigiantao(date);
        Set<ConstraintViolation<BaiViet>> violationSet= val.validate(baiViet);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            baivietRep.save(baiViet);
        }
    }
    public boolean suaBaiViet(BaiViet baiViet){
        boolean check=true;
        Optional<BaiViet> op= Optional.empty();

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        if(baivietRep.findById(baiViet.getBaivietID())==op){
            check=false;
        }
        else {
            BaiViet baiVietCurrent= baivietRep.findById(baiViet.getBaivietID()).get();
            baiVietCurrent.setTenbaiviet(baiViet.getTenbaiviet());
            baiVietCurrent.setTentacgia(baiViet.getTentacgia());
            baiVietCurrent.setNoidung(baiViet.getNoidung());
            baiVietCurrent.setNoidungngan(baiViet.getNoidungngan());
            baiVietCurrent.setHinhanh(baiViet.getHinhanh());
            baiVietCurrent.setChuDe(baiViet.getChuDe());
            baiVietCurrent.setTaiKhoan(baiViet.getTaiKhoan());
            baiVietCurrent.setThoigiantao(date);
            Set<ConstraintViolation<BaiViet>> violationSet= val.validate(baiVietCurrent);
            violationSet.forEach(x->{
                System.out.println(x.getMessage());
            });
            if(violationSet.isEmpty()){
                baivietRep.save(baiVietCurrent);
                check=true;
            }
        }

        return check;
    }
    public boolean xoaBaiViet(int baivietid){
        boolean check= true;
        Optional<BaiViet> op= Optional.empty();
        if(baivietRep.findById(baivietid)==op){
            check=false;
        }else {
            baivietRep.deleteById(baivietid);
        }
        return check;
    }
    public List<BaiViet> danhSachBaiviet(int pagenum, int pagesize){
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        return baivietRep.findAllBy(pageable);
    }
    public List<BaiViet> timKiemTheoTenBaiViet(String tenbv, int pagenum, int pagesize){
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        return baivietRep.findAllByTenbaivietEquals(tenbv,pageable);
    }
}
