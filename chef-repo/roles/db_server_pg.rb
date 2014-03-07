#
# Role name:: db_server_pg
# 
# PostgreSQL database server for Street History app
#

name "db_server_pg"
description "PostgreSQL database server"
run_list "recipe[postgresql::server]", "recipe[streethistory::database]"
default_attributes 'postgresql' => {
  "version" => "9.3",
  "config" => {
    "ssl" => "false"
  },
  "server" => {
    "packages" => ["postgresql-9.3", "postgresql-9.3-postgis-scripts"]
  }
}