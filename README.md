# Advanced software engineering 

**Date**: 12/2020

**Project**: Steambuddy - Games recommender

**Authors**: Jochum Peter, Lila Keti, Marktl Daniel, Starzacher Philipp, Tragut Mathias, Zenzmaier Florian


## Build & Run

### Frontend

Refer to the [frontend README](web/README.md)

### Backend

TODO

## Technology choices

The project uses the following technology stack.

### Project management

* Scrum/Kanban-Board

### Frontend

* Angular
* Protractor (E2e testing)

### Backend

* Java
* Spring-Framework
* JUnit

### Software technology tools

* Pre-commit
* Sonarqube (?)
* CI-System (one of Travis, Gitlab, DroneCI, CircleCI)

## Application epics

### Database of games

Games are classified using Tags, Genres and Groups

Games consist of Title, Image, Description

### User and group management

Membership (Tags, Genres, Groups)
Friends

### Users game collections

Users should be able to organize a collection of their games,
which is the base for the recommendation of other games they do not yet own.

#### Game collection view

Users can view all the games from their collection.

#### Game detail view

* Adding games to my collection
* Rating games

### Game recommendation system

The Core part of the application is a recommendation system 
which is able to find games the user might enjoy based on current games in the collection.

#### Games you like

* View to find games or recommend games of interest.

#### Games by genre

* Support pagination

### Profile page

* Edit your name
* Search friends
* Add friends
