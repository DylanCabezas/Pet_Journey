package com.dbp.pet_journey.servicio.domain;

public enum EstadoServicio {
    PENDIENTE ,      // El servicio ha sido solicitado pero aún no ha sido confirmado.
    CONFIRMADO,    // El servicio ha sido confirmado y está listo para ser realizado.
    EN_PROGRESO,  // El servicio está actualmente en ejecución.
    COMPLETADO,    // El servicio ha sido completado.
    CANCELADO,      // El servicio ha sido cancelado por el usuario o el cuidador.
    REEMBOLSADO,
}
