API REST – Gestión de Personas (EC1)
Descripción
API REST desarrollada con Spring Boot para la gestión de personas y sus relaciones (documento, direcciones, mascota y roles).
Proyecto realizado como parte de la Evaluación Continua 1 (EC1) del curso Desarrollo de Servicios Web 2 – IDAT.

🧩 Entidades Principales

Person → Información personal del usuario (tb_persona)
Documento → DNI / Pasaporte (tb_documento)
Dirección → Múltiples direcciones por persona (tb_direccion)
Mascota → Información de la mascota (tb_mascota)
Rol → Roles del sistema (tb_rol)

Relaciones JPA implementadas:

OneToOne (Person ↔ Documento)
OneToMany (Person → Direcciones)
ManyToOne (Person → Mascota)
ManyToMany (Person ↔ Rol)

Endpoints (CRUD)
Base URL:
http://localhost:8080/api/v1/persons

Método	Endpoint	Descripción	Auth
GET	/api/v1/persons	Listar personas	
GET	/api/v1/persons/{id}	Obtener por ID	
GET	/api/v1/persons/search/country?pais=	Buscar por país	
POST	/api/v1/persons	Crear persona	
PUT	/api/v1/persons/{id}	Actualizar persona	
DELETE	/api/v1/persons/{id}	Eliminar persona

Documentación Swagger

La API cuenta con documentación interactiva:
http://localhost:8080/swagger-ui.html
Permite probar todos los endpoints CRUD directamente desde el navegador.

Tecnologías

Java 17
Spring Boot 3
Spring Web
Spring Data JPA (Hibernate)
Spring Security (Basic Auth)
H2 Database
Swagger / OpenAPI (SpringDoc)
Maven
JUnit 5 + Mockito

Pruebas
Se implementaron pruebas unitarias para la capa de servicio con JUnit 5 y Mockito.
Seguridad
Autenticación HTTP Basic Auth con Spring Security.
Usuarios de prueba:
admin / admin123 → Rol ADMIN
user / user123 → Rol USER
Ubicación:
src/test/java/com/idat/Tarea3/service/PersonServiceTest.java


Ejecución:
mvn test
