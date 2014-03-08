Street History Vagrant Boxes
============================

Here there are different vagrant boxes configurations to give a quick look to different deployment options.

* `all_in_one` - a virtual machine with all the roles (database server, backend and frontend)
* `database_server` - a virtual machine with the database
* `backend_server` - a virtual machine with a fully functional database and backend

All the boxes are use an ubuntu precise32 box as a basis and then different components 
are deployed using chef-solo and the cookbooks and roles of the `chef-repo` directory
of the repository.

Requirements
============

In order to run the virtual machines you will need installed:

* vagrant: 1.3.5+
* VirtualBox: 4.3+

Running the machines
====================

To interact with `vagrant` just open a terminal and change to the desired directory. All the commands run in
this directory will use the configurations in the file `Vagrantfile`.

Different commands you can make:

* start the virtual machine: `vagrant up`
* connect to a console of the virtual machine: `vagrant ssh`
* stop the virtual machine: `vagrant halt`
* destroy the virtual machine once you've finished with it: `vagrant destroy`

More information about vagrant can be found in [Vagrant's documentation site](http://docs.vagrantup.com/v2/)

Boxes
=====

all_in_one
----------

###Description

This box clones downloads an updated version of the application from 
the [github repository](https://github.com/jagilpe/streethistory) and deploys in the same virtual
machine all the components of the application (database, backend and frontend).

This is the recommended box to be able to have an updated working version of the application in a box,
and also to watch the whole process of deployment using chef.

Once lauched and after some minutes (from 15 to 30 depending on the computer) it will have:

* cloned the repository
* installed all the required software
* set up the database
* built from the updated sources and deployed the backend application
* built from the updated sources and deployed the frontend application

After it has finished all this the application will be availabe in the port 9080. To access it just
go to [http://localhost:9080/](http://localhost:9080/)

###Platform

This box has been tested in Mac OSX 10.8.5 Mountain Lion.

database
--------

###Description

This box clones downloads an updated version of the application from 
the [github repository](https://github.com/jagilpe/streethistory) and configures a database server
with an updated version of the database for the use by the backend application.

This could be, for example, the box used by a backend developer to have an updated version
of the database running with a controlled configuration and version of the software.

Once lauched and after some minutes (from 15 to 30 depending on the computer) it will have:

* cloned the repository
* installed all the required software
* set up the database

After it has finished all this the postgresql server will be listening in the ip 127.0.0.1 and port 5432.

backend
-------

###Description

This box clones downloads an updated version of the application from 
the [github repository](https://github.com/jagilpe/streethistory) and sets up an updated version of the
backend application running in a Tomcat7 server and a postgresql server with and updated version of the 
database.

This could be, for example, the box used by a fronted developer to have an updated version
of the database running with a controlled configuration and version of the software.

Once lauched and after some minutes (from 15 to 30 depending on the computer) it will have:

* cloned the repository
* installed all the required software
* set up the database
* built from the updated sources and deployed the backend application

After it has finished all this the backend application will be availabe in the port 8080. It exposes the
RESTful Web Services used by the frontend application in 
[http://localhost:8080/streethistory/restful](http://localhost:8080/streethistory/restful)
