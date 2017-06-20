# news-aggregation

A simple project to fetch RSS feeds from the New York Times, track user interactions on them
and recommend top articles based on the number of page views.

Z-Score ranking of the number of page views has been used to 
rank the individual articles

### Technologies ###
Java 8, Spring-Boot with Spring Data JPA and Spring Data REST

### Building ###
* Run './gradlew build' to build the software
* Run './gradlew bootRun' to run it locally

### Configuration ####
* application.yml configuration file

### Status ###

[![Build Status](https://travis-ci.org/DevObiero/news-aggregation.svg?branch=master)](https://travis-ci.org/DevObiero/news-aggregation)

