@echo off
if not exist target mkdir target
if not exist target\classes mkdir target\classes


echo compile classes
javac -nowarn -d target\classes -sourcepath jvm -cp "d:\code\java\jni4net\lib\jni4net.j-0.8.8.0.jar"; "jvm\myiccardreader\myCardReader.java" 
IF %ERRORLEVEL% NEQ 0 goto end


echo myIcCardReader.j4n.jar 
jar cvf myIcCardReader.j4n.jar  -C target\classes "myiccardreader\myCardReader.class"  > nul 
IF %ERRORLEVEL% NEQ 0 goto end


echo myIcCardReader.j4n.dll 
csc /nologo /warn:0 /t:library /out:myIcCardReader.j4n.dll /recurse:clr\*.cs  /reference:"D:\code\2017\C#\APP\JNmyIcCardReader\myIcCardReader\bin\Debug\myIcCardReader.dll" /reference:"D:\code\Java\jni4net\lib\jni4net.n-0.8.8.0.dll"
IF %ERRORLEVEL% NEQ 0 goto end


:end
