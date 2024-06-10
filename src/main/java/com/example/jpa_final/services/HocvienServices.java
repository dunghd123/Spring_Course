package com.example.jpa_final.services;

import com.example.jpa_final.model.*;
import com.example.jpa_final.repo.IDangkyRep;
import com.example.jpa_final.repo.IHocvienRep;
import com.example.jpa_final.repo.IKhoahocRep;
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
public class HocvienServices {
    @Autowired
    IHocvienRep hocvienRep;
    @Autowired
    IDangkyRep dangkyRep;
    @Autowired
    IKhoahocRep khoahocRep;
    @Autowired
    KhoahocServices khoahocServices;

    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();
    public boolean isEmailValid(String email){
        boolean check= false;
        if(email.contains("@gmail.com")){
            check=true;
        }
        return check;
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Biểu thức chính quy kiểm tra định dạng số điện thoại
        String regex = "^0[0-9]{9}$";

        // Tạo Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher từ Pattern và chuỗi số điện thoại đầu vào
        Matcher matcher = pattern.matcher(phoneNumber);

        // Kiểm tra xem số điện thoại có đúng định dạng hay không
        return matcher.matches();
    }
    public boolean checkEmail(String email, List<HocVien> lst){
        boolean check= true;
        for(HocVien hv: lst){
            if(hv.getEmail().equals(email)){
                check=false;
                break;
            }
        }
        return check;
    }
    public boolean checkSDT(String phoneNumber,List<HocVien> lst){
        boolean check= true;
        for(HocVien hv: lst){
            if(hv.getSodienthoai().equals(phoneNumber)){
                check=false;
                break;
            }
        }
        return check;
    }
    public static String formatName(String name) {
        // Chia chuỗi thành các từ, tách bởi khoảng trắng hoặc dấu gạch ngang
        String[] words = name.split("[\\s-]");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                // Chuyển đổi chữ cái đầu của từ thành chữ hoa và thêm vào chuỗi kết quả
                formattedName.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        // Xóa dấu cách cuối cùng
        if (formattedName.length() > 0) {
            formattedName.setLength(formattedName.length() - 1);
        }
        return formattedName.toString();
    }
    public boolean themHocVien(HocVien hocVien){
        boolean check=false;
        hocVien.setHoten(formatName(hocVien.getHoten()));
        Set<ConstraintViolation<HocVien>> violationSet= val.validate(hocVien);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            if(hocvienRep.findAll().isEmpty()){
                if(isEmailValid(hocVien.getEmail()) && isValidPhoneNumber(hocVien.getSodienthoai())){
                    check=true;
                    hocvienRep.save(hocVien);
                }
            }else {
                List<HocVien> lst= new ArrayList<>(hocvienRep.findAll());
                if(isEmailValid(hocVien.getEmail()) && isValidPhoneNumber(hocVien.getSodienthoai())){
                    if(checkEmail(hocVien.getEmail(),lst) && checkSDT(hocVien.getSodienthoai(),lst)){
                        check=true;
                        hocvienRep.save(hocVien);
                    }
                }
            }
        }
        System.out.println(check);
        return check;
    }

    public boolean suaHocVien(HocVien hocVien){
        boolean check= false;
        Optional<HocVien> op= Optional.empty();
        if(hocvienRep.findById(hocVien.getHocvienID())==op){
            check=false;
        }else {
            HocVien hvCurrent= hocvienRep.findById(hocVien.getHocvienID()).get();
            hvCurrent.setHoten(formatName(hocVien.getHoten()));
            hvCurrent.setHinhanh(hocVien.getHinhanh());
            hvCurrent.setNgaysinh(hocVien.getNgaysinh());
            hvCurrent.setEmail(hocVien.getEmail());
            hvCurrent.setSodienthoai(hocVien.getSodienthoai());
            hvCurrent.setTinhthanh(hocVien.getTinhthanh());
            hvCurrent.setQuanhuyen(hocVien.getQuanhuyen());
            hvCurrent.setPhuongxa(hocVien.getPhuongxa());
            hvCurrent.setSonha(hocVien.getSonha());
            //lay danh sach khong chua id truyen vao
            List<HocVien> list= new ArrayList<>();
            for(HocVien hv: hocvienRep.findAll()){
                if(hv.getHocvienID()!= hocVien.getHocvienID()){
                    list.add(hv);
                }
            }
            Set<ConstraintViolation<HocVien>> violationSet= val.validate(hocVien);
            violationSet.forEach(x->{
                System.out.println(x.getMessage());
            });
            if(violationSet.isEmpty()){
                if(isValidPhoneNumber(hvCurrent.getSodienthoai()) && isEmailValid(hvCurrent.getEmail())){
                    if(checkSDT(hvCurrent.getSodienthoai(),list) && checkEmail(hvCurrent.getEmail(),list)){
                        check=true;
                        hocvienRep.save(hvCurrent);
                    }
                }
            }
        }
      return  check;
    }

    public boolean xoaHocVien(int hocvienid){
        boolean check= true;
        Optional<HocVien> op= Optional.empty();
        if(hocvienRep.findById(hocvienid)==op){
            check=false;
        }else {
            for(DangKyHoc dk: dangkyRep.findAll()){
                int khid=dk.getKhoaHoc().getKhoahocID();
                KhoaHoc kh= khoahocRep.findById(khid).get();
                if(dk.getHocVien().getHocvienID()==hocvienid){
                    dangkyRep.delete(dk);
                    khoahocServices.suaKhoaHoc(kh);
                }
            }
            hocvienRep.deleteById(hocvienid);
        }
        return check;
    }
    public List<HocVien> hienThiDS(int pagenum,int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        return hocvienRep.findAllBy(pageable);
    }
    public List<HocVien> timKiemtheoTen(String ten, int pagenum,int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        return hocvienRep.findAllByHotenIsContainingIgnoreCase(ten,pageable);
    }
    public HocVien timKiemTheoEmail(String email){
        return hocvienRep.findByEmailEquals(email);
    }
}
