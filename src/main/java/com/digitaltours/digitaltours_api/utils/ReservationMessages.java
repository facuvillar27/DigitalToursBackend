package com.digitaltours.digitaltours_api.utils;

public class ReservationMessages {

    public ReservationMessages() {
        throw new IllegalStateException("No existe un constructor para la clase ProductMessages");
    }

    public static final String CLIENT_ERROR = "CLIENT_ERROR";
    public static final String ERROR = "SERVER_ERROR";
    public static final String BAD_REQUEST_ERROR = "La informacion que envió no tiene el formato correcto.";
    public static final String RESERVATION_NOT_FOUND = "Reservación no encontrada";
    public static final String RESERVATIONS_NOT_FOUND = "Reservaciones no encontradas";
    public static final String RESERVATIONS_DELETE = "Reservación no pudo ser eliminada";
    public static final String RESERVATIONS_NOT_FOUND_USER = "El usuario no tiene reservaciones";
    public static final String RESERVATIONS_NOT_CREATED = "No se pudo crear la reservación";
}