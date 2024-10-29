package com.digitaltours.digitaltours_api.utils;

public class ProductMessages {

    private ProductMessages() {
        throw new IllegalStateException("No existe un constructor para la clase ProductMessages");
    }

    public static final String CLIENT_ERROR = "CLIENT_ERROR";
    public static final String ERROR = "SERVER_ERROR";
    public static final String BAD_REQUEST_ERROR = "La informacion que envió no tiene el formato correcto.";
}