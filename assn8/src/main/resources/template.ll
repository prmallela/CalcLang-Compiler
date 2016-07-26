; ModuleID = 'hello.c'
target datalayout = "e-m:o-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-apple-macosx10.11.0"

%struct.__sFILE = type { i8*, i32, i32, i16, i16, %struct.__sbuf, i32, i8*, i32 (i8*)*, i32 (i8*, i8*, i32)*, i64 (i8*, i64, i32)*, i32 (i8*, i8*, i32)*, %struct.__sbuf, %struct.__sFILEX*, i32, [3 x i8], [1 x i8], %struct.__sbuf, i32, i64 }
%struct.__sFILEX = type opaque
%struct.__sbuf = type { i8*, i32 }

<<strings>>
@__stdoutp = external global %struct.__sFILE*, align 8
@__stdinp = external global %struct.__sFILE*, align 8
@showInt.fmt = private unnamed_addr constant [4 x i8] c"%ld\00", align 1

; Function Attrs: nounwind ssp uwtable
define i32 @main() #0 {
<<code>>
}

declare i32 @printf(i8*, ...) #1

; Function Attrs: nounwind ssp uwtable
define i64 @intPow(i64 %base, i64 %exp) #0 {
  %1 = alloca i64, align 8
  %2 = alloca i64, align 8
  %result = alloca i64, align 8
  %i = alloca i64, align 8
  store i64 %base, i64* %1, align 8
  store i64 %exp, i64* %2, align 8
  store i64 1, i64* %result, align 8
  store i64 0, i64* %i, align 8
  br label %3

; <label>:3                                       ; preds = %11, %0
  %4 = load i64, i64* %i, align 8
  %5 = load i64, i64* %2, align 8
  %6 = icmp slt i64 %4, %5
  br i1 %6, label %7, label %14

; <label>:7                                       ; preds = %3
  %8 = load i64, i64* %1, align 8
  %9 = load i64, i64* %result, align 8
  %10 = mul nsw i64 %9, %8
  store i64 %10, i64* %result, align 8
  br label %11

; <label>:11                                      ; preds = %7
  %12 = load i64, i64* %i, align 8
  %13 = add nsw i64 %12, 1
  store i64 %13, i64* %i, align 8
  br label %3

; <label>:14                                      ; preds = %3
  %15 = load i64, i64* %result, align 8
  ret i64 %15
}

declare i32 @fflush(%struct.__sFILE*) #1

; Function Attrs: nounwind readnone
declare double @llvm.pow.f64(double, double) #2

declare i32 @puts(i8*) #1

; Function Attrs: nounwind readnone
declare double @floor(double) #3

; Function Attrs: nounwind readnone
declare double @sqrt(double) #3

; Function Attrs: nounwind ssp uwtable
define double @logWithBase(double %x, double %base) #0 {
  %1 = alloca double, align 8
  %2 = alloca double, align 8
  store double %x, double* %1, align 8
  store double %base, double* %2, align 8
  %3 = load double, double* %1, align 8
  %4 = call double @log(double %3) #2
  %5 = load double, double* %2, align 8
  %6 = call double @log(double %5) #2
  %7 = fdiv double %4, %6
  ret double %7
}

; Function Attrs: nounwind ssp uwtable
define i8* @strConcat(i8* %s1, i8* %s2) #0 {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %r = alloca i8*, align 8
  store i8* %s1, i8** %1, align 8
  store i8* %s2, i8** %2, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i64 @strlen(i8* %3)
  %5 = load i8*, i8** %2, align 8
  %6 = call i64 @strlen(i8* %5)
  %7 = add i64 %4, %6
  %8 = add i64 %7, 1
  %9 = call i8* @calloc(i64 %8, i64 1)
  store i8* %9, i8** %r, align 8
  %10 = load i8*, i8** %r, align 8
  %11 = load i8*, i8** %1, align 8
  %12 = load i8*, i8** %r, align 8
  %13 = call i64 @llvm.objectsize.i64.p0i8(i8* %12, i1 false)
  %14 = call i8* @__strcpy_chk(i8* %10, i8* %11, i64 %13) #6
  %15 = load i8*, i8** %r, align 8
  %16 = load i8*, i8** %2, align 8
  %17 = load i8*, i8** %r, align 8
  %18 = call i64 @llvm.objectsize.i64.p0i8(i8* %17, i1 false)
  %19 = call i8* @__strcat_chk(i8* %15, i8* %16, i64 %18) #6
  %20 = load i8*, i8** %r, align 8
  ret i8* %20
}

; Function Attrs: nounwind ssp uwtable
define i64 @parseInt(i8* %s) #0 {
  %1 = alloca i8*, align 8
  store i8* %s, i8** %1, align 8
  %2 = load i8*, i8** %1, align 8
  %3 = call i64 @atol(i8* %2)
  ret i64 %3
}

