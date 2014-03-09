Street History
==============

Introduction
------------

This repository contains the sources of all the elements used in the blog
[A Devops Journey](http://adevopsjourney.wordpress.com) to illustrate all the 
articles and reflections in it made.

The idea behind the blog is to explore all the aspects involved in the design, development,
deployment and operation of an multitier application, focusing in the relationship between 
all the aspects from an integrated standpoint, instead of viewing it as different individual
elements. For example, instead of making a deep explanation of how to develop an Spring MVC app
or how to use chef for the automation of the app's infrastructure, we would look at how deals 
chef with the management of the different environments (development, testing, staging, production...)
and will try to find how it affects at the way we should manage the configuration of the app when 
we are developing it (extenalizing it, for example, to a file which can be modified by chef at 
deploy-time).

We will take as an starting point for the development of a really simple application for managing 
geolocalized photos and videos and show them in a map. We'll progressively add the different pieces
of the puzzle (automated operation, continious integration, monitoring...), in order to find interesting
points of interaction between them.

Basically this application has in this moments:

* A [postgresql](http://www.postgresql.org/) database with [PostGIS](http://postgis.net/) extension to manage the geographical data
* An [Spring](http://spring.io/) backend, that exposes a RESTful WS to the possible clients
* An [Angular](http://angularjs.org/) Frontend, that consumes the RESTful-WS of the backend

database
--------

This directory contains the differents scripts used to set up the database of the application and some
initial test data. It has also a pgmodeler file with the schema of the database.


sh-backend
----------

This directory contains the [Eclipse](http://www.eclipse.org/) project of the Spring backend of the application.

In order to build the application you will need to have the JDK 7 and [Maven](http://maven.apache.org/) installed in your computer and run
`maven package`. This will create a war (`streethistory.war`) that can be used to deploy the app in Java Web 
Container or App Server with JRE 7 installed. It has been tested in [Tomcat 7](http://tomcat.apache.org/) 
and [Glassfish 3.0.1](https://glassfish.java.net/)

sh-frontend
-----------

This directory contains the [Yeoman](http://yeoman.io/) project of the frontend of the application 
based on [Angular](http://angularjs.org/).

chef-repo
---------

This directory contains all the [Chef](http://www.getchef.com/chef/) cookbooks and roles used to automate the deployment and operation of
the different components of the application.

Special significance have in this directory:

* the `streethistory` cookbook
* the `roles` directory
* the `environments` directory

#### streethistory cookbook

This cookbook is used to build and deploy the different parts of the application. More information 
about this cookbook can be found in its 
[README.md](https://github.com/jagilpe/streethistory/blob/master/chef-repo/cookbooks/streethistory/README.md)

#### roles directory

In this directory can be found the definition files for the different roles that we are considering
for the purpose of the blog.

In this moment the roles defined are:

* `base-ubuntu` - role with the common configuration of the ubuntu servers used
* `db_server_pg` - role with the configuration of a postgresql database server for the application
* `backend` - role with the configuration to set up the backend application in a Tomcat 7
* `frontend` - role with the configuration to set up the frontend in a [ngix](http://nginx.org/) server

vagrant
-------

This directory different vagrant boxes configurations to give a quick look to the deployment process and options, and
to the application.

In this moment the configured vagrant environments are:

* `all_in_one` - a virtual machine with all the roles (database server, backend and frontend)
* `database_server` - a virtual machine with the database
* `backend_server` - a virtual machine with a fully functional database and backend

More information about this cookbook can be found in its 
[README.md](https://github.com/jagilpe/streethistory/blob/master/vagrant/README.md)

License and Authors
-------------------
Authors: Javier Gil Pereda

Copyright (C) 2014 Javier Gil Pereda 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at 

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and 
limitations under the License.