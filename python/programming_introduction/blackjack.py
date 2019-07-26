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
    return deck[0],deck[ 1: ]

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

def blackjack():
    print("Welcome to SMaSH Casino!")
    chips = 0
    while True:
        print("-----")
        deck = fresh_deck()
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
            print("Chips =", chips)
            if more("More round? (y/n) "):
                continue
            else:
                break
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
                if more("More round? (y/n) "):
                    continue
                else:
                    break
            else:
                while score_dealer <= 16:
                    card, deck = hit(deck)
                    dealer.append(card)
                    score_dealer = count_score(dealer)
                if score_dealer > 21:
                    show_cards(dealer, "My cards are:")
                    print("I bust! You won.")
                    chips += 1
                    print("Chips =", chips)
                    if more("More round? (y/n) "):
                        continue
                    else:
                        break
                elif score_dealer == score_player:
                    show_cards(dealer, "My cards are:")
                    print("We draw.")
                    print("Chips =", chips)
                    if more("More round? (y/n) "):
                        continue
                    else:
                        break
                elif score_player > score_dealer:
                    show_cards(dealer, "My cards are:")
                    print("You won.")
                    chips += 1
                    print("Chips =", chips)
                    if more("More round? (y/n) "):
                        continue
                    else:
                        break
                else:
                    show_cards(dealer, "My cards are:")
                    print("I won.")
                    chips -= 1
                    print("Chips =", chips)
                    if more("More round? (y/n) "):
                        continue
                    else:
                        break
    print("Bye!")
