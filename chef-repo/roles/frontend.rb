#
# Role name:: frontend
# 
# PostgreSQL database server for Street History app
#

name "frontend"
description "Frontend server"
run_list "recipe[nginx]", "recipe[streethistory::frontend]"
