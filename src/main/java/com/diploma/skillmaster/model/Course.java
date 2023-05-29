package com.diploma.skillmaster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdOn;
    @UpdateTimestamp
    private Instant updatedOn;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Step> steps = new ArrayList<>();
}
