#coding:cp949
import dice

num = input('4, 6, 8, 12, 20 �� ��� �ֻ����� �����Ͻðڽ��ϱ�? : ') # input �Լ��� ���� �޾Ƶ���
my_dice = dice.Dice(num)    # ����ڿ� �ֻ���
cpu_dice = dice.Dice(num)    # ��ǻ�Ϳ� �ֻ���

my_pip = my_dice.shoot()    # pip�� �ֻ����� ���� �ǹ���
cpu_pip = cpu_dice.shoot()    # ��ǻ�Ϳ� �ֻ����� �� 
# ���� ���� ȭ�鿡 ���, ���ڴ� str �Լ��� ����� ���ڿ��� ����
print('CPU : ' + str(cpu_pip) + '  ��� : ' + str(my_pip))
# ��Ȳ�� ���� �޼����� ������
if my_pip > cpu_pip:
	print('�����մϴ�. ����� �¸��Դϴ�!')
elif my_pip < cpu_pip:
	print('�ȵƳ׿�! ����� �й��Դϴ�.')
else:
	print('�����ϴ�')
