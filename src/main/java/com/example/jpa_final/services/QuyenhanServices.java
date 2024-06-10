package com.example.jpa_final.services;


import com.example.jpa_final.model.QuyenHan;
import com.example.jpa_final.model.TaiKhoan;
import com.example.jpa_final.repo.IQuyenhanRep;
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
public class QuyenhanServices {
    @Autowired
    IQuyenhanRep quyenhanRep;
    @Autowired
    ITaikhoanRep taikhoanRep;

    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();
    public void themQuyenHan(QuyenHan quyenHan){
        Set<ConstraintViolation<QuyenHan>> violationSet= val.validate(quyenHan);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            quyenhanRep.save(quyenHan);
        }
    }
    public boolean suaQuyenHan(QuyenHan quyenHan){
        boolean check= false;
        QuyenHan qhCurrent= quyenhanRep.findById(quyenHan.getQuyenhanID()).get();
        qhCurrent.setTenquyenhan(quyenHan.getTenquyenhan());
        Set<ConstraintViolation<QuyenHan>> violationSet= val.validate(qhCurrent);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            check=true;
            quyenhanRep.save(qhCurrent);
        }
        return check;
    }
    public boolean xoaQuyenHan(int quyenid){
        boolean check= true;
        Optional<QuyenHan> op= Optional.empty();
        if(quyenhanRep.findById(quyenid)==op){
            check=false;
        }else {
            for(TaiKhoan tk: taikhoanRep.findAll()){
                if(tk.getQuyenHan().getQuyenhanID()==quyenid){
                    taikhoanRep.delete(tk);
                }
            }
            quyenhanRep.deleteById(quyenid);
        }
        return check;
    }
    public List<QuyenHan> hienthiQuyen(int pagenum){
        Pageable pageable= PageRequest.of(pagenum,1);
        return  quyenhanRep.findAllBy(pageable);
    }
}
