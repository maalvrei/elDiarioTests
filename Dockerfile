FROM mcr.microsoft.com/playwright/java:v1.45.0-jammy

WORKDIR /app

COPY . .

CMD ["mvn", "test"]