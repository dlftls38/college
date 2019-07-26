# 대출 상환금 계산 서비스
# 대출금 상환금액을 계산해주는 프로그램
#
# 입력: 원금(principal) - 백만이상 정수만 허용
#      상환기간(years) - 1이상 정수만 허용
#      연이자율(interest rate) - 0.1~9.9 범위의 정수 또는 부동소수점수 만 허용
# 출력: 연상환금, 월상환금, 상환금 총액
#
# 작성자: 조일신
# 작성날짜: 2015년 3월 20일 (version 1.0)

print("대출 상환금 계산 서비스에 오심을 환영합니다.")

# 입력

p = input("대출원금이 얼마인가요? (백만이상) ")
while not (p.isdigit() and int(p)>=1000000):
    p = input("대출원금이 얼마인가요? (백만이상) ")
    
y = input("상환기간은 몇년인가요? (1년이상 연단위) ")
while not (y.isdigit() and int(y)>=1):
    y = input("상환기간은 몇년인가요? (1년이상 연단위) ")
    
r = input("이자율은 몇% 인가요? (0.1~9.9) ")
k = r.partition('.')
while not (r.isdigit() and len(r)==1 or\
           k[0].isdigit() and k[2].isdigit() and len(k[0]) == len(k[2]) == 1 or\
           k[0].isdigit() and len(k[0]) == 1 and k[2] == '' or\
           k[2].isdigit() and len(k[2]) == 1 and k[0] == '')or\
           r=='0' or r=='0.0' or r=='.0' or r=='0.' :
    
     r = input("이자율은 몇% 인가요? (0.1~9.9) ")
     k = r.partition('.')
    

# 상환금 계산

p = int(p)
y = int(y)
r = float(r)/100
d = r*p*(1+r)**y/((1+r)**y-1)

# 출력

print("대출 상환금 내역을 알려드리겠습니다.")
print("대출원금:", p, "원")
print("연 이자율", r*100, "%로", y, "년동안 상환")
print("1년에 한번씩 상환하시면 매년", round(d), "원씩 지불하셔야 합니다.")
print("1달에 한번씩 상환하시면 매월", round(d/12), "원씩 지불하셔야 합니다.")
print("상환 완료시까지 총 상환금액은", round(d*y), "원 입니다.")
print("저희 서비스를 이용해주셔서 감사합니다.")
print("또 들려주세요.")
