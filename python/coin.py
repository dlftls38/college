print("동전합산 서비스에 오심을 환영합니다.\n가지고 계신 동전의 개수를 입력해주세요.")

print("")

오백원 = int(input("500원짜리? "))
백원 = int(input("100원짜리? "))
오십원 = int(input("50원짜리? "))
십원 = int(input("10원짜리? "))

총액 = 오백원*500 + 백원*100 + 오십원*50 + 십원*10

print("")

print("손님이 보유하고 있는 동전은 총", format(총액, ","), "원 입니다.")
print("저희 서비스를 이용해주셔서 감사합니다.")
print("또 들려주세요.")
