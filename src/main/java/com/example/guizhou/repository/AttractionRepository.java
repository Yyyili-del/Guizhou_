package com.example.guizhou.repository;

import com.example.guizhou.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findTop10ByOrderByViewCountDesc();

    List<Attraction> findTop10ByOrderByRatingDesc();

    List<Attraction> findByLocationContaining(String location);

    List<Attraction> findByCategory(String category);

    List<Attraction> findByNameContaining(String keyword);

    @Query(value = "SELECT * FROM attraction ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Attraction> findRandomAttractions(@Param("limit") int limit);

    @Query("SELECT a FROM Attraction a WHERE a.rating >= :minRating ORDER BY a.viewCount DESC")
    List<Attraction> findByRatingGreaterThanEqual(@Param("minRating") Double minRating);

    @Query("SELECT DISTINCT a.category FROM Attraction a")
    List<String> findAllCategories();
}