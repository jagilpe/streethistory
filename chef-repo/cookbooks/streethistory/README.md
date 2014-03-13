streethistory
=============
This cookbook is used to build and deploy the different parts of the multitier application `Street History`.


Requirements
------------
This cookbook has been tested with Chef 11.8.0 but should work without problem with Chef 11.0.0 or later.

###Platfrom
This cookbook has been tested on:

* Ubuntu 12.04 - Precise


### Cookbooks
#### streethistory::database

The following cookbooks are dependencies for the `database` recipe:

* git
* database

#### streethistory::backend

* git
* maven

#### streethistory::frontend

* git
* nodejs
* rsync


Attributes
----------

#### streethistory common attributes
<table>
  <tr>
    <th>Key</th>
    <th>Type</th>
    <th>Description</th>
    <th>Default</th>
  </tr>
  <tr>
    <td><tt>['streethistory']['repository']</tt></td>
    <td>String</td>
    <td>url of the git repository</td>
    <td><tt>https://github.com/jagilpe/streethistory</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['app_name']</tt></td>
    <td>String</td>
    <td>name of the application</td>
    <td><tt>streethistory</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['db_name']</tt></td>
    <td>String</td>
    <td>name of the database</td>
    <td><tt>db_streethistory</tt></td>
  </tr>
 <tr>
   <td><tt>['streethistory']['db_username']</tt></td>
   <td>String</td>
   <td>user to connect to the database server</td>
   <td><tt>sh_user</tt></td>
 </tr>
 <tr>
   <td><tt>['streethistory']['db_password']</tt></td>
   <td>String</td>
   <td>password to connect to the database server</td>
   <td><tt>sh_user</tt></td>
 </tr>
 <tr>
   <td><tt>['streethistory']['work_dir']</tt></td>
   <td>String</td>
   <td>directory to store deployment information</td>
   <td><tt>/var/lib/streethistory</tt></td>
 </tr>
 <tr>
   <td><tt>['streethistory']['srid']</tt></td>
   <td>String</td>
   <td>SRID to use with geographical data</td>
   <td><tt>4326</tt></td>
 </tr>
 <tr>
   <td><tt>['streethistory']['photo_url']</tt></td>
   <td>String</td>
   <td>base url to access the photos repository</td>
   <td><tt>http\://localhost/photos</tt></td>
 </tr>
</table>

#### streethistory::database
<table>
  <tr>
    <th>Key</th>
    <th>Type</th>
    <th>Description</th>
    <th>Default</th>
  </tr>
  <tr>
    <td><tt>['streethistory']['database']['db_schema_sql']</tt></td>
    <td>String</td>
    <td>path to the schema creation sql script</td>
    <td><tt>database/sh_create_schema.sql</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['database']['db_data_init_sql']</tt></td>
    <td>String</td>
    <td>path to the sample data initialization sql script</td>
    <td><tt>database/sh_load_test_data.sql</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['database']['db_server']</tt></td>
    <td>String</td>
    <td>fqdn or ip of the database server</td>
    <td><tt>localhost</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['database']['db_server_port']</tt></td>
    <td>String</td>
    <td>tcp port used to connect to the database server</td>
    <td><tt>5432</tt></td>
  </tr>
</table>

