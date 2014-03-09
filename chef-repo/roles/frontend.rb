#
# Role name:: frontend
# 
# Frontend server for Street History app
#

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
