package com.example.jpa_final.repo;

import com.example.jpa_final.model.BaiViet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBaivietRep extends JpaRepository<BaiViet,Integer> {
    @Query(value = "select * from baiviets",nativeQuery = true)
    public List<BaiViet> findAllBy(Pageable pageable);
    @Query(value = "select * from baiviets bv where bv.tenbaiviet like ?1",nativeQuery = true)
    public List<BaiViet> findAllByTenbaivietEquals(String tenbv,Pageable pageable);
}
