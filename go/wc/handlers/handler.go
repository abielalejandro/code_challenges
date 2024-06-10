package handlers

import (
	"os"
)

type Handler interface {
	Info(*os.File) (string, error)
}
