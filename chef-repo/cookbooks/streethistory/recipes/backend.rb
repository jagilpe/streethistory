#
# Cookbook Name:: streethistory
# Recipe:: backend
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

## Include dependencies
include_recipe 'git'

## Parameters definition
repo_path = "#{Chef::Config[:file_cache_path]}/" + node['streethistory']['app_name']
source_path = "#{repo_path}/" + node['streethistory']['backend']['backend_src_dir']
deploy_path = node['streethistory']['backend']['deploy_dir']
resource_path = "#{source_path}/" + node['streethistory']['backend']['resources_dir']
artifact = "#{source_path}/target/" + node['streethistory']['backend']['artifact_id'] + ".war"
deployed_commit = node['streethistory']['work_dir'] + "/" + node['streethistory']['backend']['commit_ctrl']


## Create working directory
directory node['streethistory']['work_dir'] do
  owner "root"
  group "root"
  mode 00644
  action :create
end

## Clone git repository
git "#{repo_path}" do
  repository node['streethistory']['repository']
  action :sync
end

## Copy the configuration files before building the app
template "#{resource_path}/db.properties" do
  source "db.properties.erb"
  mode 0644
  owner "root"
  group "root"
  variables ({
    :db_server => node['streethistory']['database']['db_server'],
    :db_server_port => node['streethistory']['database']['db_server_port'],
    :db_username => node['streethistory']['db_username'],
    :db_password => node['streethistory']['db_password'],
    :db_initial_conex => node['streethistory']['backend']['db_initial_conex'],
    :db_max_conex => node['streethistory']['backend']['db_max_conex'],
    :db_name => node['streethistory']['db_name']
  })
end

template "#{resource_path}/streethistory.properties" do
  source "streethistory.properties.erb"
  mode 0644
  owner "root"
  group "root"
  variables ({
    :srid => node['streethistory']['srid'],
    :photo_url => node['streethistory']['photo_url']
  })
end

## Build the application
execute "build_app" do
  app_uptodate = <<-EOH
  cd #{repo_path}
  git log --format='%H' | head -n1 | grep `cat #{deployed_commit}`
  EOH
  cwd "#{source_path}"
  command "sudo #{node['maven']['m2_home']}/bin/mvn package && sudo cp #{artifact} #{deploy_path} && git log --format='%H' | head -n1 > #{deployed_commit}"
  action :run
  not_if app_uptodate
end

## Restart the app server 