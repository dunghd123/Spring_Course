package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.DangKyHoc;
import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.model.LoaiKhoaHoc;
import com.example.jpa_final.repo.IDangkyRep;
import com.example.jpa_final.repo.IKhoahocRep;
import com.example.jpa_final.repo.ILoaikhoahocRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KhoahocServices {
    @Autowired
    IKhoahocRep khoahocRep;
    @Autowired
    IDangkyRep dangkyRep;
    @Autowired
    ILoaikhoahocRep loaikhoahocRep;
    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();

    public static int demHocVien(int khoahocid,List<DangKyHoc> list){
        int dem=0;
        List<Integer> listhvid= new ArrayList<>();
        for(DangKyHoc dk: list){
            if(list.size()==1 && dk.getKhoaHoc().getKhoahocID()==khoahocid &&
                (dk.getTinhTrangHoc().getTinhtranghocID()==2
                || dk.getTinhTrangHoc().getTinhtranghocID()==3
                || dk.getTinhTrangHoc().getTinhtranghocID()==4)){
                dem++;
                listhvid.add(dk.getHocVien().getHocvienID());
            }
            if(dk.getKhoaHoc().getKhoahocID()==khoahocid && !listhvid.contains(dk.getHocVien().getHocvienID())
                && (dk.getTinhTrangHoc().getTinhtranghocID()==2
                || dk.getTinhTrangHoc().getTinhtranghocID()==3
                || dk.getTinhTrangHoc().getTinhtranghocID()==4)){
                dem++;
                listhvid.add(dk.getHocVien().getHocvienID());
            }
        }
        return dem;
    }

    public boolean themKhoahoc(KhoaHoc khoaHoc){
        boolean check=true;
        khoaHoc.setSohocvien(0);
        Set<ConstraintViolation<KhoaHoc>> violationSet= val.validate(khoaHoc);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            int lkhid= khoaHoc.getLoaiKhoaHoc().getLoaikhoahocID();
            Optional<LoaiKhoaHoc> op= Optional.empty();
            if(loaikhoahocRep.findById(lkhid)==op){
                check=false;
            }else {
                khoahocRep.save(khoaHoc);
            }
        }
        return check;
    }

    public boolean suaKhoaHoc(KhoaHoc khoaHoc){
        boolean check=true;
        KhoaHoc khCurrent= khoahocRep.findById(khoaHoc.getKhoahocID()).get();
        khCurrent.setTenkhoahoc(khoaHoc.getTenkhoahoc());
        khCurrent.setThoigianhoc(khoaHoc.getThoigianhoc());
        khCurrent.setGioithieu(khoaHoc.getGioithieu());
        khCurrent.setNoidung(khoaHoc.getNoidung());
        khCurrent.setHocphi(khoaHoc.getHocphi());
        khCurrent.setSohocvien(demHocVien(khoaHoc.getKhoahocID(), dangkyRep.findAll()));
        khCurrent.setSoluongmon(khoaHoc.getSoluongmon());
        khCurrent.setLoaiKhoaHoc(khoaHoc.getLoaiKhoaHoc());
        Set<ConstraintViolation<KhoaHoc>> violationSet= val.validate(khoaHoc);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            int lkhid= khCurrent.getLoaiKhoaHoc().getLoaikhoahocID();
            Optional<LoaiKhoaHoc> op= Optional.empty();
            if(loaikhoahocRep.findById(lkhid)==op){
                check=false;
            }else {
                khoahocRep.save(khCurrent);
            }
        }
        return check;
    }
    public boolean xoaKhoaHoc(int khoahocid){
        boolean check= true;
        Optional<KhoaHoc> op= Optional.empty();
        if(khoahocRep.findById(khoahocid)==op){
            check=false;
        }else {
            for(DangKyHoc dk: dangkyRep.findAll()){
                if(dk.getKhoaHoc().getKhoahocID()==khoahocid){
                    dangkyRep.delete(dk);
                }
            }
            khoahocRep.deleteById(khoahocid);
        }
        return check;
    }
    public List<KhoaHoc> hienthiDS(int pagenum,int pagesize){
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        return khoahocRep.findAllBy(pageable);
    }
    public List<KhoaHoc> timkiemtheoTenKH(String ten,int pagenum, int pagesize){
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        return khoahocRep.findAllByTenkhoahocEquals(ten,pageable);
    }
}
