package main

import (
	"fmt"
	"os"

	"github.com/abielalejandro/wcc/handlers"
	"github.com/abielalejandro/wcc/parameters"
)

type FileManager struct {
	params   *parameters.OptionParameters
	handlers []handlers.Handler
	file     string
}

func NewFileManager(p *parameters.OptionParameters, f string) *FileManager {
	fm := &FileManager{
		params:   p,
		handlers: make([]handlers.Handler, 0),
		file:     f,
	}
	hasOptions := p.HasOptions()
	fm.handlers = append(fm.handlers, &handlers.BytesHandler{Active: p.Bytes || !hasOptions})
	fm.handlers = append(fm.handlers, &handlers.LinesHandler{Active: p.Lines || !hasOptions})
	fm.handlers = append(fm.handlers, &handlers.WordsHandler{Active: p.Words || !hasOptions})
	fm.handlers = append(fm.handlers, &handlers.CharsHandler{Active: p.Chars || !hasOptions})

	return fm
}

func (fm *FileManager) Execute() {
	for _, h := range fm.handlers {
		file, e := os.Open(fm.file)
		if e != nil {
			continue
		}
		info, e := h.Info(file)
		if e != nil {
			continue
		}
		fmt.Printf("%v", info)
	}
}
