#
# Cookbook Name:: streethistory
# Recipe:: frontend
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
include_recipe "nodejs::install_from_package"
include_recipe "nodejs::npm"
include_recipe "rsync"
include_recipe "git"

## Parameters definition
repo_path = "#{Chef::Config[:file_cache_path]}/" + node['streethistory']['app_name']
source_path = "#{repo_path}/" + node['streethistory']['frontend']['frontend_src_dir']
deployed_commit = node['streethistory']['work_dir'] + "/" + node['streethistory']['frontend']['commit_ctrl']
config_file = "#{source_path}/" + node['streethistory']['frontend']['config_file']
if node['streethistory']['frontend']['rest_ssl']
  rest_url = "https://"
else
  rest_url = "http://"
end
rest_url = "#{rest_url}" + node['streethistory']['frontend']['rest_server_name'] + ":" + node['streethistory']['frontend']['rest_server_port'] + "/" + node['streethistory']['frontend']['rest_location']

## Create working directory
directory node['streethistory']['work_dir'] do
  owner "root"
  group "root"
  mode 00644
  action :create
end

## Create www root directory
directory node['nginx']['default_root'] do
  owner "www-data"
  group "www-data"
  mode 00644
  recursive true
  action :create
end

## Clone git repository
git "#{repo_path}" do
  repository node['streethistory']['repository']
  action :sync
end

## Update the config file
template "#{config_file}" do
  source "config.js.erb"
  owner "root"
  group "root"
  mode 0644
  variables ({
    :restful_url => "#{rest_url}"
  })
end

## Install compass
gem_package "compass" do
  action :install
  options("--no-rdoc --no-ri")
end

## Install yeoman
execute "yeoman_install" do
  command "sudo npm install -g yo"
  action :run
end

## Build the Angular app
execute "frontend_build" do
  app_uptodate = <<-EOH
  cd #{repo_path}
  git log --format='%H' | head -n1 | grep `cat #{deployed_commit}`
  EOH
  cwd "#{source_path}"
  command "sudo npm install && sudo bower --allow-root install && grunt build && rsync -a --owner=#{node['streethistory']['frontend']['web_server_user']} #{source_path}/dist/ #{node['streethistory']['frontend']['web_doc_root']} && git log --format='%H' | head -n1 > #{deployed_commit}"
  action :run
  not_if app_uptodate 
end

