import random
def fresh_deck():
    suits = {"Spade", "Heart", "Diamond", "Club"}
    ranks = {2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K", "A"}
    deck = []
    for s in suits:
        for r in ranks:
            card = {"suit" : s, "rank": r}
            deck.append(card)
    random.shuffle(deck)
    return deck

def hit(deck):
    if deck == []:
        deck = fresh_deck()
    return deck[0],deck[1:]

def count_score(cards):
    score = 0
    number_of_ace = 0
    for card in cards:
        rank = card['rank']
        if rank == 'A':
            score += 11
            number_of_ace += 1
        elif rank in {'J','Q','K'}:
            score += 10
        else:
            score += rank
    while score > 21 and number_of_ace > 0:
        score -= 10
        number_of_ace -= 1
    return score

def show_cards(cards,message):
    print(message)
    for card in cards:
        print(" ", card["suit"], card["rank"])

def more(message):
    answer = input(message)
    while not (answer == "y" or answer == "n"):
        answer = input(message)
    return answer == 'y'

def load_members():
    file = open("members.txt","r")
    members = {}
    for line in file:
        name, passwd, tries, wins, chips = line.strip('\n').split(',')
        members[name] = (passwd, int(tries), float(wins), int(chips))
    file.close()
    return members

def store_members(members):
    file = open("members.txt","w")
    names = members.keys()
    for name in names:
        passwd, tries, wins, chips = members[name]
        line = name + ',' + passwd + ',' + \
               str(tries) + ',' + str(wins) + ',' + \
               str(chips) + '\n'
        file.write(line)
    file.close()

def divide(x,y) :
    return x/y if y!= 0 else 0

def login(members):
    username = input("Enter your name : (4 letters max) ")
    while len(username) > 4:
        username = input("Enter your name : (4 letters max) ")
    trypasswd = input("Enter your password : ")
    if username in members:
        if trypasswd in members[username]:
            print("You played", members[username][1], "games and won", members[username][2], "of them.")
            print("Your all-time winning percentage is", "{0:.1f}".format(divide(members[username][2],members[username][1])*100), "%.")
            if members[username][3] >= 0:
                print("You have", members[username][3], "chips.")
            else :
                print("You owe", members[username][3], "chips.")
            return (username, members[username][1], members[username][2], members[username][3]), members
        else:
            return login(members)
    else:
        members[username] = (trypasswd,0,0,0)
        return (username, 0, 0, 0), members

def show_top5(members):
    print("-----")
    sorted_members = sorted(members.items(),key=lambda x:x[1][3],reverse=True)
    print("All-time Top 5 based on the number of chips earned")
    num = 0
    num2 = 0
    for lists in sorted_members:
        if  lists[1][3] > 0:
            print(num+1, ".", lists[0], ":", lists[1][3])
        else :
            num -= 1
        num += 1
        num2 += 1
        if num2 == 4 or num2 == len(members):
            break
   
def blackjack():
    print("Welcome to SMaSH Casino!")
    members = load_members()
    (username, tries, wins, chips), members = login(members)
    passwd = members[username][0]
    nowtries = 0
    nowwins = 0
    deck = fresh_deck()
    while True:
        print("-----")
        dealer = []
        player = []
        card, deck = hit(deck)
        player.append(card)
        card, deck = hit(deck)
        dealer.append(card)
        card, deck = hit(deck)
        player.append(card)
        card, deck = hit(deck)
        dealer.append(card)
        print("My cards are:")
        print(" ", "****", "**")
        print(" ", dealer[1]["suit"], dealer[1]["rank"])
        show_cards(player, "Your cards are:")
        score_player = count_score(player)
        score_dealer = count_score(dealer)
        if score_player == 21:
            print("Blackjack! You won.")
            chips += 2
            wins += 1
            nowwins += 1
            print("Chips =", chips)
        else:
            while score_player < 21 and more("Hit? (y/n) "):
                card, deck = hit(deck)
                player.append(card)
                score_player = count_score(player)
                print(" ", card["suit"], card["rank"])
            if score_player > 21:
                print("You bust! I won.")
                chips -= 1
                print("Chips =", chips)
            else:
                while score_dealer <= 16:
                    card, deck = hit(deck)
                    dealer.append(card)
                    score_dealer = count_score(dealer)
                show_cards(dealer,"My cards are:")
                if score_dealer > 21:
                    print("I bust! You won.")
                    chips += 1
                    wins +=1
                    nowwins += 1
                elif score_dealer == score_player:
                    print("We draw.")
                    wins += 0.5
                    nowwins += 0.5
                elif score_player > score_dealer:
                    print("You won.")
                    chips += 1
                    wins +=1
                    nowwins += 1
                else:
                    print("I won.")
                    chips -= 1
                print("Chips =", chips)
        nowtries += 1
        tries += 1
        if not more("Play more? (y/n) "):
             break
    members[username] = (passwd, tries, wins, chips)
    store_members(members)
    print("-----")
    print("You played", nowtries, "games and won", nowwins, "of them.")
    print("Your winning percentage today is", "{0:.1f}".format(divide(nowwins,nowtries)*100), "%.")
    show_top5(members)
    print("Bye!")
