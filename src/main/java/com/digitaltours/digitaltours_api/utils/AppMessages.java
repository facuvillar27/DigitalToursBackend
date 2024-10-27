package com.digitaltours.digitaltours_api.utils;

public class AppMessages {

    private AppMessages() {
        throw new IllegalStateException("No existe un constructor para la clase AppMessages");
    }

    public static final String CLIENT_ERROR = "CLIENT_ERROR";
    public static final String ERROR = "SERVER_ERROR";
    public static final String BAD_REQUEST_ERROR = "La informacion que envió no tiene el formato correcto.";
    public static final String UNAUTHORISED_MESSAGE = "Usted no está autorizado para acceder este recurso.";
    public static final String MESSAGE_NOT_FOUND = "No se encontró información";
}