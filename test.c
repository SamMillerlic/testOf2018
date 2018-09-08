#include<stdio.h>
#include<string.h>
int main(void)
{
	int a=1379,a1,a2,a3,a4,b;
	a1=(a/1000)%10;
	a2=(a/100)%10;
	a3=(a/10)%10;
	a4=a%10;
	b=167/1000;
	printf("%d%d%d%d,%d",a1,a2,a3,a4,b);
	return 0;
 } 