#### streethistory::backend
<table>
  <tr>
    <th>Key</th>
    <th>Type</th>
    <th>Description</th>
    <th>Default</th>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['app_server']</tt></td>
    <td>String</td>
    <td>web container or app server used</td>
    <td><tt>tomcat7</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['appserv_user']</tt></td>
    <td>String</td>
    <td>user to run the app server with</td>
    <td><tt>tomcat7</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['appserv_group']</tt></td>
    <td>String</td>
    <td>group of the user to run the app server wit</td>
    <td><tt>tomcat7</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['deploy_dir']</tt></td>
    <td>String</td>
    <td>path to the deployment directory</td>
    <td><tt>/var/lib/tomcat7/webapps</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['java_version']</tt></td>
    <td>String</td>
    <td>java version</td>
    <td><tt>7</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['backend_src_dir']</tt></td>
    <td>String</td>
    <td>source directory of the backend application within the repository</td>
    <td><tt>sh-backend</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['artifact_id']</tt></td>
    <td>String</td>
    <td>name of the artifact</td>
    <td><tt>streethistory</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['resources_dir']</tt></td>
    <td>String</td>
    <td>path to the configuration resources directory of the app</td>
    <td><tt>src/main/resources</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['commit_ctrl']</tt></td>
    <td>String</td>
    <td>name of to file to store the latest commit of the backend successfully deployed on the server</td>
    <td><tt>bk_commit.deployed</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['db_server']</tt></td>
    <td>String</td>
    <td>fqdn or ip of the database server the backend must conect to</td>
    <td><tt>localhost</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['db_server_port']</tt></td>
    <td>String</td>
    <td>tcp port used by the backend to connect to the database server</td>
    <td><tt>5432</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['db_initial_conex']</tt></td>
    <td>String</td>
    <td>number of the initial conexions of the pool</td>
    <td><tt>20</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['backend']['db_max_conex']</tt></td>
    <td>String</td>
    <td>number of the maximum conexions of the pool</td>
    <td><tt>30</tt></td>
  </tr>
</table>

#### streethistory::frontend
<table>
  <tr>
    <th>Key</th>
    <th>Type</th>
    <th>Description</th>
    <th>Default</th>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['frontend_src_dir']</tt></td>
    <td>String</td>
    <td>source directory of the frontend application within the repository</td>
    <td><tt>sh-frontend</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['commit_ctrl']</tt></td>
    <td>String</td>
    <td>name of to file to store the latest commit of the frontend successfully deployed on the server</td>
    <td><tt>fr_commit.deployed</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['config_file']</tt></td>
    <td>String</td>
    <td>path to the configuration file of the frontend application</td>
    <td><tt>app/scripts/services/config.js</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['rest_server_name']</tt></td>
    <td>String</td>
    <td>fqdn or ip of the backend server the user will connect to</td>
    <td><tt>localhost</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['rest_server_port']</tt></td>
    <td>String</td>
    <td>tcp port of the backend server</td>
    <td><tt>8080</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['rest_location']</tt></td>
    <td>String</td>
    <td>path to access the restful web services</td>
    <td><tt>streethistory/restful</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['rest_ssl']</tt></td>
    <td>Boolean</td>
    <td>whether or not will ssl used to connect to the backend server</td>
    <td><tt>false</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['web_server_user']</tt></td>
    <td>String</td>
    <td>user owner of the deployed files</td>
    <td><tt>root</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['web_doc_root']</tt></td>
    <td>String</td>
    <td>path to the www files</td>
    <td><tt>/var/www</tt></td>
  </tr>
  <tr>
    <td><tt>['streethistory']['frontend']['photo_url']</tt></td>
    <td>String</td>
    <td>base url for the photo files</td>
    <td><tt>photos/</tt></td>
  </tr>
	
</table>

Recipes
-------

#### database
Fetches the latest version of the application from the git repository and then sets up the database
for the application. The tasks that this recipes executes are:

* Clones the git repository
* Creates the user
* Creates the database
* Creates the PostGIS extension in the database
* Creates the database schema
* Loads the sample data

#### backend
Fetches the latest version of the application from the git repository and then builds and deploys the 
backend application. The tasks that this recipes executes are:

* Clones the git repository
* Recreates the configuration files with the overridden attributes
* Builds the application using maven
* Copies the resulting war artifact to the deployment directory of the server

It only build and deploys the application if the commit id downloaded is different from the latest
successfully deployed.

#### frontend
Fetches the latest version of the application from the git repository and then builds and deploys the 
frontend application. The tasks that this recipes executes are:

