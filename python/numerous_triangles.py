from tkinter import*

from random import*

tk = Tk()

w = randrange(1000)

h = randrange(1000)

canvas = Canvas(tk, width=w, height=h)

canvas.pack()

colors = ['red','green','blue','yellow','orange','white','purple']

def random_triangle():
    p1 = randrange(w)
    p2 = randrange(h)
    p3 = randrange(w)
    p4 = randrange(h)
    p5 = randrange(w)
    p6 = randrange(h)
    color = choice(colors)
    canvas.create_polygon(p1, p2, p3, p4, p5, p6, fill=color, outline="")

for x in range(0,100):
    random_triangle()
    
    
    
