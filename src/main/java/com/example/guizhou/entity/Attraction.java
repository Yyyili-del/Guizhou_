package com.example.guizhou.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attraction")
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String location;

    @Column(length = 50)
    private String category;

    @Column(length = 500)
    private String description;

    private String imageUrl;

    private Double rating;

    private Integer viewCount;

    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        if (viewCount == null) viewCount = 0;
        if (rating == null) rating = 0.0;
    }
}
