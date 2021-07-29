package com.example.shoes_back.comment;

import com.example.shoes_back.shoes.Shoes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String rating;

    private String description;

    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;


}
