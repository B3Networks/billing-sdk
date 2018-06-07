BIlling SDK
to build: gradle build

to use :


java -jar {path to jar}.jar commandName apikey type date 

commandName : right now only support "getDataCommand"


type : voice, sms, dnc, subscription


date: in format yyyy-mm-dd . For example 2018-05-06

The output will be put in "type".csv file at the same place of the jar fileß

