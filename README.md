# gb-jdbk-chat
database authorization 
Client

"C:\Program Files\Java\jdk1.8.0_291\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.2.1\lib\idea_rt.jar=58278:C:\Program Files\JetBrains\IntelliJ IDEA 2021.2.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_291\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\rt.jar;C:\Java\gb-jdbk-chat\target\classes;C:\Users\Alex\.m2\repository\org\xerial\sqlite-jdbc\3.36.0.3\sqlite-jdbc-3.36.0.3.jar" ru.gb.client.ClientRunner
Поздравляем с успешной регистрацией.
Войдите через форму авторизации. 
CLIENT: Send auth message
java.io.EOFException
	at java.io.DataInputStream.readUnsignedShort(DataInputStream.java:340)
	at java.io.DataInputStream.readUTF(DataInputStream.java:589)
	at java.io.DataInputStream.readUTF(DataInputStream.java:564)
	at ru.gb.client.Controller.lambda$connect$0(Controller.java:65)
	at java.lang.Thread.run(Thread.java:748)
Exception in thread "Thread-4" java.lang.RuntimeException: java.io.EOFException
	at ru.gb.client.Controller.lambda$connect$0(Controller.java:102)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.io.EOFException
	at java.io.DataInputStream.readUnsignedShort(DataInputStream.java:340)
	at java.io.DataInputStream.readUTF(DataInputStream.java:589)
	at java.io.DataInputStream.readUTF(DataInputStream.java:564)
	at ru.gb.client.Controller.lambda$connect$0(Controller.java:65)
	... 1 more

Process finished with exit code 0

Server

"C:\Program Files\Java\jdk1.8.0_291\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.2.1\lib\idea_rt.jar=58273:C:\Program Files\JetBrains\IntelliJ IDEA 2021.2.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_291\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_291\jre\lib\rt.jar;C:\Java\gb-jdbk-chat\target\classes;C:\Users\Alex\.m2\repository\org\xerial\sqlite-jdbc\3.36.0.3\sqlite-jdbc-3.36.0.3.jar" ru.gb.server.ServerRunner
SERVER: Server start...
java.sql.SQLException: Query returns results
	at org.sqlite.jdbc3.JDBC3PreparedStatement.executeUpdate(JDBC3PreparedStatement.java:92)
	at ru.gb.server.SimpleAuthService.getNicknameByLoginAndPassword(SimpleAuthService.java:30)
	at ru.gb.server.ClientHandler.authenticate(ClientHandler.java:58)
	at ru.gb.server.ClientHandler.lambda$new$0(ClientHandler.java:32)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Exception in thread "pool-1-thread-1" java.lang.RuntimeException: java.sql.SQLException: Query returns results
	at ru.gb.server.ClientHandler.authenticate(ClientHandler.java:75)
	at ru.gb.server.ClientHandler.lambda$new$0(ClientHandler.java:32)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.sql.SQLException: Query returns results
	at org.sqlite.jdbc3.JDBC3PreparedStatement.executeUpdate(JDBC3PreparedStatement.java:92)
	at ru.gb.server.SimpleAuthService.getNicknameByLoginAndPassword(SimpleAuthService.java:30)
	at ru.gb.server.ClientHandler.authenticate(ClientHandler.java:58)
	... 4 more
SERVER: Client  logout...

Process finished with exit code 130