* Clones the git repository
* Creates the root directory for the files if it does not exists
* Recreates the configuration files with the overridden attributes
* Installs all the build dependencies (Compass, Yeoman)
* Builds the application using Grunt
* Copies the resulting files to the deployment directory

It only build and deploys the application if the commit id downloaded is different from the latest
successfully deployed.

Usage
-----
#### streethistory::database

Just include `streethistory::database` in your node's `run_list` and override the default attributes, if needed:

In ruby...

```ruby
name "db_server_pg"
description "PostgreSQL database server"
run_list "recipe[postgresql::server]", "recipe[streethistory::database]"
default_attributes "postgresql" => {
  "version" => "9.3",
  "config" => {
    "ssl" => "false"
  },
  "server" => {
    "packages" => ["postgresql-9.3", "postgresql-9.3-postgis-scripts"]
  }
},
"streethistory" => {
	"db_username" => "sh_user",
	"db_password" => "very_secret_password",
	"database" => {
		"db_server" => "server"
	}
}	

```

...or in json

```json
{
  "name":"db_server_pg",
	"description":"PostgreSQL database server",
  "run_list": [
		"recipe[postgresql::server]",
    "recipe[streethistory::database]"
  ],
	"default_attributes": {
		"postgresql": {
			"version": "9.3",
			"config": {
				"ssl": "false"
			},
			"server": {
				"packages": ["postgresql-9.3", "postgresql-9.3-postgis-scripts"]
			}
		},
		"streethistory": {
			"db_username": "sh_user",
			"db_password": "very_secret_password",
			"database": {
				"db_server": "server"
			}
		}	
	}
}
```


#### streethistory::backend
Just include the addecuate recipe to install the desired Java app server and `streethistory::backend` 
in your node's `run_list` and override the default attributes, if needed. You should remember to include the `java`
recipe if you will use a different version from the default java version installed in the destination platform.

In ruby...

```ruby
name "backend"
description "Backend server"
run_list "recipe[java]", "recipe[tomcat]", "recipe[streethistory::backend]"
default_attributes "maven" => {
    "setup_bin" => true
  },
  "java" => {
    "jdk_version" => '7'
  },
  "tomcat" => {
    "base_version" => '7'
  },
	"streethistory" => {
		"backend" => {
			"deploy_dir" => "/var/lib/tomcat7/webapps"
		}
	}
```
...or in json
```json
{
  "name":"backend",
	"description":"Backend server",
  "run_list": [
		"recipe[java]", 
		"recipe[tomcat]", 
		"recipe[streethistory::backend]"
  ],
	"default_attributes": {
		"maven": {
	    "setup_bin": true
	  },
	  "java": {
	    "jdk_version": "7"
	  },
	  "tomcat": {
	    "base_version": "7"
	  },
		"streethistory": {
			"backend": {
				"deploy_dir": "/var/lib/tomcat7/webapps"
			}
		}
	}	
}
```

#### streethistory::frontend
Just include the addecuate recipe to install the desired web server and `streethistory::frontend` 
in your node's `run_list` and override the default attributes, if needed:

In ruby...

```ruby
name "frontend"
description "Frontend server"
run_list "recipe[nginx]", "recipe[streethistory::frontend]"
default_attributes "streethistory"=> {
  "frontend" => {
    "web_server_user" => "www-data",
    "web_doc_root" => "/var/www/nginx-default"
  },
  "nginx" => {
    "default_root" => "/var/www/nginx-default",
    "user" => "www-data"
  }
}
```

...or in json

```json
{
	"name":"frontend",
	"description":"Frontend server",
	"run_list": [
		"recipe[nginx]", 
		"recipe[streethistory::frontend]"
	],
	"default_attributes": {
		"streethistory": {
			"frontend": {
				"web_server_user": "www-data",
				"web_doc_root": "/var/www/nginx-default"
			}
		},
		"nginx":{
	    "default_root": "/var/www/nginx-default",
	    "user": "www-data"
		}
	}
}

```

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
