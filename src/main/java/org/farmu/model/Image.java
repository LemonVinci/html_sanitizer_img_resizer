package org.farmu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue
    private int id;

    private String inputImg;

    private String outputPath;

    private int newWidth;

    private int newHeight;

}