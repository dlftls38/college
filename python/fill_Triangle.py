from tkinter import*
tk = Tk()
canvas = Canvas(tk, width=210, height=210)
canvas.pack()


canvas.create_polygon(10,10,50,10,50,50,fill="red",outline='black')
canvas.create_polygon(50,10,100,10,50,50,fill="yellow",outline='black')
canvas.create_polygon(100,10,150,10,150,50,fill="green",outline='black')
canvas.create_polygon(150,10,200,10,150,50,fill="purple",outline='black')
canvas.create_polygon(10,50,50,50,50,100,fill="gold",outline='black')
canvas.create_polygon(50,50,100,50,50,100,fill="silver",outline='black')
canvas.create_polygon(100,50,150,50,150,100,fill="blue",outline='black')
canvas.create_polygon(150,50,200,50,150,100,fill="pink",outline='black')
canvas.create_polygon(10,100,50,100,50,150,fill="red",outline='black')
canvas.create_polygon(50,100,100,100,50,150,fill="yellow",outline='black')
canvas.create_polygon(100,100,150,100,150,150,fill="green",outline='black')
canvas.create_polygon(150,100,200,100,150,150,fill="purple",outline='black')
canvas.create_polygon(10,150,50,150,50,200,fill="gold",outline='black')
canvas.create_polygon(50,150,100,150,50,200,fill="silver",outline='black')
canvas.create_polygon(100,150,150,150,150,200,fill="blue",outline='black')
canvas.create_polygon(150,150,200,150,150,200,fill="pink",outline='black')


