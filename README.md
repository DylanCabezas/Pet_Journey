# 🐾 PetJourney: Servicios para el Cuidado de Mascotas 🏨🚶‍♂️

PetJourney es un servicio diseñado para ayudar a personas con mascotas que tienen un estilo de vida ocupado, ya sea por trabajo o viajes frecuentes. Este servicio ofrece hoteles, guarderías y paseos personalizados para que las mascotas reciban el mejor cuidado posible mientras sus dueños están ocupados.

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
- **Mascota**: Información de las mascotas (nombre, raza, edad).
- **Servicio**: Servicios que pueden ser solicitados (hotel, paseo).
- **Cuidador**: Encargado de los servicios de cuidado y paseo.

## 🧪 Testing y Manejo de Errores

Se han implementado pruebas unitarias para verificar la correcta funcionalidad de las principales operaciones del sistema. Los errores se manejan mediante excepciones personalizadas y respuestas adecuadas para el cliente.

## 🔒 Medidas de Seguridad Implementadas

1. **Autenticación y autorización**: Se utiliza Spring Security con JWT (JSON Web Token) para garantizar que solo usuarios autenticados puedan acceder a los servicios.
2. **Roles y permisos**: Roles para diferenciar entre administradores, cuidadores y usuarios.
3. **Protección de datos**: Los datos sensibles se almacenan de manera segura, y se implementan prácticas para proteger la privacidad de los usuarios.

## ⏲️ Eventos y Asincronía

Cuando se asigna un servicio, el usuario recibe un correo detallado con la información del cuidador, incluyendo su nombre y contacto. Además, se especifican los detalles del servicio, como el tipo, fecha, hora y costo, junto con notas importantes sobre la mascota. Este correo garantiza que el dueño esté informado y tenga la tranquilidad de saber quién está a cargo del cuidado de su mascota, ofreciendo transparencia y facilitando la comunicación directa con el cuidador.

## 🎯 Conclusión

PetJourney busca mejorar la calidad de vida tanto de las mascotas como de sus dueños, ofreciendo soluciones de cuidado de alta calidad y permitiendo que las mascotas reciban la atención que merecen mientras sus dueños están ocupados.

## 📎 Apéndices
