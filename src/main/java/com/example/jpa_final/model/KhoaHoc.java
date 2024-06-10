package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "khoahocs")
@Getter
@Setter
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int KhoahocID;
    @Column(name = "tenkhoahoc",nullable = false)
    @Size(max = 50,message = "ten loai khong qua 50 ky tu")
    private String Tenkhoahoc;
    @Column(name = "thoigianhoc",nullable = false)
    private int Thoigianhoc;
    @Column(name = "gioithieu",nullable = false)
    private String Gioithieu;
    @Column(name = "noidung",nullable = false)
    private String Noidung;
    @Column(name = "hocphi",nullable = false)
    private float Hocphi;
    @Column(name = "sohocvien",nullable = false)
    private int Sohocvien;
    @Column(name = "soluongmon",nullable = false)
    private int Soluongmon;
    @Column(name = "hinhanh",nullable = false)
    private String Hinhanh;

    //khoa ngoai den bang loaikhoahoc
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaikhoahocid",foreignKey = @ForeignKey(name = "FK_KhoaHoc_LoaiKhoaHoc"))
    @JsonIgnoreProperties(value = "khoaHocs")
    LoaiKhoaHoc loaiKhoaHoc;

    //lien ket den bang dangkyhoc
    @OneToMany(mappedBy = "khoaHoc")
    @JsonIgnoreProperties(value = "khoaHoc")
    Set<DangKyHoc> dangKyHocs;
}
