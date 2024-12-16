package com.digitaltours.digitaltours_api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.ReservationDTO;
import com.digitaltours.digitaltours_api.entities.DatesEntity;
import com.digitaltours.digitaltours_api.entities.ReservationEntity;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.mappers.ReservationMapper;
import com.digitaltours.digitaltours_api.repository.DatesRepository;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.repository.ReservationRepository;
import com.digitaltours.digitaltours_api.repository.UserRepository;
import com.digitaltours.digitaltours_api.service.EmailService;
import com.digitaltours.digitaltours_api.service.ReservationService;

import jakarta.mail.MessagingException;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final DatesRepository datesRepository;

    @Autowired
    private EmailService emailService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, DatesRepository datesRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.datesRepository = datesRepository;
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        UserEntity user = userRepository.findById(reservationDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("Reservation not found"));


        DatesEntity date = datesRepository.findById(reservationDTO.getDateId())
            .orElseThrow(() -> new RuntimeException("Date not found"));


        if (date.getAvailable_space() < reservationDTO.getNumberOfPeople()) {
            throw new RuntimeException("Not enough space");
        }

        ReservationEntity reservation = ReservationMapper.mapReservationDTO(reservationDTO);
        reservation.setUser(user);
        reservation.setDate(date);
        reservation.setConfirmationNumber("DT-" + UUID.randomUUID().toString().substring(0, 8));


        reservation = reservationRepository.save(reservation);

        date.setAvailable_space(date.getAvailable_space() - reservationDTO.getNumberOfPeople());
        datesRepository.save(date);

        try {
            emailService.sendConfirmationReserve(
                user.getEmail(),
                user.getUsername(), 
                date.getProduct().getName(), 
                date.getDate().toString(),
                date.getProduct().getStartTime(),
                reservation.getConfirmationNumber()
            );
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al enviar el correo de confirmacion de reserva");
        }

        return ReservationMapper.mapReservation(reservation);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
            .map(ReservationMapper::mapReservation)
            .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        ReservationEntity reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reservation not found"));


        return ReservationMapper.mapReservation(reservation);
    }

    @Override
    public List<ReservationDTO> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId).stream()
            .map(ReservationMapper::mapReservation)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteReservation(Long id) {
        ReservationEntity reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reservation not found"));

        DatesEntity date = reservation.getDate();
        date.setAvailable_space(date.getAvailable_space() + reservation.getNumberOfPeople());
        datesRepository.save(date);

        reservationRepository.delete(reservation);
    }
    
}
