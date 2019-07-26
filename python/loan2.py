"""
# 대출 상환금 계산 서비스
# 대출금 상환금액을 계산해주는 프로그램
#
# 입력: 원금(principal) - 백만이상 정수만 허용
#       상환기간(years) - 1이상 정수만 허용
#       연이자율(interest rate) - 0.0에서 9.9 사이의 부동소수점수 만 허용
# 출력: 연상환금액, 월상환금액, 상환금액의 총계
#
# 작성자: 조일신
# 작성날짜: 2014년 12월 19일 (version 1.0)
"""
def print_intro():
    print("대출 상환금 계산 서비스에 오심을 환영합니다.")
    
def get_int(question, number):
    p_y = input(question)
    while not (p_y.isdigit() and int(p_y) >= number) :
        p_y = input(question)
    return int(p_y)
    
def get_interest(question, min, max):
    interest = input(question)
    k = interest.partition(".")
    while not ((interest.isdigit() and len(interest)==1 or\
           k[0].isdigit() and k[2].isdigit() and len(k[0]) == len(k[2]) == 1 or\
           k[0].isdigit() and len(k[0]) == 1 and k[2] == '' or\
           k[2].isdigit() and len(k[2]) == 1 and k[0] == '' ) and\
           min <= float(interest) < max ) or float(interest) == 0 :
        interest = input(question)
        k = interest.partition(".")
    return float(interest)

def print_result(d,principal,interest,years):
    print("")
    print("대출 상환금 내역을 알려드리겠습니다.")
    print("대출원금:", principal, "원")
    print("연 이자율", interest*100, "%로", years, "년동안 상환")
    print("1년에 한번씩 상환하시면 매년", d, "원씩 지불하셔야 합니다.")
    print("1달에 한번씩 상환하시면 매월", int(d/12), "원씩 지불하셔야 합니다.")
    print("상환 완료시까지 총 상환금액은", d * years, "원 입니다.")
    print("")

def stop():
    cont = input("계속하시겠습니까? (y/n) ")
    while not (cont == "y" or cont == "n"):
        cont = input("계속하시겠습니까? (y/n) ")
    return cont == "n"

def print_outtro():
    print("저희 서비스를 이용해주셔서 감사합니다.")
    print("또 들려주세요.")
    
def loan():
    print_intro()
    while True:
        principal = get_int("대출원금(원)? (1000000 이상) ", 1000000)
        years = get_int("상환기간(년)? (1 이상) ", 1)
        interest = get_interest("이자율(%)? ", 0.1, 10) / 100
        compound = (1 + interest)**years
        d = int(compound * principal * interest / (compound - 1))
        print_result(d,principal,interest,years)
        if stop():
            print_outtro()
            break
