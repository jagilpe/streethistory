#
# Role name:: base-ubuntu
# 
# Basic configuration for the Ubuntu servers
#

name "base-ubuntu"
description "Ubuntu server base"
run_list "recipe[apt]"