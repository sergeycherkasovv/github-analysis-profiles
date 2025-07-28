# GitHub Action
[![my-actions](https://github.com/sergeycherkasovv/github-profile-analyzer/actions/workflows/main.yml/badge.svg)](https://github.com/sergeycherkasovv/github-profile-analyzer/actions/workflows/main.yml)
# SonarQube
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=bugs)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_github-profile-analyzer&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_github-profile-analyzer)

**GitHub Analysis Profiles** is a Telegram bot that analyzes the activity of a GitHub profile by _username_. It is built with Spring Boot and uses GraphQL to perform requests to the GitHub API.

**Bot:** [@GithubAnalysisBot](https://t.me/GithubAnalysisBot)

## Metrics
- Public Repositories
- Commits
- Forks
- Issues
- Pull Requests
- Code Reviews
- Followers
- Following
- Stars (Starred repositories)
- Stargazers (number of users who starred public repositories)
## Technology stack
- **Java 21**
- **[GraphQL](https://graphql.org/)**
- **[GitHub GraphQL API](https://docs.github.com/en/graphql)**
- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[WebClient](https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html)**
- **[JUnit5](https://junit.org/)**
- **CI/CD & Tools**:
    - [Gradle](https://gradle.org/)
    - [GitHub Actions](https://github.com/features/actions)
    - [SonarQube](https://www.sonarsource.com/)

## 🗂 Project structure
    java-project-99
    ├── .github/                          # CI configs (GitHub Actions, SonarQube)
    ├── src/
    │   ├── main/
    │   │   ├── java/telegram/bot/         
    │   │   │   ├── component/            # Application properties (Telegram, GitHub)
    │   │   │   ├── config/               # WebClient config
    │   │   │   ├── enums/                # Constants
    │   │   │   ├── exception/            # Custom exceptions
    │   │   │   ├── handler/              # Global Exception Handler
    │   │   │   ├── dto/                  # DTO classes for data transmission
    │   │   │   ├── repository/           # Repository
    │   │   │   ├── service/              # Business logic
    │   │   │   ├── telegram/             # Telegram bot entry point
    │   │   │   ├── userCountStatistic/   # Entyty classes 
    │   │   │   ├── util/                 # Utility classes 
    │   │   │   └── AppApplication.java   # Spring Boot application entry point
    │   │   └── resources/
    │   │       └── application           # Spring config files
    │   └── test/
    │       └── java/telegram/bot/         
    │           ├── service/              # Tests
    │           └── util/                 # 
    ├── build.gradle.kts                  # Gradle assembly (Kotlin DSL)
    ├── Makefile                          # Build, run and test commands
    └── README.md  

## 👤 Author
Developed as part of our own pet project

**Author:** [sergeycherkasovv](https://github.com/sergeycherkasovv)

**Email:** iamcherkasov.job@gmail.com
