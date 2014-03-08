#
# Cookbook Name:: streethistory
# Attributes:: default
#
# Copyright 2014, Javier Gil Pereda
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

## Common application parameters
default['streethistory']['repository'] = 'https://github.com/jagilpe/streethistory'
default['streethistory']['db_name'] = 'db_streethistory'
default['streethistory']['db_username'] = 'sh_user'
default['streethistory']['db_password'] = 'sh_user'
default['streethistory']['app_name'] = 'streethistory'
default['streethistory']['work_dir'] = '/var/lib/streethistory'
default['streethistory']['srid'] = '4326'
default['streethistory']['photo_url'] = 'http\://localhost/photos'

## Database default parameters
default['streethistory']['database']['db_schema_sql'] = 'database/sh_create_schema.sql'
default['streethistory']['database']['db_data_init_sql'] = 'database/sh_load_test_data.sql'
default['streethistory']['database']['db_server'] = 'localhost'
default['streethistory']['database']['db_server_port'] = '5432'

## Backend default parameters
default['streethistory']['backend']['app_server'] = 'tomcat7'
default['streethistory']['backend']['appserv_user'] = 'tomcat7'
default['streethistory']['backend']['appserv_group'] = 'tomcat7'
default['streethistory']['backend']['deploy_dir'] = '/var/lib/tomcat7/webapps'
default['streethistory']['backend']['java_version'] = '7'
default['streethistory']['backend']['backend_src_dir'] = 'sh-backend'
default['streethistory']['backend']['artifact_id'] = 'streethistory'
default['streethistory']['backend']['resources_dir'] = 'src/main/resources'
default['streethistory']['backend']['commit_ctrl'] = 'bk_commit.deployed'
default['streethistory']['backend']['db_server'] = 'localhost'
default['streethistory']['backend']['db_server_port'] = '5432'
default['streethistory']['backend']['db_initial_conex'] = '20'
default['streethistory']['backend']['db_max_conex'] = '30'


## Frontend default parameters
default['streethistory']['frontend']['frontend_src_dir'] = 'sh-frontend'
default['streethistory']['frontend']['commit_ctrl'] = 'fr_commit.deployed'
default['streethistory']['frontend']['config_file'] = 'app/scripts/services/config.js'
default['streethistory']['frontend']['rest_server_name'] = 'localhost'
default['streethistory']['frontend']['rest_server_port'] = '8080'
default['streethistory']['frontend']['rest_location'] = 'streethistory/restful'
default['streethistory']['frontend']['rest_ssl'] = false