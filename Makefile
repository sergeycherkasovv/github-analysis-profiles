clean:
	./gradlew clean

test:
	./gradlew test

reload-classes:
	./gradlew -t classes

start-prod:
	./gradlew bootRun --args='--spring.profiles.active=prod'

install:
	./gradlew installDist

lint:
	./gradlew checkstyleMain checkstyleTest

help:
	@echo "make start-prod      	- launch Spring Boot in the production profile"
	@echo "make install         	- build a self-sufficient distribution"
	@echo "make clean   		- remove build artifacts"
	@echo "make test    		- run all the tests"
	@echo "make lint            	- perform code verification via Checkstyle"
	@echo "make reload-classes  	- auto-reassembly of classes with changes"

.PHONY: test clean reload-classes start-prod install lint help