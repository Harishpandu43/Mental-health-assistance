#!/bin/bash

if [ -d "mental_health_assistance" ] 
then
    echo "Python virtual environment exists." 
else
    python3 -m venv mental_health_assistance
fi

source mental_health_assistance/bin/activate


pip3 install -r requirements.txt

if [ -d "logs" ] 
then
    echo "Log folder exists." 
else
    mkdir logs
    touch logs/error.log logs/access.log
fi

sudo chmod -R 777 logs
