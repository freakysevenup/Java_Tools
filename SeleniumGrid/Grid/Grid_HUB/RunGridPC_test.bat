title RunGrid On PC Windows 8, 7

:: Written by Jesse Derochie jderochie@qaconsultants.com
:: 11/03/2016
:: version 1.0

@echo off
REM Open WebBrowser to Selenium Grid Console

start http://localhost:4444/grid/console#

REM Initialize the Grid

call programs/gridSetup.bat


