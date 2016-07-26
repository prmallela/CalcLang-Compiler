LLC=/usr/local/opt/llvm37/bin/llc-3.7
CLANG=clang -Wall
AS=as

default: hello

hello: hello.obj
	$(CC) -o $@ $^

%.obj: %.s
	$(AS) -o $@ $^

%.s: %.ll
	$(LLC) -o $@ $^

%.ll: %.c
	$(CLANG) -S -emit-llvm $^

.PRECIOUS: %.ll %.s %.obj

clean:
	$(RM) hello hello.{ll,s,obj}

