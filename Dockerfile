# Base image — Java 17 + Maven!
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory inside container
WORKDIR /app

# Set CI environment!
ENV CI=true

# Copy pom.xml first — for dependency caching!
COPY pom.xml .

# Download dependencies (cached layer!)
RUN mvn dependency:go-offline -B

# Copy rest of project
COPY src ./src
COPY testng*.xml .


# Install Chrome for UI tests
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Default command — run smoke tests!
CMD ["mvn", "clean", "test", "-Psmoke", "-Denv=dev"]