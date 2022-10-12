1 - I create schema in file schema.sql 2- I create 3 table for drone and medication and audit 3- you can use swagger to
know all APIs 4- I used H2 for in memory database 5- I used log4j for logging 6- I create periodic task to audit drones
battery levels every 10 minutes 7- for registering a drone required it exist in drone controller for create drone 8- for
loading a drone with medication items required that exist in medication controller that making create medication should
had its drone so I load it in creation of medication. 9- for checking loaded medication items for a given drone make
drone projection to return all drones data. 10- for checking available drones for loading I make new field remainWeight
that for check if this drone can load or not 11- check drone battery level for a given drone you can get that by 2 API
one for information of drone and another for just get its battery information. 12- I make any drone start to get
medication its status become loading 13- I do validation for drone from being loaded with more weight that it can carry.
14- I do validation for Prevent the drone from being in LOADING state if the battery level is below 25% 15- If you need
to build this project in any environment first if you will change database change it form pom.xml then configuration
properties then extract jar form this project by run this command in terminal "mvn clean install"
16- after that you will run this schema.sql on new database of environment and then start jar on this environment 