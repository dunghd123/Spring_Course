package com.example.jpa_final.repo;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.KhoaHoc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKhoahocRep extends JpaRepository<KhoaHoc,Integer> {
    @Query(value = "select * from khoahocs",nativeQuery = true)
    public List<KhoaHoc> findAllBy(Pageable pageable);
    @Query(value = "select * from khoahocs kh where kh.tenkhoahoc like ?1",nativeQuery = true)
    public List<KhoaHoc> findAllByTenkhoahocEquals(String tenkh,Pageable pageable);
}
