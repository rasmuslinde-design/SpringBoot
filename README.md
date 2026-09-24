# Spring Boot CRUD kasutajate haldamiseks

See on iseseisva töö raames loodud Spring Booti veebirakendus. Rakendus võimaldab
kasutajaid lisada, vaadata, muuta ja kustutada ehk teostada CRUD-toiminguid.

Rakendus on loodud CodeJava Spring Boot CRUD videoõpetuse põhjal, kuid projekt
on seadistatud ja käivitatav iseseisva Maven-projektina.

## Kasutatud tehnoloogiad

- Java 17+
- Spring Boot
- Spring MVC
- Spring Data JPA ja Hibernate
- MySQL 8
- Docker ja Docker Compose
- Thymeleaf
- Bootstrap
- Maven
- JUnit ja Mockito automaattestid

## Projekti struktuur

- `user/User.java` - kasutaja andmemudel ehk JPA-entiteet
- `user/UserRepository.java` - andmebaasiga suhtlev repository
- `user/UserService.java` - rakenduse äriloogika
- `user/UserController.java` - kasutajate CRUD-veebiaadressid
- `src/main/resources/templates/` - Thymeleafi kasutajaliidese vaated
- `docker-compose.yml` - MySQL-i Docker-konteineri seadistus
- `src/test/` - teenuse automaattestid

## Andmebaas Dockeris

MySQL-i ei installitud arvutisse otse, vaid see käivitatakse Docker-konteineris.
Docker Compose loob andmebaasi `usersdb` ja säilitab andmed Docker volume'is.

Ühenduse seadistus:

| Seadistus | Väärtus |
|---|---|
| Andmebaas | `usersdb` |
| Kasutaja | `root` |
| Parool | `rootpass` |
| Host | `localhost` |
| Port | `3306` |

## Eeldused

Enne käivitamist peavad arvutis olema:

- Docker Desktop;
- Java 17 või uuem;
- IntelliJ IDEA või Maven.

## Käivitamine IntelliJ IDEA kaudu

1. Klooni GitHubi repositoorium:

   ```bash
   git clone <GitHubi-repositooriumi-aadress>
   cd SpringBoot
   ```

2. Käivita Docker Desktop.

3. Käivita projekti juurkaustas MySQL:

   ```bash
   docker compose up -d
   ```

4. Ava projekt IntelliJ IDEA-s Maven-projektina ja laadi `pom.xml` fail uuesti.

5. Käivita klass:

   ```text
   src/main/java/ee/opilane/springbootcrud/SpringbootCrudApplication.java
   ```

6. Ava brauseris [http://localhost:8080](http://localhost:8080).

Rakenduse esimesel käivitamisel loob Hibernate MySQL-i andmebaasi tabeli
`users` automaatselt.

## Käivitamine Maveniga

MacOS-i või Linuxi korral:

```bash
mvn spring-boot:run
```

IntelliJ IDEA kasutamisel võib rakenduse käivitada ka otse
`SpringbootCrudApplication.java` faili `main`-meetodist.

## Rakenduse aadressid

- `/` - avaleht
- `/users` - kõigi kasutajate nimekiri
- `/users/new` - uue kasutaja lisamine
- `/users/edit/{id}` - kasutaja muutmine
- `/users/delete/{id}` - kasutaja kustutamine

## Testide käivitamine

```bash
mvn test
```

Kui Maven ei ole käsureal paigaldatud, saab nii rakenduse kui ka testid
käivitada IntelliJ IDEA Maven-paneeli kaudu.

Testid kontrollivad muu hulgas kasutajate nimekirja tagastamist, puuduva
kasutaja käsitlemist ja kasutaja kustutamist.

## Peatamine

Spring Booti rakenduse peatamiseks vajuta IntelliJ IDEA konsoolis `Stop`.
MySQL-i konteineri peatamiseks kasuta:

```bash
docker compose down
```

Andmebaasi andmed jäävad alles Docker volume'i. Kui soovid ka andmed kustutada:

```bash
docker compose down -v
```
