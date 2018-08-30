@echo "Starting Selenium test1"

set projectLocation=C:\Users\dilli.r.kumaran.e.l\Automation\GitPull\Hasbro15Feb\Hasbro\FlexPLM
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\SanitySuite2018.xml
pause


