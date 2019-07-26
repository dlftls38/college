# Sudoku 9x9
import random

def create_board(): # modify
    seed = [1,2,3,4,5,6,7,8,9]
    random.shuffle(seed)
    n1 = seed[0]
    n2 = seed[1]
    n3 = seed[2]
    n4 = seed[3]
    n5 = seed[4]
    n6 = seed[5]
    n7 = seed[6]
    n8 = seed[7]
    n9 = seed[8]
    
    return [[n1,n2,n3,n4,n5,n6,n7,n8,n9],
            [n4,n5,n6,n7,n8,n9,n1,n2,n3],
            [n7,n8,n9,n1,n2,n3,n4,n5,n6],
            [n2,n3,n1,n5,n6,n4,n8,n9,n7],
            [n5,n6,n4,n8,n9,n7,n2,n3,n1],
            [n8,n9,n7,n2,n3,n1,n5,n6,n4],
            [n3,n1,n2,n6,n4,n5,n9,n7,n8],
            [n6,n4,n5,n9,n7,n8,n3,n1,n2],
            [n9,n7,n8,n3,n1,n2,n6,n4,n5]]
  

def shuffle_ribbons(board) : # modify
    top = board[:3]
    middle = board[3:6]
    bottom = board[6:]
    random.shuffle(top)
    random.shuffle(middle)
    random.shuffle(bottom)
    return top + middle + bottom

def transpose(board) : 
    transposed = []  
    for _ in range(len(board)):
        transposed.append([])
    for row in board:
        i = 0
        for entry in row:
            transposed[i].append(entry)
            i += 1
    return transposed

def create_solution_board():
    board = create_board()
    board = shuffle_ribbons(board)
    board = transpose(board)
    board = shuffle_ribbons(board)
    board = transpose(board)
    return board

def get_level():
    level = input("난이도 (상,중,하) 중에서 하나 선택하여 입력 : ")
    while level not in {'상','중','하'}:
        level = input("난이도 (상,중,하) 중에서 하나 선택하여 입력 : ")
    if level == '하':
        return 28
    elif level == '중':
        return 34
    else:
        return 40

def copy_board(board):
    board_clone = []
    for row in board :
        row_clone = row[:]
        board_clone.append(row_clone)
    return board_clone

def make_holes(board,no_of_holes): 
    holeset = set()
    while no_of_holes >0:
        i = random.randint(0,8)
        j = random.randint(0,8)
        if board[i][j] == 0:
            continue
        board[i][j] = 0
        holeset.add((i,j))
        no_of_holes -= 1
    return (board, holeset) 

def show_board(board): # modify
    print()
    print('S','|','1','2','3','4','5','6','7','8','9')
    print('-','+','-','-','-','-','-','-','-','-','-')
    i = 1
    for row in board:
        print(i,end=' ')
        print('|',end=' ')
        for entry in row:
            if entry == 0:
                print('.',end=' ')
            else:
                print(entry,end=' ')
        print()
        i += 1
    print()

def get_integer(message,i,j): 
    num = input(message)
    while not (num.isdigit() and i<=int(num)<=j and len(num.split("."))==1):
        num = input(message)
    return int(num)

def sudok():
    solution = create_solution_board()
    no_of_holes = get_level()
    puzzle = copy_board(solution)
    (puzzle,holeset) = make_holes(puzzle,no_of_holes)
    show_board(puzzle)
    while no_of_holes > 0:
        i = get_integer("가로줄번호(1~9): ",1,9) - 1
        j = get_integer("세로줄번호(1~9): ",1,9) - 1
        if (i,j) not in holeset:
            print("빈칸이 아닙니다.")
            continue
        n = get_integer("숫자(1~9): ",1,9)
        sol = solution[i][j]
        if n == sol:
            puzzle[i][j] = sol
            show_board(puzzle)
            holeset.remove((i,j))
            no_of_holes -= 1
        else:
            print(n,"가 아닙니다. 다시 해보세요.")
    print("잘 하셨습니다. 또 들려주세요.")





