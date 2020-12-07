#!/bin/bash

mvn spring-boot:run &
sleep 15 
cd bloodstream-client 
npm run ng serve
