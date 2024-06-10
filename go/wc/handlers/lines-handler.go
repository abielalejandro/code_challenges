package handlers

import (
	"bufio"
	"fmt"
	"os"
)

type LinesHandler struct {
	Active bool
}

func (h *LinesHandler) Info(f *os.File) (string, error) {
	if !h.Active {
		return "", nil
	}
	sc := bufio.NewScanner(f)
	sc.Split(bufio.ScanLines)
	lines := 0
	for sc.Scan() {
		lines = lines + 1
	}
	return fmt.Sprintf(" %v", lines), nil
}
