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

## Database default parameters
default['streethistory']['repository'] = 'https://github.com/jagilpe/streethistory'
default['streethistory']['db_name'] = 'db_streethistory'
default['streethistory']['db_username'] = 'sh_user'
default['streethistory']['db_password'] = 'sh_user'
default['streethistory']['db_schema_sql'] = 'database/sh_create_schema.sql'
default['streethistory']['db_data_init_sql'] = 'database/sh_load_test_data.sql'