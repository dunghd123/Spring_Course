package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "taikhoans")
@Getter
@Setter
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Taikhoanid;
    @Column(name = "tennguoidung")
    @Size(max = 50,message = "ten nguoi dung khong qua 50 ky tu!")
    private String Tennguoidung;
    @Column(name = "tendangnhap")
    @Size(max = 50,message = "ten dang nhap khong qua 50 ky tu!")
    private String Tendangnhap;
    @Column(name = "matkhau")
    @Size(max = 50,message = "mat khau khong qua 50 ky tu!")
    private String Matkhau;

    //khoa ngoai den bang quyenhan
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quyenhanid",foreignKey = @ForeignKey(name = "FK_Taikhoan_Quyenhan"))
    @JsonIgnoreProperties(value = "taiKhoans")
    QuyenHan quyenHan;
    //lien ket den bang baiviet
    @OneToMany(mappedBy = "taiKhoan")
    @JsonIgnoreProperties(value = "taiKhoan")
    Set<BaiViet> baiViets;
    //lien ket den bang Dangkyhoc
    @OneToMany(mappedBy = "taiKhoan")
    @JsonIgnoreProperties(value = "taiKhoan")
    Set<DangKyHoc> dangKyHocs;
}
