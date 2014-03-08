#
# Cookbook Name:: streethistory
# Recipe:: database
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

## Include prerequisites
include_recipe 'git'
include_recipe 'database::postgresql'

## Parameters definition
source_path = '#{Chef::Config[:file_cache_path]}/streethistory'


## Clone git repository
git '#{source_path}' do
  repository node['streethistory']['repository']
  action :sync
end

## Setup database
# Database connection ruby hash for user postgres
postgresql_connection_info = {
  :host     => node['streethistory']['database']['db_server'],
  :port     => node['streethistory']['database']['db_server_port'],
  :username => 'postgres',
  :password => node['postgresql']['password']['postgres']
}
# Create user
postgresql_database_user node['streethistory']['db_username'] do
  connection    postgresql_connection_info
  password      node['streethistory']['db_password']
  database_name node['streethistory']['db_name']
  action        :create
end
# Create database
postgresql_database node['streethistory']['db_name'] do
  connection    postgresql_connection_info
  owner         node['streethistory']['db_username']
  action        :create
end
# Create postgis extension
postgresql_database node['streethistory']['db_name'] do
  connection    postgresql_connection_info
  sql           'CREATE EXTENSION IF NOT EXISTS postgis'
  action        :query
end
# Database connection ruby hash for app user
postgresql_connection_info_sh = {
  :host     => node['streethistory']['database']['db_server'],
  :port     => node['streethistory']['database']['db_server_port'],
  :username => node['streethistory']['db_username'],
  :password => node['streethistory']['db_password']
}
# Create database schema
postgresql_database node['streethistory']['db_name'] do
  exists = <<-EOH
  su postgres -c "psql -d #{node['streethistory']['db_name']} -c '\\dt' | grep -c sh_user" | grep 0
  EOH
  connection    postgresql_connection_info_sh
  sql           { ::File.open('#{source_path}/' + node['streethistory']['database']['db_schema_sql']).read }
  action        :query
  only_if exists
end
# Populate initial data
postgresql_database node['streethistory']['db_name'] do
  exists = <<-EOH
  su postgres -c "psql -d #{node['streethistory']['db_name']} -c 'select title from photo' | grep -c Photo" | grep 0
  EOH
  connection    postgresql_connection_info_sh
  sql           { ::File.open('#{source_path}/' + node['streethistory']['database']['db_data_init_sql']).read }
  action        :query
  only_if exists  
end
