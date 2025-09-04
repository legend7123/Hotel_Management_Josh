#!/bin/bash
MODULE_PATH=/mnt/c/Users/cavar/javafx21/javafx-sdk-21.0.8/lib
SRC_DIR=main
OUT_DIR=out

# Compile
javac --module-path $MODULE_PATH --add-modules javafx.controls,javafx.fxml -d $OUT_DIR $(find $SRC_DIR -name "*.java")

# Run
java --module-path $MODULE_PATH --add-modules javafx.controls,javafx.fxml -cp $OUT_DIR main.Main
