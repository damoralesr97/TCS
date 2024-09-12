> Developed by: David Morales
----

# Prueba Tecnica TCS.

## Descripcion:
Se realiza la implementacion de 2 microservicios, uno para gestionar personas y clientes y otro para gestionar cuentas y movimientos.
La comunicacion de los microservicios es de forma asincroma mediante Kafka.
* #### Se realizo el caso en el que cuando se registra un cliente debe ingresar a parte de los datos personales, el tipo de cuenta que va a abrir y el monto. Con esto se realiza una transaccion asincrona para realizar el registro de la cuenta bancaria.
* #### El numero de cuenta se genera de forma automatica
* #### Se realizan pruebas unitarias y configuracion para ser desplegada en dockers.
* #### En la raiz del proyecto se encuentra el Json generado por Postman.

Para desplegar dirigirse a la carpeta docker
```shell
$ cd docker
```

Usar docker compose para iniciar los contenedores
```shell
$ docker-compose up
```
