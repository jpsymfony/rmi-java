cd ..
start rmiregistry
cd tp_rmi
start java -cp . ServeurWeb8086
start java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://localhost:8086/ -cp . question2.MainObservable
java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://localhost:8086/ -cp . question2.MainObservers
