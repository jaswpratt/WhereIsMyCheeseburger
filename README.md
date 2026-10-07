# 🍔 WhereIsMyCheeseburger

A full-stack cheeseburger quest log — a personal journey to find your favorite cheeseburger, one restaurant, one region, one review at a time.

> *"Have you found your favorite cheeseburger yet?"*

## The Vision

This isn't meant to stay a simple review app. The long-term idea is a **gameful cheeseburger exploration universe** — a quest to discover your favorite cheeseburger that expands outward into restaurant reviews, recipes, BBQ and grilling, camping cookouts, American road trips, the regional variation of cheeseburgers across the U.S., and eventually the science behind what makes a great burger (the Maillard reaction, cheese melt dynamics, bun structure, and more).

The app is built to grow into that vision incrementally, one real feature at a time, rather than all at once.

## Current Features

- **Burger logging** — record a cheeseburger you've eaten: name, restaurant, rating, notes, side, and drink
- **Restaurants as real entities** — restaurants aren't just free text; they're their own records, automatically created the first time you log a burger there, and reused for every burger after
- **Regional tracking** — restaurants can be tagged with a U.S. state, laying the groundwork for exploring how cheeseburgers differ by region
- **Click-through details** — click any burger in your log to open a popup with its full details
- **Responsive, theme-aware styling** — a clean, minimal interface that automatically adapts to light/dark mode based on your system preference
- **Live deployment at** — `https://virginiabrightleaf.tech/cheeseburger-api/`

## Tech Stack

**Backend**
- Java 17, Spring Boot 3.5
- Spring Data JPA / Hibernate
- MariaDB 11.8
- Packaged as a WAR, deployed to Apache Tomcat

**Frontend**
- React 19
- Vite
- React Router

**Infrastructure**
- MariaDB (local dev + production, kept in sync)
- Nginx reverse proxy with HTTPS (Let's Encrypt)
- Deployed via Tomcat's Manager app

## Project Structure

```
WhereIsMyCheeseburger/
├── cheeseburger-api/        # Spring Boot backend
│   └── src/main/java/us/tn/greatsmokey/
│       ├── Burger.java
│       ├── BurgerController.java
│       ├── BurgerRepository.java
│       ├── Restaurant.java
│       ├── RestaurantRepository.java
│       └── CheeseburgerServer.java
└── cheeseburger-react/      # React frontend
    └── src/
        ├── pages/           # BurgerList, AddBurger
        ├── components/      # BurgerModal
        └── api/             # burgers.js (backend API calls)
```

Frontend files are colocated by feature rather than by file type — a component's `.jsx` and its `.css` live side by side, so everything relevant to one piece of UI is in one place.

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Node.js + npm
- MariaDB 11.8 (or compatible)

### Database setup

```sql
CREATE DATABASE cheeseburgerdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'cheeseburger_app'@'localhost' IDENTIFIED BY 'your_password_here';
GRANT ALL PRIVILEGES ON cheeseburgerdb.* TO 'cheeseburger_app'@'localhost';
FLUSH PRIVILEGES;
```

### Backend configuration

Create `cheeseburger-api/src/main/resources/application-local.properties`:

```properties
spring.datasource.url=jdbc:mariadb://127.0.0.1:3306/cheeseburgerdb
spring.datasource.username=cheeseburger_app
spring.datasource.password=your_password_here
```

This file is gitignored — never commit real credentials.

### Running locally

Backend:
```bash
cd cheeseburger-api
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Frontend (separate terminal):
```bash
cd cheeseburger-react
npm install
npm run dev
```

The app will be available at `http://localhost:5173/cheeseburger-api/`, with the backend API running on port 8080.

## Deployment

The backend is packaged as a WAR file and deployed to an Apache Tomcat server, with a separate `application-prod.properties` profile that pulls database credentials from environment variables rather than hardcoding them. A reverse proxy in front of Tomcat handles HTTPS termination via a Let's Encrypt certificate.

## Roadmap

Features from the original vision not yet built:

- [ ] Quest / achievement / XP system
- [ ] Recipe finder (build-your-own-burger, ingredient comparisons)
- [ ] BBQ & grilling content
- [ ] Road trip planning (burger-stop route building, using the regional data already in place)
- [ ] The science of cheeseburgers
- [ ] A "Burger Passport" for collecting stops as you travel
- [ ] The eventual, entirely optional cheeseburger multiverse

## License

Personal project — not currently licensed for reuse.
