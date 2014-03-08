#!/bin/bash

# First we should check if Vagrant and VirtualBox are installed

if ! [ `command -v vagrant` ]; then
	echo "Vagrant not detected. Is it already installed?"
	exit 1
fi

if ! [ `command -v VirtualBox` ]; then
	echo "VirtualBox not detected. Is it already installed?"
	exit 2
fi

echo "Starting vagrant box"

vagrant up