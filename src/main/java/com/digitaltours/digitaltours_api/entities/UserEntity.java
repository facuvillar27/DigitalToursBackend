// package com.digitaltours.digitaltours_api.entities;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "users")
// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// public class UserEntity {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String username;

//     @Column(nullable = false)
//     private String password;

//     @Column(nullable = false)
//     private String role; // Puede ser "ROLE_USER" o "ROLE_ADMIN"
// }

package com.digitaltours.digitaltours_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "railway")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String role;

}