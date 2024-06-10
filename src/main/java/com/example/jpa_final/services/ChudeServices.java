package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.ChuDe;
import com.example.jpa_final.model.LoaiBaiViet;
import com.example.jpa_final.repo.IBaivietRep;
import com.example.jpa_final.repo.IChudeRep;
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
public class ChudeServices {
    @Autowired
    IChudeRep chudeRep;
    @Autowired
    IBaivietRep baivietRep;
    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();

    public void themChude(ChuDe chuDe){
        Set<ConstraintViolation<ChuDe>> violationSet= val.validate(chuDe);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            chudeRep.save(chuDe);
        }
    }
    public void suaChude(ChuDe chuDe){
        ChuDe chudeCurrent= chudeRep.findById(chuDe.getChudeID()).get();
        chudeCurrent.setTenchude(chuDe.getTenchude());
        chudeCurrent.setNoidung(chuDe.getNoidung());
        chudeCurrent.setLoaiBaiViet(chuDe.getLoaiBaiViet());

        Set<ConstraintViolation<ChuDe>> violationSet= val.validate(chudeCurrent);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            chudeRep.save(chudeCurrent);
        }
    }
    public boolean xoaChude(int chudeid){
        boolean check=true;
        Optional<ChuDe> op= Optional.empty();
        if(chudeRep.findById(chudeid)==op){
            check=false;
        }else {
            for(BaiViet bv: baivietRep.findAll()){
                if(bv.getChuDe().getChudeID()==chudeid){
                    baivietRep.delete(bv);
                }
            }
            chudeRep.deleteById(chudeid);
        }
        return check;
    }
    public List<ChuDe> hienthiChuDe(int pagenum, int pagesize){
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        return chudeRep.findAllBy(pageable);
    }
}
