package com.example.batch.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "minha_tabela")
@Data
public class MinhaEntidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    private LocalDateTime dataFim;
    
    private String status;
}
