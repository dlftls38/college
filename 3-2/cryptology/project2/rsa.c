/*
 * @file    rsa.c
 * @author  작성자 이름 / 학번
 * @date    작성 일자
 * @brief   mini RSA implementation code
 * @details 세부 설명
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "rsa.h"
llint p, q, e, d, n;

/*
 * @brief     모듈러 덧셈 연산을 하는 함수.
 * @param     llint a     : 피연산자1.
 * @param     llint b     : 피연산자2.
 * @param     byte op    : +, - 연산자.
 * @param     llint n      : 모듈러 값.
 * @return    llint result : 피연산자의 덧셈에 대한 모듈러 연산 값. (a op b) mod n
 * @todo      모듈러 값과 오버플로우 상황을 고려하여 작성한다.
 */
 llint modAdd(llint a, llint b, byte op, llint n) {
     while(a>=n && n!=0){
         a-=n;
     }
     while(b>=n && n!=0){
         b-=n;
     }
     llint result = -n;
     result+=a;
     if(op=='+'){
         result+=b;
     }
     else if(op=='-'){
         result-=b;
     }
     while(result<0 && n!=0){
         result+=n;
     }
     return result;
 }

/*
 * @brief      모듈러 곱셈 연산을 하는 함수.
 * @param      llint x       : 피연산자1.
 * @param      llint y       : 피연산자2.
 * @param      llint n       : 모듈러 값.
 * @return     llint result  : 피연산자의 곱셈에 대한 모듈러 연산 값. (a x b) mod n
 * @todo       모듈러 값과 오버플로우 상황을 고려하여 작성한다.
 */
llint modMul(llint x, llint y, llint n) {
    while(x>=n && n!=0){
        x-=n;
    }
    while(y>=n && n!=0){
        y-=n;
    }
    llint result=0;
    while(y>0){
        if(y&1==1){
			result=modAdd(result, x, '+', n);
		}
        x=modAdd(x, x, '+', n);
        y>>=1;
    }
    return result;
}

/*
 * @brief      모듈러 거듭제곱 연산을 하는 함수.
 * @param      llint base   : 피연산자1.
 * @param      llint exp    : 피연산자2.
 * @param      llint n      : 모듈러 값.
 * @return     llint result : 피연산자의 연산에 대한 모듈러 연산 값. (base ^ exp) mod n
 * @todo       모듈러 값과 오버플로우 상황을 고려하여 작성한다.
               'square and multiply' 알고리즘을 사용하여 작성한다.
 */
llint modPow(llint base, llint exp, llint n) {
    while(base>=n && n!=0){
        base-=n;
    }
    llint result=1;
    while(exp>0){
        if(exp&1==1){
			result=modMul(result, base, n);
		}
        base=modMul(base, base, n);
        exp>>=1;
    }
    return result;
}

/*
 * @brief      입력된 수가 소수인지 입력된 횟수만큼 반복하여 검증하는 함수.
 * @param      llint testNum   : 임의 생성된 홀수.
 * @param      llint repeat    : 판단함수의 반복횟수.
 * @return     llint result    : 판단 결과에 따른 TRUE, FALSE 값.
 * @todo       Miller-Rabin 소수 판별법과 같은 확률적인 방법을 사용하여,
               이론적으로 4N(99.99%) 이상 되는 값을 선택하도록 한다.
 */
bool isPrime(llint testNum, llint repeat) {
    llint q=testNum-1;
    llint k=0;
    while(((q>>1)<<1)==q){
        k++;
        q>>=1;
    }
    for(llint i=0;i<repeat;i++){
        double random_value=WELLRNG512a();
        llint a=random_value*((n-2)-2)+2;
        llint mul_val=10;
        while(!(1<a && a<testNum-1)){
            a=random_value*mul_val;
            mul_val*=10;
        }
        if(modPow(a, q, testNum)==1){
            continue;
        }
        bool IsComposite=TRUE;
        for(llint j=0;j<k;j++){
            if(modPow(a, modPow(2, j, 0)*q, testNum)==testNum-1){
                IsComposite=FALSE;
                break;
            }
        }
        if(IsComposite){
            return FALSE;
        }
    }
    return TRUE;
}

/*
 * @brief       모듈러 역 값을 계산하는 함수.
 * @param       llint a      : 피연산자1.
 * @param       llint m      : 모듈러 값.
 * @return      llint result : 피연산자의 모듈러 역수 값.
 * @todo        확장 유클리드 알고리즘을 사용하여 작성하도록 한다.
 */
llint modInv(llint a, llint m) {
    llint r, r1, r2, t, t1, t2, q;
	r1=a;
	r2=m;
	t1=0;
    t2=1;
	while (r1!=1){
        llint quotient=0, temp_r2=r2, temp_r1=r1, cnt=1;
        while(temp_r2>=temp_r1){
            quotient+=cnt;
            while(temp_r2>=(temp_r1<<1) && modPow(2, 62, 0)>=(temp_r1<<1)){
                cnt<<=1;
                temp_r1<<=1;
            }
            temp_r2-=temp_r1;
            while(temp_r2<temp_r1 && temp_r1>r1){
                cnt>>=1;
                temp_r1>>=1;
            }
        }
		q=quotient;
		r=r2-r1*q;
		t=t1-t2*q;
		r2=r1;
		r1=r;
		t1=t2;
		t2=t;
	}
	if (t2 < 0){
        t2 = t2 + m;
    }
	return t2;
}

