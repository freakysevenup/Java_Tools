title ChromeNode

:: Written by Jesse Derochie jderochie@qaconsultants.com
:: 11/03/2016
:: version 1.0

:: TODO:// As the browser gets updated to new versions the version will need to change

REM set the number of instances and the port from parameters passed to this 
:: Batch file, if none passed assume the default value

REM ipaddress=172.16.50.14
set ipaddress=%1
set instances=%2
set port=%3
set version=54

IF [%1]==[] (set ipaddress=localhost)
IF [%2]==[] (set instances=1)
IF [%3]==[] (set port=5666)

REM initiate and Register this node with the Selenium Grid Hub

java -jar jar/selenium-server-standalone-3.0.1.jar -role node -port %port% -hub http://%ipaddress%:4444/grid/register -browser browserName="chrome",version=%version%,platform=WINDOWS,maxInstances=%instances% -hubHost http://%ipaddress%:4444 -host %ipaddress%

