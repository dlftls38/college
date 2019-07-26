#coding:utf-8

import turtle
import math
import random

class Line:
	def __init__(self, slp, x0, y0): 
		self.slp = float(slp)
		self.x0 = float(x0)
		self.y0 = float(y0)

	def get_y(self, x):
		return self.slp * (x - self.x0) + self.y0

	def get_x(self, y):
		return self.x0 + (y - self.y0) / self.slp


class Geobuk(turtle.Turtle):
	def __init__(self):
		super(Geobuk, self).__init__()
		self.shape('turtle')
		self.shapesize(2,2)
		self.radians()                               # 각도 지정을 호도법으로 변경               
		
		self.width(10)                               # 궤적 폭을 10으로 설정
		self.getscreen().bgcolor('gray')             # 배경을 회색(gray)으로(청소 전의 느낌)
		self.pencolor('white')                       # 궤적 색을 흰색으로(청소 후의 느낌)
		
	def hit_wall(self):
		xx = self.window_width() / 2.0               # 윈도우 폭의 절반이 x 좌표의 최댓값
		yy = self.window_height() / 2.0              # 윈도우 높이의 절반이 y좌표의 최댓값
			
		line = Line(math.tan(self.heading()),self.xcor(),self.ycor())
		rand_angle = math.pi * random.random()
		if self.towards(-xx,yy) > self.heading() >= self.towards(xx,yy):
			des_x = line.get_x(yy)
			des_y = yy
			turn_angle = self.heading() + rand_angle
		elif self.towards(-xx,-yy) > self.heading() >= self.towards(-xx,yy):
			des_x = -xx
			des_y = line.get_y(-xx)
			turn_angle = self.heading() - 0.5 * math.pi + rand_angle
		elif self.towards(xx,-yy) > self.heading() >= self.towards(-xx,-yy):
			des_x = line.get_x(-yy)
			des_y = -yy
			turn_angle = self.heading() - rand_angle
		else:
			des_x = xx
			des_y = line.get_y(xx)
			turn_angle = self.heading() - 0.5 * math.pi - rand_angle
		self.goto(des_x,des_y)            # 벽에 닿을 때까지 이동
		self.right(turn_angle)            # 회전해서 안쪽으로 방향을 돌림
 
	def run(self):
		while True:
			self.hit_wall()

	def click_on_move(self, x, y):
		self.goto(x, y)
		
