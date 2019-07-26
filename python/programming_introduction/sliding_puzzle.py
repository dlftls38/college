def create_init_board():
    return [[' ','15','14','13'],['12','11','10','9'],['8','7','6','5'],['4','3','2','1']]

def set_goal_board():
    return [['1','2','3','4'],['5','6','7','8'],['9','10','11','12'],['13','14','15',' ']]

def print_board(k):
    for i in range(0,4):
        for j in range(0,4):
            if j == 3:
                print(k[i][j].rjust(2))
            else:
                print(k[i][j].rjust(2),end=' ')
def get_number():
    get = input("Type the number you want to move (Type 0 to quit): ")
    while not get.isdigit() or not 0<= int(get) < 16:
        get = input("Type the number you want to move (Type 0 to quit): ")
    return int(get)

def find_position(num,board):
    for i in range(0,4):
        for j in range(0,4):
            if board[i][j] == ' ':
                continue
            elif int(board[i][j]) == num:
                return (i,j)

def move(pos,empty,board):
    if pos[0] == empty[0]+1 and pos[1] == empty[1] or\
       pos[0] == empty[0] and pos[1] == empty[1]+1 or\
       pos[0] == empty[0]-1 and pos[1] == empty[1] or\
       pos[0] == empty[0] and pos[1] == empty[1]-1:
        board[pos[0]][pos[1]],board[empty[0]][empty[1]] =\
        board[empty[0]][empty[1]],board[pos[0]][pos[1]]
        return (pos,board)
    else:
        print("Can't move! Try again.")
        return (empty,board)
        
def sliding_puzzle():
    board = create_init_board()
    goal = set_goal_board()
    empty = (0,0)
    while True:
        print_board(board)
        if board == goal:
            print("Congratulations!")
            break
        num = get_number()
        if num == 0:
            break
        pos = find_position(num,board)
        (empty,board) = move(pos,empty,board)
    print("Please come again.")
