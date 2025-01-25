ARG JAVA_VERSION
ARG GRADLE_IMAGE=gradle:jdk${JAVA_VERSION}-alpine
ARG BASE_IMAGE=ghcr.io/dimantchick/spring-base-image:$JAVA_VERSION
# Build project
FROM ${GRADLE_IMAGE} AS build
WORKDIR /workspace
COPY --chown=gradle:gradle ./src ./src
COPY --chown=gradle:gradle build.gradle ./build.gradle
COPY --chown=gradle:gradle settings.gradle ./settings.gradle
RUN gradle :clean :build --no-daemon

# Extract jar
FROM $BASE_IMAGE AS extract
ARG JAR_PATH
COPY --from=build /workspace/$JAR_PATH ./app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher

# Build container image
FROM $BASE_IMAGE
LABEL org.opencontainers.image.source=https://github.com/Dimantchick/spring-telegram-bot
LABEL org.opencontainers.image.description="Spring boot telegram bot image. \
                                            See https://github.com/Dimantchick/spring-telegram-bot for usage"
# Application layers
## Spring|Libraries
COPY --from=extract /workspace/app/dependencies/ ./
COPY --from=extract /workspace/app/spring-boot-loader/ ./
COPY --from=extract /workspace/app/snapshot-dependencies/ ./
## Application
COPY --from=extract /workspace/app/application/ ./

