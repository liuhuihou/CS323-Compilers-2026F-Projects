ANTLR_JAR  := libs/antlr-4.13.2-complete.jar
OUTPUT_DIR := src/main/java/generated
CLASS_DIR  := bin
SOURCE_FILES = $(shell find src/main/java -name '*.java')

GRAMMARS := $(basename $(wildcard *.g4))

.DEFAULT_GOAL := all
all: generate compile

define ANTLR_RULE
$(OUTPUT_DIR)/$(1)/$(1)Parser.java: $(1).g4
	mkdir -p $(OUTPUT_DIR)
	@echo Building $(1)
	java -jar $(ANTLR_JAR) -visitor -o $(OUTPUT_DIR)/$(1) -package generated.$(1) $$<
endef

$(foreach grammar,$(GRAMMARS),$(eval $(call ANTLR_RULE,$(grammar))))

generate: $(foreach grammar,$(GRAMMARS),$(OUTPUT_DIR)/$(grammar)/$(grammar)Parser.java)

compile: generate
	mkdir -p $(CLASS_DIR)
	javac -cp $(ANTLR_JAR) -d $(CLASS_DIR) $(SOURCE_FILES)

run: compile
	java -cp $(CLASS_DIR):$(ANTLR_JAR) Main

clean:
	rm -rf $(OUTPUT_DIR) $(CLASS_DIR)

jar:
	mvn jar:jar


