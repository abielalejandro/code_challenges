package handlers

import (
	"fmt"
	"os"
)

type BytesHandler struct {
	Active bool
}

func (h *BytesHandler) Info(f *os.File) (string, error) {
	if !h.Active {
		return "", nil
	}

	info, err := f.Stat()
	if err != nil {
		return "", err
	}
	return fmt.Sprintf(" %v", info.Size()), nil
}
