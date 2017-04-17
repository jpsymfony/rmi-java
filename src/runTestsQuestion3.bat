cd ..
start rmiregistry
cd tp_rmi
start java -cp . ServeurWeb8086
pause
start java -Djava.rmi.server.useCodebaseOnly=true -Djava.rmi.server.codebase=http://localhost:8086/ -cp . question3.MainObservable
pause
java -Djava.rmi.server.useCodebaseOnly=true -Djava.rmi.server.codebase=http://localhost:8086/ -cp . question3.MainObservers
