#language: es
  Característica: Prueba técnica - Automatización

  La característica consta de dos escenarios para automatizar, el primero consta de 4 registros que se deben
  almacenar en una base de datos para posteriormente hacer los calculos y cruzar con la información almacenada.
  El segundo escenario consta de un enunciado donde debera acceder a una pagina web y llenar un formulario de
  contactenos.

  Antecedentes: Definir los parametros previos
    Dados los siguientes parametros de entrada
      | fecha       | portafolio | nominal | precio | total    |
      | 15/05/2020  | OBL-MODER  | 123000  |  23.65 | 2908950  |
      | 15/05/2020  | OBL-MODER  | 10000   |  25.00 | 250000   |
      | 15/05/2020  | OBL-RIESGO | 1276987 | -10.20 | -13023267|
    Entonces debo insertar los registros en la base de datos

  Escenario: Primer escenario - Calculos y cruce informacion
    Dados los parametros de entrada previos
    Entonces calcular el valor total asi [nominal * precio]
    Pero si el portafolio es "OBL-RIESGO" se debe calcular el valor total asi [(nominal * precio) + 2000]
    Y validar que el valor total calculado sea igual al campo total de la base de datos

  Escenario: Segundo escenario - Manejo de selenium
    Dada la siguiente url "https://somosmakers.co/"
    Cuando hacemos clic en el enlace de "CONTACTO"
    Entonces capturar el numero de celular de servicio al cliente
    Y llenar el formulario "dejenos un mensaje" y en el campo mensaje adjuntar el numero de celular capturado
    Y antes de hacer click en el boton "enviar mensaje" tomar un pantallazo con la información diligenciada