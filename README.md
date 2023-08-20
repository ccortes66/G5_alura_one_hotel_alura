# G5_alura_one_hotel_alura
![one](https://github.com/ccortes66/G5_alura_one_hotel_alura/assets/63931313/dbdde68a-7557-4914-9df1-b80669045e0f)

## Tecnologías
- MariaDb(Base De Datos)
- Javalin(Framework)
- Java Template Engine(parte Visual)
- Serializar/de serializar(Jackson databind)
- Java JPA (Operaciones a base de datos)
- Inyección de dependencia(Google Guice)
- lombok(Generar Getters y Setters)
- Jdk version 17(amazon corretto)
- Argon2(cifrar contraseñas)
- DTO records

## Diagrama Físico DDBB

![DiagramaFisico](https://github.com/ccortes66/G5_alura_one_hotel_alura/assets/63931313/2842a48a-1a4d-44c3-82d5-5ba4e55aa464)

## Recomendaciones
- Registrar el primer empleado esto en el fin de crear los métodos de pago, habitación tipo y habitación teminado en direccionWeb:8083
- Si tiene errores con h-captcha, se recomienda colocar un host diferente a localhost, en Linux editan el archivo /etc/hosts(permisos admin) nano o vin colocan 
 ```bash
   127.0.0.1 hotelalura.com
```
- Puertos por defecto:8080 clientes y 8083 para empleados, si tienen inconvenientes con los puertos suele suceder por estas razones
  esta ocupado o este bloqueado por el firewall de su PC
