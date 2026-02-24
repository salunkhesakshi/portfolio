# Full-Stack Developer Portfolio

Production-ready portfolio platform with a **React + Vite frontend** and **Spring Boot backend** using clean/hexagonal-style package boundaries.

## Stack
- Frontend: React (Vite), Tailwind CSS, Redux Toolkit, Axios, React Router
- Backend: Spring Boot 3, Spring Security + JWT, Spring Data JPA, Hibernate Validator, PostgreSQL, Swagger/OpenAPI, Actuator
- Deployment: Dockerfile for backend, environment-based configuration

## Architecture Overview
### Backend package structure
- `domain`: core model placeholders and business boundaries
- `application`: use-cases/ports (`PortfolioUseCase`) and services
- `infrastructure`: persistence entities/repositories and security internals
- `controller`: API adapters (public/admin/auth)
- `config`: security and seed configuration
- `exception`: global exception handling

### Public APIs
- `GET /api/profile`
- `GET /api/skills`
- `GET /api/projects`
- `POST /api/contact`

### Admin APIs (JWT + ADMIN)
- `POST /api/admin/project`
- `PUT /api/admin/project/{id}`
- `DELETE /api/admin/project/{id}`
- `POST /api/admin/skill`

### Auth
- `POST /api/auth/login`

## Database
- SQL schema: `backend/schema.sql`
- JPA/Hibernate entities map to:
  - `users`, `profile`, `skills`, `projects`, `contacts`

## Local Run
### 1) Backend
```bash
cd backend
mvn spring-boot:run
```

Environment variables:
- `DB_URL` (default: `jdbc:postgresql://localhost:5432/portfolio_db`)
- `DB_USER` (default: `postgres`)
- `DB_PASSWORD` (default: `postgres`)
- `JWT_SECRET` (must be >= 32 chars)
- `JWT_EXPIRATION_MS` (default: 86400000)

Useful endpoints:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Actuator health: `http://localhost:8080/actuator/health`

Default seeded admin:
- email: `admin@portfolio.dev`
- password: `admin123`

### 2) Frontend
```bash
cd frontend
npm install
npm run dev
```

Optional env:
- `VITE_API_URL=http://localhost:8080/api`

## Docker (Backend)
```bash
cd backend
docker build -t portfolio-backend .
docker run -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host.docker.internal:5432/portfolio_db \
  -e DB_USER=postgres \
  -e DB_PASSWORD=postgres \
  -e JWT_SECRET=01234567890123456789012345678901 \
  portfolio-backend
```

## Notes
- CORS is enabled for all origins by default for easy integration. Restrict in production.
- Validation, global exception handling, and logging are configured.
- Seed data is inserted at startup if tables are empty.
