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

* A postgresql database with PostGIS extension to manage the geographical data
* An Spring backend, that exposes a RESTful WS to the possible clients
* An Angular Frontend, that consumes the RESTful-WS of the backend

database
--------

This directory contains the differents scripts used to set up the database of the application and some
initial test data. It has also a pgmodeler file with the schema of the database.


sh-backend
----------

This directory contains the Eclipse project of the Spring backend of the application.

In order to build the application you will need to have the JDK 7 and maven installed in your computer and run
`maven package`. This will create a war (`streethistory.wr`) that can be used to deploy the app in Java Web 
Container or App Server with JRE 7 installed. It has been tested in Tomcat 7 and Glassfish 3.0.1

sh-frontend
-----------

This directory contains the Yeoman project of the Angular frontend of the application.

chef-repo
---------

This directory contains all the cookbooks and roles used to automate the deployment and operation of
the different components of the application.

Special significance have in this directory:

* the `streethistory` cookbook
* the `roles` directory
* the `environments` directory

#### streethistory cookbook

This cookbook is used to build and deploy the different parts of the application. More information 
about this cookbook can be found in its [README.md](https://github.com/jagilpe/streethistory/blob/master/chef-repo/README.md)

#### roles directory

In this directory can be found the definition files for the different roles that we are considering
for the purpose of the blog.

In this moment the roles defined are:

* base-ubuntu - role with the common configuration of the ubuntu servers used
* db_server_pg - role with the configuration of a postgresql database server for the application
* backend - role with the configuration to set up the backend application in a Tomcat 7
* frontend - role with the configuration to set up the frontend in a ngix server

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