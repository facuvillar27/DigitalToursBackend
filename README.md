# DigitalToursAPI

## Descripción

**DigitalToursBackend** es una api desarrollada en Spring Boot que sirve como parte de una plataforma digital para la gestión de tours. Esta aplicación proporciona funcionalidades esenciales como seguridad, gestión de errores, y documentación, facilitando la interacción con un frontend correspondiente.

## Tecnologías

- **Framework**: Spring Boot
- **Versión de Spring Boot**: 3.3.5
- **Versión de Java**: 21
- **Base de Datos**: MySQL (con H2 como opción para pruebas)

## Estructura del Proyecto

La arquitectura estándar de Spring Boot utilizada en este proyecto incluye las siguientes capas:

- **Controllers**: Manejan las solicitudes HTTP y responden con datos adecuados.
- **Services**: Contienen la lógica de negocio de la aplicación.
- **Service Implementations**: Implementaciones concretas de los servicios.
- **Entities**: Clases que representan las tablas de la base de datos.
- **Mappers**: Clases encargadas de convertir entre DTOs y entidades.
- **DTOs (Data Transfer Objects)**: Objetos que transportan datos entre las capas.
- **Exceptions**: Manejo de excepciones personalizadas.
- **Repository**: Interfaces que extienden de `JpaRepository` para acceso a datos.
- **Utils**: Métodos de utilidad para tareas comunes.
- **application.yaml**: Archivo de configuración de la aplicación.
- **Pruebas Unitarias**: Pruebas para asegurar la funcionalidad de cada componente.

## Funcionalidades

### Documentación

Se utiliza **Swagger** para generar documentación interactiva de la API, facilitando la visualización de los endpoints disponibles.
La ruta local es <http://localhost:8080/api/v1/swagger-ui/index.html>.

### Seguridad

La aplicación implementa autenticación y autorización utilizando JWT, asegurando que solo los usuarios autenticados puedan acceder a ciertos recursos.

### Manejo de Errores

Se han implementado controladores de excepciones para manejar errores de forma centralizada y enviar respuestas significativas al cliente.

### Validaciones

Se utilizan anotaciones de validación de Java para asegurar que los datos recibidos cumplan con los requisitos antes de ser procesados.

## Configuración de la Base de Datos

### MySQL

La aplicación se conecta a una base de datos MySQL para almacenar los datos persistentes.

### H2 (opcional)

Se puede utilizar H2 como base de datos en memoria para pruebas. Para habilitar H2, asegúrate de configurar las dependencias y el `application.yaml` apropiadamente.

## Pruebas Unitarias

Se han implementado pruebas unitarias para garantizar el correcto funcionamiento de cada componente de la aplicación, utilizando **JUnit** y **Mockito**.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
