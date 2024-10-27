package com.digitaltours.digitaltours_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    // Constructor sin ID
    public ImageDTO(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    private Long id;
    private String name;
    private String imageUrl;
}
