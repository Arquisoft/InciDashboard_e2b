# language: es
Característica: Solventar el problema de una incidencia peligrosa
  Escenario: El agente entra en la aplicación para solventar el problema de una incidencia peligrosa
    Dado el agente "Pedro" con contraseña "1234"
    Cuando el agente hace login correctamente
    Y hace click en la opción de aniadir comentario una incidencia, dentro de una incidencia seleccionada
    Y crea un comentario
    Entonces aparece el comentario creado en la lista