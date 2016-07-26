#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define INTFMT "%ld\n"
#define FLOATFMT "%.6le\n"

void initRuntime();
long intPow(long base, long exp);
double logWithBase(double, double base);
char* strConcat(const char*, const char*);
char* readLine();
double randomDouble();
long parseInt(const char*);
char* showInt(long);

int main()
{
    initRuntime();
    long x = 2;
    long y = 3;
    printf(INTFMT, x/y);
    printf(INTFMT, intPow(x,y));
    fflush(stdout);
    double a = 3.141;
    double b = 9.999;
    printf(FLOATFMT, a/b);
    printf(FLOATFMT, pow(a,b));
    puts(a? "True" : "False");
    printf(INTFMT, (long)floor(a));
    printf(FLOATFMT, sqrt(a));
    printf(FLOATFMT, logWithBase(32.0, 2.0));
    puts(strConcat("Hello,", " world"));
    if(b < 10) puts("Cool");
    //puts(readLine());
    printf(FLOATFMT, randomDouble());
    printf(INTFMT, parseInt("9931"));
    puts(showInt(9931));
    return 0;
}

void initRuntime()
{
    srand(time(NULL)); rand();
}

long intPow(long base, long exp)
{
    long result = 1; // Slow exponentiation!
    for(long i = 0; i < exp; i++) {
        result *= base;
    }
    return result;
}

double logWithBase(double x, double base)
{
    return log(x)/log(base);
}

char* strConcat(const char* s1, const char* s2)
{
    char* r = calloc(strlen(s1) + strlen(s2) + 1, sizeof(char));
    strcpy(r, s1);
    strcat(r, s2);
    return r;
}

char* readLine()
{
    const int N = 128;
    char* r = calloc(N, sizeof(char));
    fgets(r, N-1, stdin);
    int i = strlen(r) - 1;
    if(i >= 0 && r[i] == '\n') r[i] = '\0'; // discard newline
    return r;
}

double randomDouble()
{
    return (double)rand() / (double)RAND_MAX;
}

long parseInt(const char* s)
{
    return atol(s);
}

char* showInt(long i)
{
    char fmt[] = {'%','l','d','\0'};
    char* r = calloc(50, sizeof(char));
    sprintf(r, fmt, i);
    return r;
}
