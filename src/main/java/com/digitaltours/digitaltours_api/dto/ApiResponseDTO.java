package com.digitaltours.digitaltours_api.dto;

import com.digitaltours.digitaltours_api.utils.Meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiResponseDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {

    private Meta meta;
    private Object data;
}
