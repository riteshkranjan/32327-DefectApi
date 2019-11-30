# Setup
## use mysql
`
   create database defectappdb;
   create database userprofiledb;
`

## Build
build all binaries:  mvn clean install -DskipTests


## Docker related
run : mvn install dockerfile:build -DskipTests   
OR   
docker build -t riteshkranjan/<imagename> .   
run docker images and note down image id   


## Deploy
run : `sh deploy.sh` for single command to deploy
run : `sh stop.sh`  for stopping all servers. 


# Requirement:

## MS1:
### Regestration:  
    User{id,password,email,phno,address}  boolean
### Login:       
    {id,password}  boolean


## Ms2:
### Add Defects:  
     Defect{prod name, year of purchase, defectdetails,Datetime}    --status: created ----defectID
### Update defects: 
    {defect id, status}   boolean
### view defects:  
    {defect id}   Defect   
    {userid}   Defects objs   

## Ms3:-->ms2@resttemplate, ms1@feign client
###  viewprofile:  
     {userid}  user{name,mailid,address}+defects{..}

* DB: Mysql(DB design)
* Cloud config: git(public/private)
* Eureka:
* Zuul:
* Sleuth & zipkin: log


