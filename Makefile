
build_java:
	cd java && javac -d build *.java

run_java: build_java
	cd java && java -cp build Wc $@