/*
 * @brief     RSA 키를 생성하는 함수.
 * @param     llint *p   : 소수 p.
 * @param     llint *q   : 소수 q.
 * @param     llint *e   : 공개키 값.
 * @param     llint *d   : 개인키 값.
 * @param     llint *n   : 모듈러 n 값.
 * @return    void
 * @todo      과제 안내 문서의 제한사항을 참고하여 작성한다.
 */
void miniRSAKeygen(llint *p, llint *q, llint *e, llint *d, llint *n) {
    while(TRUE){
		while(TRUE){
			*p=WELLRNG512a()*modPow(10, (llint)WELLRNG512a()*20+9, 0);
            printf("random-number1 %lld selected.\n",*p);
			if(isPrime(*p, 10)){
                printf("%lld may be Prime\n\n",*p);
				break;
			}
            else{
                printf("%lld is not Prime\n\n",*p);
            }
		}
		while(TRUE){
			*q=WELLRNG512a()*modPow(10, (llint)WELLRNG512a()*20+9, 0);
            printf("random-number2 %lld selected.\n",*q);
            if(isPrime(*q, 10)){
                printf("%lld may be Prime\n\n",*q);
				break;
			}
            else{
                printf("%lld is not Prime\n\n",*q);
            }
		}
		*n=modMul(*p, *q, modPow(2,62,0));
        if(modPow(2, 53, 0)<*n && *n<modPow(2,62,0)){
            printf("finally selected prime p, q = %lld, %lld.\n",*p, *q);
            printf("thus, n = %lld\n\n",*n);
            break;
        }
	}
	llint pi_n=((*p)-1)*((*q)-1);
	while(TRUE){
		*e =(llint)(WELLRNG512a()*modPow(10,(llint)WELLRNG512a()*20+13, 0))+2;
		if(*e>=pi_n){
            *e-=pi_n;
        }
        printf("e : %lld selected.\n\n",*e);
		if(1<*e && *e<pi_n && gcd(*e, pi_n)==1){
            break;
        }
	}
	*d=modInv(*e, pi_n);
    printf("\nd : %lld selected.\n\n",*d);
    printf("e, d, n, pi_n : %lld    %lld    %lld    %lld\n", *e, *d, *n, pi_n);
    printf("e*d mod pi_n  : %lld\n\n", modMul(*e, *d, pi_n));
}

/*
 * @brief     RSA 암복호화를 진행하는 함수.
 * @param     llint data   : 키 값.
 * @param     llint key    : 키 값.
 * @param     llint n      : 모듈러 n 값.
 * @return    llint result : 암복호화에 결과값
 * @todo      과제 안내 문서의 제한사항을 참고하여 작성한다.
 */
llint miniRSA(llint data, llint key, llint n) {
    if(data>n){
        printf("error: plain text is bigger than n\n");
        return -1;
    }
    llint result;
    printf(" input data : %lld\n", data);
	result = modPow(data, key, n);
    printf(" output data : %lld\n\n", result);
	return result;
}

llint gcd(llint a, llint b) {
    llint prev_a;
    llint prev_b;

    while(b != 0) {
        printf("GCD(%lld, %lld)\n", a, b);
        prev_a = a;
        a = b;
        prev_b = b;
        while(prev_a>=prev_b){
            while(prev_a>=(prev_b<<1) && modPow(2, 62, 0)>=(prev_b<<1)){
                prev_b<<=1;
            }
            prev_a-=prev_b;
            while(prev_a<prev_b && prev_b>b){
                prev_b>>=1;
            }
        }
        b = prev_a;
    }
    printf("GCD(%lld, %lld)\n\n", a, b);
    return a;
}

int main(int argc, char* argv[]) {
    byte plain_text[4] = {0x12, 0x34, 0x56, 0x78};
    llint plain_data, encrpyted_data, decrpyted_data;
    uint seed = time(NULL);
    memcpy(&plain_data, plain_text, 4);
    // 난수 생성기 시드값 설정
    seed = time(NULL);
    InitWELLRNG512a(&seed);
    // RSA 키 생성
    printf("mRSA key generator start.\n\n");
    miniRSAKeygen(&p, &q, &e, &d, &n);
    printf("0. Key generation is Success!\n ");
    printf("p : %lld\n q : %lld\n e : %lld\n d : %lld\n N : %lld\n\n", p, q, e, d, n);
    // // RSA 암호화 테스트
    plain_data=(llint)(WELLRNG512a()*modPow(10,(llint)WELLRNG512a()*20+15, 0));
    encrpyted_data = miniRSA(plain_data, e, n);
    printf("1. plain text : %lld\n", plain_data);
    printf("2. encrypted plain text : %lld\n\n", encrpyted_data);

    // RSA 복호화 테스트
    decrpyted_data = miniRSA(encrpyted_data, d, n);
    printf("3. cipher text : %lld\n", encrpyted_data);
    printf("4. Decrypted plain text : %lld\n\n", decrpyted_data);

    // 결과 출력
    printf("RSA Decryption: %s\n", (decrpyted_data == plain_data) ? "SUCCESS!" : "FAILURE!");

    return 0;
}