; Function Attrs: nounwind ssp uwtable
define i8* @showInt(i64 %i) #0 {
  %1 = alloca i64, align 8
  %fmt = alloca [4 x i8], align 1
  %r = alloca i8*, align 8
  store i64 %i, i64* %1, align 8
  %2 = bitcast [4 x i8]* %fmt to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* getelementptr inbounds ([4 x i8], [4 x i8]* @showInt.fmt, i32 0, i32 0), i64 4, i32 1, i1 false)
  %3 = call i8* @calloc(i64 50, i64 1)
  store i8* %3, i8** %r, align 8
  %4 = load i8*, i8** %r, align 8
  %5 = load i8*, i8** %r, align 8
  %6 = call i64 @llvm.objectsize.i64.p0i8(i8* %5, i1 false)
  %7 = getelementptr inbounds [4 x i8], [4 x i8]* %fmt, i32 0, i32 0
  %8 = load i64, i64* %1, align 8
  %9 = call i32 (i8*, i32, i64, i8*, ...) @__sprintf_chk(i8* %4, i32 0, i64 %6, i8* %7, i64 %8)
  %10 = load i8*, i8** %r, align 8
  ret i8* %10
}

; Function Attrs: nounwind ssp uwtable
define void @initRuntime() #0 {
  %1 = call i64 @time(i64* null)
  %2 = trunc i64 %1 to i32
  call void @srand(i32 %2)
  %3 = call i32 @rand()
  ret void
}

declare void @srand(i32) #1

declare i64 @time(i64*) #1

declare i32 @rand() #1

; Function Attrs: nounwind readnone
declare double @log(double) #3

declare i8* @calloc(i64, i64) #1

declare i64 @strlen(i8*) #1

; Function Attrs: nounwind
declare i8* @__strcpy_chk(i8*, i8*, i64) #4

; Function Attrs: nounwind readnone
declare i64 @llvm.objectsize.i64.p0i8(i8*, i1) #2

; Function Attrs: nounwind
declare i8* @__strcat_chk(i8*, i8*, i64) #4

; Function Attrs: nounwind ssp uwtable
define i8* @readLine() #0 {
  %N = alloca i32, align 4
  %r = alloca i8*, align 8
  %i = alloca i32, align 4
  store i32 128, i32* %N, align 4
  %1 = call i8* @calloc(i64 128, i64 1)
  store i8* %1, i8** %r, align 8
  %2 = load i8*, i8** %r, align 8
  %3 = load %struct.__sFILE*, %struct.__sFILE** @__stdinp, align 8
  %4 = call i8* @fgets(i8* %2, i32 127, %struct.__sFILE* %3)
  %5 = load i8*, i8** %r, align 8
  %6 = call i64 @strlen(i8* %5)
  %7 = sub i64 %6, 1
  %8 = trunc i64 %7 to i32
  store i32 %8, i32* %i, align 4
  %9 = load i32, i32* %i, align 4
  %10 = icmp sge i32 %9, 0
  br i1 %10, label %11, label %24

; <label>:11                                      ; preds = %0
  %12 = load i32, i32* %i, align 4
  %13 = sext i32 %12 to i64
  %14 = load i8*, i8** %r, align 8
  %15 = getelementptr inbounds i8, i8* %14, i64 %13
  %16 = load i8, i8* %15, align 1
  %17 = sext i8 %16 to i32
  %18 = icmp eq i32 %17, 10
  br i1 %18, label %19, label %24

; <label>:19                                      ; preds = %11
  %20 = load i32, i32* %i, align 4
  %21 = sext i32 %20 to i64
  %22 = load i8*, i8** %r, align 8
  %23 = getelementptr inbounds i8, i8* %22, i64 %21
  store i8 0, i8* %23, align 1
  br label %24

; <label>:24                                      ; preds = %19, %11, %0
  %25 = load i8*, i8** %r, align 8
  ret i8* %25
}

declare i8* @fgets(i8*, i32, %struct.__sFILE*) #1

; Function Attrs: nounwind ssp uwtable
define double @randomDouble() #0 {
  %1 = call i32 @rand()
  %2 = sitofp i32 %1 to double
  %3 = fdiv double %2, 0x41DFFFFFFFC00000
  ret double %3
}

declare i64 @atol(i8*) #1

; Function Attrs: argmemonly nounwind
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1) #5

declare i32 @__sprintf_chk(i8*, i32, i64, i8*, ...) #1

attributes #0 = { nounwind ssp uwtable "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="core2" "target-features"="+cx16,+fxsr,+mmx,+sse,+sse2,+sse3,+ssse3" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="core2" "target-features"="+cx16,+fxsr,+mmx,+sse,+sse2,+sse3,+ssse3" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind readnone }
attributes #3 = { nounwind readnone "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="core2" "target-features"="+cx16,+fxsr,+mmx,+sse,+sse2,+sse3,+ssse3" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="core2" "target-features"="+cx16,+fxsr,+mmx,+sse,+sse2,+sse3,+ssse3" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { argmemonly nounwind }
attributes #6 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"PIC Level", i32 2}
!1 = !{!"Apple LLVM version 7.3.0 (clang-703.0.29)"}
