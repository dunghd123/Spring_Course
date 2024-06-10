package com.example.jpa_final.services;

import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.model.LoaiKhoaHoc;
import com.example.jpa_final.repo.IKhoahocRep;
import com.example.jpa_final.repo.ILoaikhoahocRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class LoaiKhoahocServices {
    @Autowired
    ILoaikhoahocRep lkhoaRep;
    @Autowired
    IKhoahocRep khoaRep;

    public void themLoaikhoahoc(LoaiKhoaHoc loaiKhoaHoc){
        ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
        Validator val= valFac.getValidator();
        Set<ConstraintViolation<LoaiKhoaHoc>> violationSet= val.validate(loaiKhoaHoc);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            lkhoaRep.save(loaiKhoaHoc);
        }
    }
    public void suaLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc){
        LoaiKhoaHoc loaikhCurrent= lkhoaRep.findById(loaiKhoaHoc.getLoaikhoahocID()).get();
        loaikhCurrent.setTenloai(loaiKhoaHoc.getTenloai());
        //check constraint
        ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
        Validator val= valFac.getValidator();
        Set<ConstraintViolation<LoaiKhoaHoc>> violationSet= val.validate(loaikhCurrent);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            lkhoaRep.save(loaikhCurrent);
        }
    }
    public boolean xoaLoaiKhoaHoc(int loaikhid){
        boolean check=true;
        Optional<LoaiKhoaHoc> op= Optional.empty();
        if(lkhoaRep.findById(loaikhid)==op){
            check=false;
        }else {
            for(KhoaHoc kh: khoaRep.findAll()){
                if(kh.getLoaiKhoaHoc().getLoaikhoahocID()==loaikhid){
                    khoaRep.delete(kh);
                }
            }
            lkhoaRep.deleteById(loaikhid);
        }
        return check;
    }
}
