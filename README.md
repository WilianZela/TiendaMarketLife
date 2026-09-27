# Tienda Web (v1)

Aplicación full stack simple: Spring Boot (backend + API REST) sirviendo un frontend
en HTML/CSS/JS puro. Incluye login, registro (con opción de registrarse como vendedor)
y un dashboard con los productos publicados.

## Funcionalidad de esta v1

- Registro e inicio de sesión con sesión basada en cookies (Spring Security).
- Al registrarte puedes marcar "Quiero registrarme como vendedor" (rol `SELLER`).
- Dashboard con los productos en venta. Si no hay ninguno, muestra
  "No hay productos en venta".
- Si tu cuenta es de vendedor, ves además un formulario para publicar productos.
- Persistencia en una base de datos H2 guardada en archivo (carpeta `data/`), sin
  necesidad de instalar nada aparte.

## Requisitos

- JDK 17+
- Maven 3.9+ (o usa el wrapper si lo agregas con `mvn -N io.takari:maven:wrapper`)

## Cómo correrlo en local

```bash
mvn spring-boot:run
```

Abre `http://localhost:8080` (redirige a la pantalla de login).

## Cómo generar el .jar para producción

```bash
mvn clean package
java -jar target/tienda-web.jar
```

## Desplegarlo en la web

Render (y la mayoría de plataformas similares) no tienen un entorno nativo para
Java: para desplegar ahí, la forma estándar es con Docker. El proyecto ya incluye
un `Dockerfile` listo para eso.

1. Sube este proyecto a un repositorio de GitHub (incluyendo el `Dockerfile`).
2. En Render: New > Web Service > selecciona tu repo > en "Environment" elige
   **Docker** (no "Node" ni "Java", que no existe como entorno nativo). Render
   detecta el `Dockerfile` automáticamente y no hace falta build/start command.
3. La app ya lee el puerto desde la variable de entorno `PORT`
   (`server.port=${PORT:8080}` en `application.properties`), que es como Render
   se comunica con el contenedor — no necesitas configurar nada extra.
4. Al desplegar, Render construye la imagen con el `Dockerfile` (usa Maven dentro
   de la imagen para compilar) y luego corre `java -jar app.jar`.

También puedes subir el `.jar` a cualquier VPS propio y correrlo con
`java -jar tienda-web.jar` detrás de un proxy (nginx, Caddy, etc.) si quieres
usar tu propio dominio con HTTPS.

### Sobre la base de datos en producción

Por defecto se usa H2 en archivo (carpeta `data/`) para que la v1 funcione sin
configurar nada. Si despliegas en una plataforma con almacenamiento efímero
(el filesystem se resetea en cada redeploy, como suele pasar en los planes
gratuitos de Render/Railway), los datos se perderán al redesplegar. Para producción
real, lo recomendable es cambiar a una base de datos administrada (por ejemplo
PostgreSQL, que Render y Railway ofrecen gratis): basta con agregar la dependencia
`org.postgresql:postgresql` en el `pom.xml` y apuntar `spring.datasource.url`,
`spring.datasource.username` y `spring.datasource.password` a las credenciales que
te den, idealmente leídas desde variables de entorno.

## Limitaciones conocidas de esta v1 (para próximas versiones)

- CSRF está deshabilitado para simplificar el flujo de login/registro vía fetch.
  Antes de un uso más serio, conviene revisar esto (por ejemplo migrando a
  autenticación con JWT).
- No hay edición ni eliminación de productos, ni subida de imágenes.
- No hay recuperación de contraseña.
- El estilo es intencionalmente simple, como pediste para esta primera versión.
