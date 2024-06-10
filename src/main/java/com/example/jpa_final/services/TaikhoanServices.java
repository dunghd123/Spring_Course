package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.DangKyHoc;

import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.model.TaiKhoan;
import com.example.jpa_final.repo.IBaivietRep;
import com.example.jpa_final.repo.IDangkyRep;
import com.example.jpa_final.repo.IKhoahocRep;
import com.example.jpa_final.repo.ITaikhoanRep;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TaikhoanServices {
    @Autowired
    ITaikhoanRep taikhoanRep;
    @Autowired
    IDangkyRep dangkyRep;
    @Autowired
    IBaivietRep baivietRep;
    @Autowired
    IKhoahocRep khoahocRep;
    @Autowired
    KhoahocServices khoahocServices;
    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();
    public static boolean isPasswordValid(String password) {
        // Biểu thức chính quy kiểm tra có ít nhất một chữ số và một ký tự đặc biệt
        String regex = "^(?=.*\\d)(?=.*[!@#$%^&*()-+=<>?{}\\[\\]_./~`]).*$";
        // Tạo Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);
        // Tạo Matcher để so khớp biểu thức chính quy với chuỗi
        Matcher matcher = pattern.matcher(password);
        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }
    public static  boolean isUsernameValid(String username, List<TaiKhoan> lst){
        boolean check= true;
        for(TaiKhoan tk: lst){
            System.out.println(tk);
            if(tk.getTendangnhap().equals(username)){
                check=false;
                break;
            }
        }
        return check;
    }
    public boolean themTaiKhoan(TaiKhoan taiKhoan){
        boolean check=false;
        Set<ConstraintViolation<TaiKhoan>> violationSet= val.validate(taiKhoan);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            if(taikhoanRep.findAll().isEmpty()){
                if(isPasswordValid(taiKhoan.getMatkhau())){
                    taikhoanRep.save(taiKhoan);
                    check=true;
                }
            }else {
                List<TaiKhoan> list= new ArrayList<>(taikhoanRep.findAll());
                if(isUsernameValid(taiKhoan.getTendangnhap(), list) && isPasswordValid(taiKhoan.getMatkhau())){
                    taikhoanRep.save(taiKhoan);
                    check=true;
                }
            }
        }
        return check;
    }
    public boolean suaTaiKhoan(TaiKhoan taiKhoan){
        boolean check=false;
        TaiKhoan tkCurrent= taikhoanRep.findById(taiKhoan.getTaikhoanid()).get();
        tkCurrent.setTennguoidung(taiKhoan.getTennguoidung());
        tkCurrent.setQuyenHan(taiKhoan.getQuyenHan());
        List<TaiKhoan> lst= new ArrayList<>();
        for(TaiKhoan tk: taikhoanRep.findAll()){
            if(!tk.getTendangnhap().equals(taiKhoan.getTendangnhap())){
                lst.add(tk);
            }
        }
        if(isUsernameValid(taiKhoan.getTendangnhap(),lst) && isPasswordValid(taiKhoan.getMatkhau())){
            tkCurrent.setTendangnhap(taiKhoan.getTendangnhap());
            tkCurrent.setMatkhau(taiKhoan.getMatkhau());
            Set<ConstraintViolation<TaiKhoan>> violationSet= val.validate(tkCurrent);
            violationSet.forEach(x->{
                System.out.println(x.getMessage());
            });
            if(violationSet.isEmpty()){
                check=true;
                taikhoanRep.save(tkCurrent);
            }
        }
        return check;
    }
    public boolean xoaTaiKhoan(int taikhoanid){
        boolean check=true;
        Optional<TaiKhoan> op= Optional.empty();
        if(taikhoanRep.findById(taikhoanid)==op){
            check=false;
        }else {
            for(DangKyHoc dk: dangkyRep.findAll()){
                int khid=dk.getKhoaHoc().getKhoahocID();
                KhoaHoc kh= khoahocRep.findById(khid).get();
                if(dk.getTaiKhoan().getTaikhoanid()==taikhoanid){
                    dangkyRep.delete(dk);
                    khoahocServices.suaKhoaHoc(kh);
                }
            }
            for(BaiViet bv: baivietRep.findAll()){
                if(bv.getTaiKhoan().getTaikhoanid()==taikhoanid){
                    baivietRep.delete(bv);
                }
            }
            taikhoanRep.deleteById(taikhoanid);
        }
        return check;
    }
    public List<TaiKhoan> hienDsTaikhoan(int pagenum, int pagesize){
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        return taikhoanRep.findAllBy(pageable);
    }
    public List<TaiKhoan> timkiemtheoTenDangnhap(String tendn,int pagenum){
        Pageable pageable= PageRequest.of(pagenum,1);
        return  taikhoanRep.findAllByTendangnhapEquals(tendn,pageable);
    }
}
