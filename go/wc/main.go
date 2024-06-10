package main

import (
	"flag"
	"fmt"
	"os"

	"github.com/abielalejandro/wcc/parameters"
)

func main() {
	params := parameters.NewOptionParameters()

	flag.BoolVar(&params.Help, "help", false, "Help for wcc")
	flag.BoolVar(&params.Bytes, "bytes", false, "Count bytes from input")
	flag.BoolVar(&params.Chars, "chars", false, "Count chars from input")
	flag.BoolVar(&params.Words, "words", false, "Count words from input")
	flag.BoolVar(&params.Lines, "lines", false, "Count lines from input")
	flag.Parse()

	if params.Help {
		fmt.Println("wcc <options> <FILE>")
		fmt.Println("Options:")
		flag.PrintDefaults()
		return
	}

	file := os.Args[len(os.Args)-1]

	if file != "" {
		_, err := os.Open(file)
		if err != nil {
			//log.Fatal("It is not a valid file")
			CreateTempFileFromStdIn()
			return
		}
	}

	fm := NewFileManager(params, file)
	fm.Execute()
}
