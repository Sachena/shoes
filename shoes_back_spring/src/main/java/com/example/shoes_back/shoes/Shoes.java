package com.example.shoes_back.shoes;

import com.example.shoes_back.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shoesName;

    private String nameKorea;

    private String url;

    @OneToMany(mappedBy = "shoes", fetch = FetchType.EAGER)
    private Set<Comment> shoesComments = new HashSet<>();


}
