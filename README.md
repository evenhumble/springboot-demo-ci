[![Build Status](https://travis-ci.org/evenhumble/sprintboot-demo-ci.svg?branch=master)](https://github.com/evenhumble/springboot-demo-ci)
# ci-spring-boot

Example of Continuous Integration using Travis and Spring Boot.

This example is a simulation of a Task Manager.

## Technologies

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/)
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [AngularJS](https://angularjs.org/)
* [Travis CI](https://travis-ci.org/)
* [Heroku](https://www.heroku.com/)

## Configuration

> You need to have installed Java and Maven

##### Spring Boot

  For the development profile you do ot need to configure anything. In this profile you will use a h2 database.
  
  If you want to use a production profile, you need to change the database configurations into __application-prod.properties__. In my example I use a environment variable __${DATABASE_URL}__ to configure the database url. 


  
##### Travis CI

Create an account and put your repository to be observed by travis. 

In your new listener, you need to configure the environment variable $HEROKU_API_KEY or put directly into a __.travis.yml__ (it's not secure). 

You need to change the __deploy>app__ into __.travis.yml__ to the same name of heroku app.

## Run

##### Dev Profile

```sh
$ mvn spring-boot:run 
```

##### Production Profile

```sh
$ mvn spring-boot:run -Dspring.profiles.active=prod
```


