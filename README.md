# 🐾 PetJourney: Servicios para el Cuidado de Mascotas 🏨🚶‍♂️

PetJourney es un servicio diseñado para ayudar a personas con mascotas que tienen un estilo de vida ocupado, ya sea por trabajo o viajes frecuentes. Este servicio ofrece hoteles, guarderías y paseos personalizados para que las mascotas reciban el mejor cuidado posible mientras sus dueños están ocupados.

## 🌟 Curso: *CS 2031 - Desarrollo Basado en Plataforma* 🌟

---

## 👥 **Integrantes del Equipo** 👥

| **🐾 Miembros del Equipo 🐾**  |
|--------------------------------|
| Dylan Andres Cabezas Ramirez   |
| Yitzhak Abraham Namihas Millan |
| Carla Viviana Molina Álvarez   |
| Tana Sofía Suárez Arce         |



---


## 📑 Índice

1. [Introducción](#introducción)
2. [Identificación del Problema o Necesidad](#identificación-del-problema-o-necesidad)
3. [Descripción de la Solución](#descripción-de-la-solución)
4. [Funcionalidades Claves](#funcionalidades-claves)
5. [Modelo de Entidades](#modelo-de-entidades)
6. [Testing y Manejo de Errores](#testing-y-manejo-de-errores)
7. [Medidas de Seguridad Implementadas](#medidas-de-seguridad-implementadas)
8. [Eventos y Asincronía](#eventos-y-asincronía)
9. [Conclusión](#conclusión)
10. [Apéndices](#apéndices)

## 🔍 Introducción

### 🌍 Contexto

PetJourney es un servicio tecnológico diseñado para ayudar a aquellas personas con mascotas que necesitan asistencia en el cuidado de sus animales cuando no pueden estar presentes. La propuesta se enfoca en crear una plataforma digital donde los dueños de mascotas puedan encontrar personas capacitadas para brindar diversos servicios de cuidado. A través de una API, la plataforma permitirá a los usuarios acceder a una red de cuidadores que ofrecen servicios como paseos, hospedaje en casa, visitas de control y actividades recreativas.
La API permitirá conectar a los usuarios con los cuidadores de mascotas de forma rápida y eficiente.


### 🎯 Objetivos del Proyecto

1. Proveer soluciones integrales de cuidado para mascotas mientras sus dueños trabajan o viajan.
2. Ofrecer servicios de hotel, guarderías y paseos personalizados para mascotas.
3. Promover el bienestar emocional y físico de las mascotas en espacios seguros.
4. Fomentar experiencias recreativas entre dueños y mascotas con servicios de transporte y actividades.

## 📝 Identificación del Problema o Necesidad

### ⚠️ Descripción del Problema

PetJourney responde a la problemática que enfrentan muchos dueños de mascotas que, debido a compromisos laborales, viajes o agendas ocupadas, no siempre tienen el tiempo necesario para cuidar adecuadamente a sus animales. Esta falta de tiempo puede generar preocupación por el bienestar de las mascotas, quienes necesitan atención constante. PetJourney ofrece una solución tecnológica que facilita la conexión entre los dueños de mascotas y cuidadores, permitiendo que los usuarios gestionen de manera eficiente el cuidado de sus mascotas a través de una plataforma digital, asegurando que siempre puedan encontrar apoyo confiable cuando lo necesiten.

### 💡 Justificación

PetJourney ofrece una solución práctica para las personas que buscan alternativas de cuidado profesional para sus mascotas mientras trabajan o viajan, asegurando que sus mascotas reciban atención y cariño en un ambiente seguro.

## 💡 Descripción de la Solución

PetJourney facilita que los dueños de mascotas encuentren servicios confiables de cuidado a través de una plataforma que conecta a cuidadores, hoteles y servicios de paseo. Los usuarios pueden realizar reservas y gestionar los cuidados de sus mascotas desde cualquier lugar.

## 🐕‍🦺 Funcionalidades Claves

1. **Registro de usuarios y cuidadores**: Los dueños y cuidadores pueden registrarse, gestionar sus perfiles y acceder a la plataforma.
2. **Búsqueda de cuidadores**: Filtrar cuidadores por ubicación, tipo de servicio, disponibilidad y experiencia.
3. **Reservas y pagos**: Sistema de reservas para gestionar horarios y un módulo de pagos para facilitar las transacciones.
4. **Sistema de reseñas**: Los usuarios pueden calificar y dejar comentarios sobre los servicios recibidos.
5. **Geolocalización y seguimiento en tiempo real**: Rastreo de paseos o servicios en tiempo real para tranquilidad de los dueños.
6. **Notificaciones y recordatorios**: Sistema de notificaciones para reservas, pagos pendientes y actualizaciones.

## 📊 Modelo de Entidades

### Entidades Principales:
- **Usuario**: Representa a los dueños de mascotas y cuidadores.
  
  UsuarioService
  
| **Método**                            | **Descripción**                                                                                                                       |
|---------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| `loginUsuario(UsuarioRequestDto)`     | Registra un nuevo usuario. Verifica si el nombre de usuario y el correo electrónico ya están en uso. Si no, guarda el usuario en la base de datos. Devuelve la respuesta del usuario registrado. |
| `getUsuario(Long id)`                 | Obtiene los detalles de un usuario específico a partir de su ID. Lanza una excepción si el usuario no se encuentra.                   |
| `deleteUsuario(Long id)`              | Elimina un usuario de la base de datos utilizando su ID.                                                                              |
| `updateUsuario(Long id, UsuarioUpdateRequestDto)` | Actualiza la información de un usuario existente. Verifica que el nuevo nombre de usuario y correo electrónico no estén en uso. Devuelve la respuesta del usuario actualizado. |
| `agregarMascota(Long id, MascotaRequestDto)` | Agrega una nueva mascota al usuario. Verifica la existencia del usuario y guarda la mascota, luego la asocia al usuario. Devuelve la lista actualizada de mascotas del usuario. |
| `eliminarMascota(Long usuarioId, Long mascotaId)` | Elimina una mascota asociada a un usuario. Verifica la existencia del usuario y la mascota antes de eliminarlas de la base de datos. |
| `actualizarMascota(Long mascotaId, MascotaUpdateRequestDto)` | Actualiza los detalles de una mascota específica utilizando su ID.                                                                     |
| `setMascotaServicio(Long mascotaId, Long servicioId)` | Asigna un servicio a una mascota. Envía un correo electrónico al usuario con los detalles del cuidador asignado y cambia el estado del servicio a PENDIENTE. Devuelve la respuesta del servicio actualizado. |

  UsuarioController
  
| **Método**                                             | **Descripción**                                                                                                           |
|-------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| `loginUsuario(UsuarioRequestDto)`                     | Registra un nuevo usuario. Recibe los datos del usuario en el cuerpo de la solicitud y llama al servicio para guardar el usuario. Devuelve un estado HTTP 201 (Creado). |
| `getUsuario(Long id)`                                 | Obtiene los detalles de un usuario específico a partir de su ID. Devuelve la información del usuario en la respuesta.     |
| `updateUsuario(Long id, UsuarioUpdateRequestDto)`    | Actualiza la información de un usuario existente. Recibe el ID del usuario y los nuevos datos en la solicitud, luego llama al servicio para realizar la actualización. Devuelve la información del usuario actualizado. |
| `deleteUsuario(Long id)`                              | Elimina un usuario de la base de datos utilizando su ID. Devuelve un estado HTTP 204 (Sin contenido) tras la eliminación.  |
| `agregarMascota(Long usuarioId, MascotaRequestDto)`  | Agrega una nueva mascota al usuario especificado. Recibe los datos de la mascota y devuelve la lista actualizada de mascotas del usuario. |
| `eliminarMascota(Long usuarioId, Long mascotaId)`    | Elimina una mascota asociada a un usuario. Recibe el ID del usuario y de la mascota y devuelve el usuario actualizado tras la eliminación. |
| `actualizarMascota(Long mascotaId, MascotaUpdateRequestDto)` | Actualiza los detalles de una mascota específica. Recibe el ID de la mascota y los nuevos datos, y devuelve la información actualizada. |
  
- **Mascota**: Información de las mascotas (nombre, raza, edad).

  MascotaService
  
| **Método**                                           | **Descripción**                                                                                                         |
|-----------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| `saveMascota(MascotaRequestDto, Usuario)`          | Crea una nueva mascota a partir de los datos proporcionados en `MascotaRequestDto`. Asocia la mascota al usuario y la guarda en la base de datos. Devuelve la mascota guardada. |
| `getMascota(Long id)`                               | Recupera una mascota de la base de datos utilizando su ID. Si no se encuentra, lanza una excepción. Devuelve los detalles de la mascota en un objeto `MascotaResponseDto`. |
| `updateMascota(Long id, MascotaUpdateRequestDto)`  | Actualiza los datos de una mascota existente. Busca la mascota por su ID, actualiza sus atributos con los datos de `MascotaUpdateRequestDto`, y guarda los cambios. Devuelve los detalles actualizados en un objeto `MascotaUpdateResponseDto`. |
| `deleteMascota(Long id)`                            | Elimina una mascota de la base de datos utilizando su ID.                                                            |
| `verificarBirthdayMascotas()`                        | Método programado que se ejecuta a diario. Verifica si alguna mascota cumple años en la fecha actual, incrementa su edad y registra un mensaje de cumpleaños en los logs. Luego, guarda los cambios en la base de datos. |

  MascotaController
  
| **Método**                                            | **Descripción**                                                                                                          |
|------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| `getMascota(Long id)`                                | Recupera los detalles de una mascota utilizando su ID. Llama al servicio `MascotaService` para obtener la información y devuelve un objeto `MascotaResponseDto`. |
| `updateMascota(Long id, MascotaUpdateRequestDto)`   | Actualiza los detalles de una mascota existente. Recibe un ID y un objeto `MascotaUpdateRequestDto`, luego llama al servicio `MascotaService` para realizar la actualización. |
| `deleteMascota(Long id)`                             | Elimina una mascota de la base de datos utilizando su ID. Llama al servicio `MascotaService` para realizar la eliminación y devuelve una respuesta sin contenido. |

- **Servicio**: Servicios que pueden ser solicitados (hotel, paseo).

  ServicioService
  
| **Método**                                           | **Descripción**                                                                                                       |
|-----------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| `postServicio(ServicioRequestDto servicioRequestDto, Cuidador cuidador)` | Crea un nuevo servicio para el cuidado de mascotas. Recibe un objeto `ServicioRequestDto` con los detalles del servicio y un objeto `Cuidador`. Se mapea la información y se guarda en la base de datos utilizando `ServicioRepository`. |

- **Cuidador**: Encargado de los servicios de cuidado y paseo.

  CuidadorService
  
| **Método**                                           | **Descripción**                                                                                                      |
|-----------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| `saveCuidador(CuidadorRequestDto cuidadorRequestDto)` | Crea y guarda un nuevo cuidador en la base de datos utilizando la información del `CuidadorRequestDto`.               |
| `getCuidador(Long id)`                              | Recupera un cuidador existente a partir de su ID. Si no se encuentra, lanza una excepción.                           |
| `crearServicio(Long cuidadorId, ServicioRequestDto servicioRequestDto)` | Crea un nuevo servicio asociado a un cuidador, guardando tanto el servicio como la referencia en el cuidador.         |
| `crearHospedaje(Long cuidadorId, HospedajeRequestDto hospedajeRequestDto)` | Crea un hospedaje para el cuidador y lo asocia al objeto `Cuidador`.                                               |
| `deleteServicio(Long cuidadorId, Long servicioId)` | Elimina un servicio asociado a un cuidador, actualizando la lista de servicios del cuidador en el proceso.            |
| `agregarRecomendacion(Long cuidadorId, RecomendacionDto recomendacionDto)` | Agrega una recomendación al cuidador, asociando la recomendación a su registro y guardándola.                         |
| `deleteCuidador(Long id)`                           | Elimina un cuidador de la base de datos utilizando su ID.                                                            |

  CuidadorController
  
| **Método**                                                                    | **Descripción**                                                                                                    |
|------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| `saveMascota(CuidadorRequestDto cuidadorRequestDto)`                       | Crea un nuevo cuidador utilizando la información proporcionada en `CuidadorRequestDto` y lo guarda en la base de datos. |
| `getCuidador(Long id)`                                                      | Recupera un cuidador existente a partir de su ID. Si no se encuentra, lanza una excepción.                         |
| `deleteCuidador(Long id)`                                                   | Elimina un cuidador de la base de datos utilizando su ID.                                                          |
| `agregarMascota(Long cuidadorId, ServicioRequestDto servicioRequestDto)`   | Agrega un nuevo servicio al cuidador especificado, actualizando la lista de servicios del cuidador.                 |
| `deleteServicio(Long cuidadorId, Long servicioId)`                         | Elimina un servicio asociado al cuidador especificado, actualizando la lista de servicios en el proceso.            |
| `crearhopedaje(Long cuidadorId, HospedajeRequestDto hospedajeRequestDto)`  | Crea un hospedaje para el cuidador especificado y lo asocia a su perfil.                                         |
| `agregarRecomendacion(Long cuidadorId, RecomendacionDto recomendacionDto)` | Agrega una recomendación al cuidador, asociando la recomendación a su registro y guardándola.                       |

## 🧪 Testing y Manejo de Errores

Se han implementado pruebas unitarias para verificar la correcta funcionalidad de las principales operaciones del sistema. Los errores se manejan mediante excepciones personalizadas y respuestas adecuadas para el cliente.

## 🔒 Medidas de Seguridad Implementadas

1. **Autenticación y autorización**: Se utiliza Spring Security con JWT (JSON Web Token) para garantizar que solo usuarios autenticados puedan acceder a los servicios.
2. **Roles y permisos**: Roles para diferenciar entre administradores, cuidadores y usuarios.
3. **Protección de datos**: Los datos sensibles se almacenan de manera segura, y se implementan prácticas para proteger la privacidad de los usuarios.

## ⏲️ Eventos y Asincronía

Cuando se asigna un servicio, el usuario recibe un correo detallado con la información del cuidador, incluyendo su nombre y contacto. Además, se especifican los detalles del servicio, como el tipo, fecha, hora y costo, junto con notas importantes sobre la mascota. Este correo garantiza que el dueño esté informado y tenga la tranquilidad de saber quién está a cargo del cuidado de su mascota, ofreciendo transparencia y facilitando la comunicación directa con el cuidador. Para poder generar este evento el se crea al ususario, su mascota, luego se crea al cuidador, este crea un servicio y un hospedaje, y al momento de que el usuario asigne un servicio a su mascota le llegara el correo a este.

## 🎯 Conclusión

PetJourney busca mejorar la calidad de vida tanto de las mascotas como de sus dueños, ofreciendo soluciones de cuidado de alta calidad y permitiendo que las mascotas reciban la atención que merecen mientras sus dueños están ocupados.

## 📎 Apéndices
