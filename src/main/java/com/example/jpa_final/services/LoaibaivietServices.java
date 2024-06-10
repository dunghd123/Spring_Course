package com.example.jpa_final.services;

import com.example.jpa_final.model.ChuDe;
import com.example.jpa_final.model.LoaiBaiViet;
import com.example.jpa_final.model.LoaiKhoaHoc;
import com.example.jpa_final.repo.IChudeRep;
import com.example.jpa_final.repo.ILoaibaivietRep;
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
public class LoaibaivietServices {
    @Autowired
    ILoaibaivietRep loaibvRep;
    @Autowired
    IChudeRep chudeRep;

    public void themLoaiBaiViet(LoaiBaiViet loaiBaiViet){
        ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
        Validator val= valFac.getValidator();
        Set<ConstraintViolation<LoaiBaiViet>> violationSet= val.validate(loaiBaiViet);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            loaibvRep.save(loaiBaiViet);
        }
    }
    public boolean suaLoaiBaiViet(LoaiBaiViet loaiBaiViet){
        boolean check=true;
        Optional<LoaiBaiViet> op=Optional.empty();
        if(loaibvRep.findById(loaiBaiViet.getLoaibaivietID())==op){
            check=false;
        }
        else {
            LoaiBaiViet loaibvCurrent= loaibvRep.findById(loaiBaiViet.getLoaibaivietID()).get();
            loaibvCurrent.setTenloai(loaiBaiViet.getTenloai());
            ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
            Validator val= valFac.getValidator();
            Set<ConstraintViolation<LoaiBaiViet>> violationSet= val.validate(loaibvCurrent);
            violationSet.forEach(x->{
                System.out.println(x.getMessage());
            });
            if(violationSet.isEmpty()){
                loaibvRep.save(loaibvCurrent);
            }
        }
        return check;
    }
    public boolean xoaLoaiBaiViet(int loaibaivietid){
        boolean check=true;
        Optional<LoaiBaiViet> op= Optional.empty();
        if(loaibvRep.findById(loaibaivietid)==op){
            check=false;
        }else {
            for(ChuDe cd: chudeRep.findAll()){
                if(cd.getLoaiBaiViet().getLoaibaivietID()==loaibaivietid){
                    chudeRep.delete(cd);
                }
            }
            loaibvRep.deleteById(loaibaivietid);
        }
        return check;
    }
    public List<LoaiBaiViet> hienThiLBV(int pagenum, int pagesize){
        Pageable page= PageRequest.of(pagenum,pagesize);
        return loaibvRep.findAllBy(page);
    }


}
