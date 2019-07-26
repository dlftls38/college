class Line:
	def __init__(self, slp, x0, y0):
		self.slp = float(slp)
		self.x0 = float(x0)
		self.y0 = float(y0)

	def get_y(self, x):
		return self.slp * (x - self.x0) + self.y0

	def get_x(self, y)
		return self.x0 + (y - self.y0) / self.slp