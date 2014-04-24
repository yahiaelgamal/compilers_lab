JAVAC = javac
JAVA = java
CLASSPATH = . 

init:
	$(JAVAC) JLex/*.java
	$(JAVAC) java_cup/runtime/*.java
	$(JAVAC) java_cup/*.java

parse: parser.cup
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main parser.cup

lex: Lexer
	$(JAVA) JLex.Main Lexer

build: sym.java parser.java Lexer.java
	$(JAVAC) sym.java parser.java Lexer.java

all:
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main parser.cup
	$(JAVA) JLex.Main Lexer
	$(JAVAC) sym.java parser.java Lexer.java

run:
	$(JAVA) -classpath $(CLASSPATH) parser

test:
	$(JAVAC) -classpath $(CLASSPATH) AP.java && java AP
