package handlers

import (
	"bufio"
	"fmt"
	"os"
)

type CharsHandler struct {
	Active bool
}

func (h *CharsHandler) Info(f *os.File) (string, error) {
	if !h.Active {
		return "", nil
	}
	sc := bufio.NewScanner(f)
	sc.Split(bufio.ScanLines)
	line := ""
	chars := 0
	for sc.Scan() {
		line = sc.Text()
		chars = chars + len(line)
	}
	return fmt.Sprintf(" %v", chars), nil
}
