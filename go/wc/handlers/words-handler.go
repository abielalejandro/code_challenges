package handlers

import (
	"bufio"
	"fmt"
	"os"
)

type WordsHandler struct {
	Active bool
}

func (h *WordsHandler) Info(f *os.File) (string, error) {
	if !h.Active {
		return "", nil
	}
	sc := bufio.NewScanner(f)
	sc.Split(bufio.ScanWords)
	words := 0
	for sc.Scan() {
		words = words + 1
	}
	return fmt.Sprintf(" %v", words), nil
}
