plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.springweb"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.0")
	compileOnly ("org.projectlombok:lombok")
	annotationProcessor ("org.projectlombok:lombok")
	testCompileOnly ("org.projectlombok:lombok")
	testAnnotationProcessor ("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
