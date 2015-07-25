# BQ Ebook Library Sample

## Introduction

This project has been built following the **"Clean architecture"** values.
The idea behind this concept is to build solid and maintainable systems.

In order to carry out its ideas, the system has been divided into three main layers: **app, domain** and **data**.

### App layer

This android framework dependent module contains the logic related with views, and it has been built following the Model View Presenter
architectural pattern.

Inside this module we will find activities acting just as views, with just UI logic inside. This will be possible
thanks to presenters, that will act as view - model mediators.


### Domain layer

This pure java module contains the business rules of the system. The base components of this layer are the **interactors**.

Interactors are just system usecases that will be executed outside the UI thread.


### Data layer

This module provides all data that the system needs. That data access will be grouped in Datasource classes.


## Things to highlight

+ You will find two main datasources in the system: DropboxDatasource and MockDbDatasource.
The second one in combination with Dagger can be easily used to test the system without actually interacting with Dropbox.

+ Data layer will be android framework dependent because of the main data source of the system, Dropbox.
I have decided to use Android Dropbox API, which usually requires context to execute requests. This
dependencies will be satisfied by Dagger.

+ In order to simplify drawables, I have included Trello's Victor library in combination with Material
Design svg menu icons.

+ I have decided to use Glide instead of Picasso for asynchronous image loading because it lets you to
easily load images from byte arrays.

+ Data layer model classes will be transformed into Domain layer model classes via mappers.







