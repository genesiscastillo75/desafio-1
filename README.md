## 📌 **README.md** - Aplicación PREVIRED

```md
# 🌟 Mi Aplicación PREVIRED (WAR)

Este proyecto es una aplicación **Spring Boot** empaquetada como **WAR** para desplegar en **Apache Tomcat**.

## 🚀 Características

✅ Spring Boot con soporte para empaquetado WAR  
✅ Controladores REST y vistas con HTML con AJAX de JQuery 
✅ Configuración para desplegar en Tomcat  
✅ Base de datos H2 para pruebas  

---

## 📂 **Estructura del Proyecto**

```bash
desafio-1/
│   01 README.md
│   02 Instrucciones del ejercicio-1.md
│   deploy.cmd
│   empleados_100000.csv
│   empleados_100002.csv
│   pom.xml
│   README.md
│
├───.settings
│       .jsdtscope
│       org.eclipse.core.resources.prefs
│       org.eclipse.jdt.apt.core.prefs
│       org.eclipse.jdt.core.prefs
│       org.eclipse.m2e.core.prefs
│       org.eclipse.wst.common.component
│       org.eclipse.wst.common.project.facet.core.xml
│       org.eclipse.wst.jsdt.ui.superType.container
│       org.eclipse.wst.jsdt.ui.superType.name
│       org.eclipse.wst.validation.prefs
│       org.springframework.ide.eclipse.prefs
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───cl
│   │   │       └───genesiscastillo
│   │   │           └───previred
│   │   │               │   Application.java
│   │   │               │   ServletInitializer.java
│   │   │               │
│   │   │               ├───controllers
│   │   │               │       EmpleadoController.java
│   │   │               │       IndexController.java
│   │   │               │
│   │   │               ├───dtos
│   │   │               │       EmpleadoDTO.java
│   │   │               │
│   │   │               ├───entities
│   │   │               │       Country.java
│   │   │               │       Empleado.java
│   │   │               │
│   │   │               ├───repositories
│   │   │               │       EmpleadoRepository.java
│   │   │               │
│   │   │               ├───services
│   │   │               │       EmpleadoService.java
│   │   │               │
│   │   │               └───validations
│   │   │                       RutValidator.java
│   │   │                       SalarioValidator.java
│   │   │                       ValidaSalario.java
│   │   │                       ValidRut.java
│   │   │
│   │   ├───resources
│   │   │   │   application.properties
│   │   │   │   data.sql
│   │   │   │
│   │   │   └───templates
│   │   │           index.html
│   │   │
│   │   └───webapp
│   └───test
│       └───java
│           └───cl
│               └───genesiscastillo
│                   └───previred
│                           EmpleadoControllerTest.java
│                           EmpleadoServiceTest.java
│                           SalarioValidatorTest.java
│
```

---

## 🔧 **Configuración y Ejecución**

### 📦 **1. Clonar el repositorio**
```sh
git clone https://github.com/tuusuario/mi-aplicacion.git
cd mi-aplicacion
```

### ⚙️ **2. Configurar el entorno**
Edita `src/main/resources/application.properties` si necesitas modificar la configuración.

### 🛠️ **3. Construir el WAR**
```sh
mvn clean package
```
El WAR generado estará en `target/mi-aplicacion.war`.

### 🚀 **4. Desplegar en Tomcat**
1. Copia el archivo `.war` en la carpeta de despliegue de Tomcat:
   ```sh
   cp target/mi-aplicacion.war /ruta/a/tomcat/webapps/
   ```
2. Inicia Tomcat:
   ```sh
   ./catalina.sh run  # Linux/macOS
   catalina.bat run  # Windows
   ```
3. Abre en el navegador:
   ```
   http://localhost:8080/mi-aplicacion/
   ```

---

## 🛠 **Tecnologías Usadas**
- **Spring Boot**  
- **Spring MVC**  
- **Thymeleaf**  
- **JPA / Hibernate**  
- **H2 Database (para pruebas)**  
- **Maven**  
- **Tomcat**  

---

## 📜 **Endpoints REST**
| Método | Ruta | Descripción |
|--------|------|------------|
| `GET` | `/` | Página de inicio |
| `GET` | `/api/saludo` | Devuelve un mensaje JSON |
| `POST` | `/api/usuarios` | Crea un usuario |

Ejemplo de endpoint:
```sh
curl -X GET http://localhost:8080/api/saludo
```

---

## 🔍 **Contribuir**
1. Haz un fork del proyecto 🍴  
2. Crea una nueva rama: `git checkout -b feature-nueva`  
3. Realiza tus cambios y commitea: `git commit -m 'Agregado nuevo feature'`  
4. Haz push a tu rama: `git push origin feature-nueva`  
5. Crea un Pull Request 🚀  

---

## 📄 **Licencia**
Este proyecto está bajo la licencia **MIT**.

---

### 🎯 **Notas Finales**
Si tienes problemas o dudas, ¡abre un issue en el repositorio! 🚀

```

---

Este README.md es **claro, detallado y fácil de seguir**. ¿Quieres agregar algo más? 😊