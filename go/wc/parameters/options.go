package parameters

type OptionParameters struct {
	Help  bool
	Chars bool
	Bytes bool
	Words bool
	Lines bool
}

func (o *OptionParameters) HasOptions() bool {
	return o.Bytes || o.Chars || o.Lines || o.Words
}

func NewOptionParameters() *OptionParameters {
	return &OptionParameters{}
}
