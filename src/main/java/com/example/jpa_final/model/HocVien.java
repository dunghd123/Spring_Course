package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "hocviens")
@Getter
@Setter
public class HocVien {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int HocvienID;
    @Column(name = "hinhanh")
    private String Hinhanh;
    @Column(name = "hoten")
    @Size(max = 50,message = "ho ten khong qua 50 ky tu!")
    private String Hoten;
    @Column(name = "ngaysinh")
    private Date Ngaysinh;
    @Column(name = "sodienthoai")
    @Size(max = 11,message = "so dien thoai khong qua 11 so!")
    private String Sodienthoai;
    @Column(name = "email")
    @Size(max = 40,message = "email khong qua 40 ky tu!")
    private String Email;
    @Column(name = "tinhthanh")
    @Size(max = 50,message = "tinh thanh khong qua 50 ky tu!")
    private String Tinhthanh;
    @Column(name = "quanhuyen")
    @Size(max = 50,message = "quan huyen khong qua 50 ky tu!")
    private String Quanhuyen;
    @Column(name = "phuongxa")
    @Size(max = 50,message = "phuong xa khong qua 50 ky tu!")
    private String Phuongxa;
    @Column(name = "sonha")
    @Size(max = 50,message = "so nha khong qua 50 ky tu!")
    private String Sonha;

    //lien ket den bang dangkyhoc
    @OneToMany(mappedBy = "hocVien")
    @JsonIgnoreProperties(value = "hocVien")
    Set<DangKyHoc> dangKyHocs;
}
