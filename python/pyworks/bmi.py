#coding: cp949

while True:
	height = raw_input('����(m)?:')
	if len(height) == 0:
		break                                        #���� Ű�� ������ ����ȴ�
	height = float(height)                               #�Է��� ���ڿ��̹Ƿ� �Ǽ��� ��ȯ�Ѵ�
	weight = float(raw_input('ü��(kg)?:'))
	bmi = weight / pow(height,2)                         #���� �Լ� pow�� ������ ��� �� �� �ִ�
	print('BMI ���� %0.1f�Դϴ�.' % bmi)                 #�Ҽ��� ���� ù° �ڸ����� ����ϵ��� ���� ����
	if bmi < 18.5:
		print('�ټ� �������ϴ�.')
	elif 18.5 <= bmi < 25.0:
		print('ǥ��ü���Դϴ�.')
	elif 25.0 <= bmi < 30.0:
		print('�浵 ���Դϴ�.')
	else:
		print('�� ���Դϴ�.')