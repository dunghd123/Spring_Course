package com.example.jpa_final.repo;

import com.example.jpa_final.model.TaiKhoan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaikhoanRep extends JpaRepository<TaiKhoan,Integer> {
    @Query(value = "select * from taikhoans",nativeQuery = true)
    public List<TaiKhoan> findAllBy(Pageable pageable);
    @Query(value = "select * from taikhoans tk where tk.tendangnhap like ?1",nativeQuery = true)
    public List<TaiKhoan> findAllByTendangnhapEquals(String tendn,Pageable pageable);
}
