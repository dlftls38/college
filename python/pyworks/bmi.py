#coding: cp949

while True:
	height = raw_input('신장(m)?:')
	if len(height) == 0:
		break                                        #엔터 키만 누르면 종료된다
	height = float(height)                               #입력은 문자열이므로 실수로 변환한다
	weight = float(raw_input('체중(kg)?:'))
	bmi = weight / pow(height,2)                         #내장 함수 pow로 제곱을 계산 할 수 있다
	print('BMI 값은 %0.1f입니다.' % bmi)                 #소수점 이하 첫째 자리까지 출력하도록 포맷 지정
	if bmi < 18.5:
		print('다소 말랐습니다.')
	elif 18.5 <= bmi < 25.0:
		print('표준체형입니다.')
	elif 25.0 <= bmi < 30.0:
		print('경도 비만입니다.')
	else:
		print('고도 비만입니다.')