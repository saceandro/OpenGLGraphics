.PHONY: check-syntax
check-syntax:
	javac -Xlint $(CHK_SOURCES)

RECENT_JAVA := $(shell ls -1t *.java | head -1)

test-fast: $(basename $(RECENT_JAVA)).run

%.run:	%.class
	java $(basename $<)
%.class:	%.java
	javac $<
