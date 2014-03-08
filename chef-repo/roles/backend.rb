#
# Role name:: backend
# 
# PostgreSQL database server for Street History app
#

name "backend"
description "Backend server"
run_list "recipe[maven]", "recipe[java]", "recipe[tomcat]", "recipe[streethistory::backend]"
default_attributes 'maven' => {
    'setup_bin' => true
  },
  'java' => {
    'jdk_version' => '7'
  },
  'tomcat' => {
    'base_version' => '7'
  }
