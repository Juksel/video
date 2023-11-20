package com.example.video.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long fileId;
    Long sum;
}
