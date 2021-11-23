ubuntu:		#Make for Ubuntu / Wsl
	javac -d classfiles *.java
	java -cp classfiles Test
doc:		#Make for creating Javadoc
	javadoc -d javadocfiles *.java
#Suleyman Golbol 