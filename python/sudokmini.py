# Sudoku 4x4
import random

def create_board(): #1
    seed = [1,2,3,4]
    random.shuffle(seed)
    seed2,seed3,seed4 = seed[:],seed[:],seed[:]
    seed2[0],seed2[1],seed2[2],seed2[3]=seed[2],seed[3],seed[0],seed[1]
    seed3[0],seed3[1],seed3[2],seed3[3]=seed[1],seed[0],seed[3],seed[2]
    seed4[0],seed4[1],seed4[2],seed4[3]=seed[3],seed[2],seed[1],seed[0]
    return [seed]+[seed2]+[seed3]+[seed4]

def shuffle_ribbons(board) :
    top = board[:2]
    bottom = board[2:]
    random.shuffle(top)
    random.shuffle(bottom)
    return top + bottom

def transpose(board) : #2
    transposed = []  
    for _ in range(len(board)):
        transposed.append([])
    for j in range(0,4):
        for k in range(0,4):
            transposed[k].append(board[j][k])
    return transposed

def create_solution_board():
    board = create_board()
    board = shuffle_ribbons(board)
    board = transpose(board)
    board = shuffle_ribbons(board)
    board = transpose(board)
    return board

def get_level(): #3
    level = input("난이도 (상,중,하) 중에서 하나 선택하여 입력 : ")
    if level == '하':
        return 6
    if level == '중':
        return 8
    if level == '상':
        return 10
    while level not in {'상','중','하'}:
        level = input("난이도 (상,중,하) 중에서 하나 선택하여 입력 : ")
        if level == '하':
            return 6
        if level == '중':
            return 8
        if level == '상':
            return 10

def copy_board(board):
    board_clone = []
    for row in board :
        row_clone = row[:]
        board_clone.append(row_clone)
    return board_clone

def make_holes(board,no_of_holes): #4
    holeset = set()
    while no_of_holes >0:
        i = random.randint(0,3)
        j = random.randint(0,3)
        if board[i][j] == 0:
            continue
        board[i][j] = 0
        holeset.add((i,j))
        no_of_holes -= 1
    return (board, holeset) 

def show_board(board): #5
    print()
    print("S | 1 2 3 4")
    print("- + - - - -")
    print("1 |", end = " ")
    for i in range(0,4):
        if board[0][i] == 0:
            print(".", end = " ")
        else:
            print(board[0][i], end = " ")
    print()
    print("2 |", end = " ")
    for i in range(0,4):
        if board[1][i] == 0:
            print(".", end = " ")
        else:
            print(board[1][i], end = " ")
    print()
    print("3 |", end = " ")
    for i in range(0,4):
        if board[2][i] == 0:
            print(".", end = " ")
        else:
            print(board[2][i], end = " ")
    print()
    print("4 |", end = " ")
    for i in range(0,4):
        if board[3][i] == 0:
            print(".", end = " ")
        else:
            print(board[3][i], end = " ")
    print()
    print()

def get_integer(message,i,j): #6
    num = input(message)
    while not (num.isdigit() and i<=int(num)<=j and len(num.split("."))==1):
        num = input(message)
    return int(num)

def sudokmini():
    solution = create_solution_board()
    no_of_holes = get_level()
    puzzle = copy_board(solution)
    (puzzle,holeset) = make_holes(puzzle,no_of_holes)
    show_board(puzzle)
    while no_of_holes > 0:
        i = get_integer("가로줄번호(1~4): ",1,4) - 1
        j = get_integer("세로줄번호(1~4): ",1,4) - 1
        if (i,j) not in holeset:
            print("빈칸이 아닙니다.")
            continue
        n = get_integer("숫자(1~4): ",1,4)
        sol = solution[i][j]
        if n == sol:
            puzzle[i][j] = sol
            show_board(puzzle)
            holeset.remove((i,j))
            no_of_holes -= 1
        else:
            print(n,"가 아닙니다. 다시 해보세요.")
    print("잘 하셨습니다. 또 들려주세요.")





