package com.example.guizhou.repository;

import com.example.guizhou.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    // 按热度（浏览次数）降序查询前N个
    List<Attraction> findTop5ByOrderByViewCountDesc();

    // 按地区查询
    List<Attraction> findByLocationContaining(String location);

    // 随机查询 N 条（使用 PostgreSQL 的 RANDOM()）
    @Query(value = "SELECT * FROM attraction ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Attraction> findRandomAttractions(@Param("limit") int limit);
}