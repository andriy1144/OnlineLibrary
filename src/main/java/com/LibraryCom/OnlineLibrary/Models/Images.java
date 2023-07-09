package com.LibraryCom.OnlineLibrary.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Images")
@Getter
@Setter
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "origname")
    private String origname;

    @Column(name = "size")
    private Long size;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "bytes", columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @Column(name = "isPreviewImage")
    private Boolean isPreviewImage;
}
