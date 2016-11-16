@echo off
echo %1
cd ..\game
javac Pawn.java
cd..\resources
javah -jni -classpath ..\ game.Pawn
if "%1"=="" goto end
gcc -Wl,--add-stdcall-alias -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -shared -o %1.dll %1.c -march=x86-64
goto:eof
:end
