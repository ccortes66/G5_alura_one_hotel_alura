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

![DiagramaFisico](https://github.com/ccortes66/G5_alura_one_hotel_alura/assets/63931313/9e2ebf88-f696-4a4b-b30f-5701130edd3c)


## Docker-Compose mariaDB
```docker

version: '3.8'
services:
  mariadb:
    image: mariadb:10.11.4
    container_name: mariadb
    restart: unless-stopped
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: su_contraseña
      MYSQL_DATABASE: hotel_alura
      MYSQL_USER: su_usuario
      MYSQL_PASSWORD: su_contraseña
    volumes:
      - mariadb_data:/var/lib/mysql

volumes:
  mariadb_data:

```
## Habilitar privilegios en MariaDB
```bash
   $ docker exec -it mariadb bash
   $ mysql -u root -p
   $ GRANT ALL PRIVILEGES ON * . * TO 'su_usuario'@'%'
   $ FLUSH PRIVILEGES
```

## Recomendaciones
- Instalar docker y docker-compose, copiar script apartado docker compose MariaDB, generar nuevo archivo llamado docker-compose.yml guardarlo, abir la terminal, buscar el directorio donde se generó el archivo, escriben el comando docker-compose up -d para levantar el contenedor, docker-compose down para parar el contenedor.
  
- Registrar el primer empleado esto en el fin de crear los métodos de pago, habitación tipo y habitación teminado en direccionWeb:8083
  
- Si tiene errores con h-captcha, se recomienda colocar un host diferente a localhost, en Linux editan el archivo /etc/hosts(permisos admin) nano o vin colocan 
```bash
   127.0.0.1 hotelalura.com
```
- Puertos por defecto:8080 clientes y 8083 para empleados, si tienen inconvenientes con los puertos suele suceder por estas razones esta ocupado o este bloqueado por el firewall de su PC

## Interface Usuario
Interface cliente

https://github.com/ccortes66/G5_alura_one_hotel_alura/assets/63931313/37250a0a-888e-4ce2-b109-96dfef6c8c29



