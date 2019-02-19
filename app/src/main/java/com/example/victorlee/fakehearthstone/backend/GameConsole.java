package com.example.victorlee.fakehearthstone.backend;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.victorlee.fakehearthstone.backend.cards.Card;
import com.example.victorlee.fakehearthstone.backend.Exceptions.HandMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.NoCardsLeft;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Victor Lee on 6/16/2018.
 */

public class GameConsole extends BaseObservable {
    private Player currentPlayer;
    private Player opponentPlayer;
    private boolean testing;
    private String winnerMsg = null;

    public GameConsole(Player currentPlayer, Player opponentPlayer, boolean testing) {
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
        this.testing = testing;
    }

    @Bindable
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        notifyPropertyChanged(BR.currentPlayer);
        return;
    }

    @Bindable
    public Player getOpponentPlayer() {
        return this.opponentPlayer;
    }

    public void setOpponentPlayer(Player player) {
        this.opponentPlayer = player;
        notifyPropertyChanged(BR.opponentPlayer);
        return;
    }

    @Bindable
    public String getWinnerMsg() {
        return this.winnerMsg;
    }

    public void setWinnerMsg(String winnerMsg) {
        this.winnerMsg = winnerMsg;
        notifyPropertyChanged(BR.winnerMsg);
        return;
    }

    public void game(Stack<Card> player1Deck, Stack<Card> player2Deck) {
        try {
            initialize(player1Deck, player2Deck);
        } catch (Exception e) {
            System.out.println("Initialize deck failed.");
            System.out.println(e);
            return;
        }

        boolean endOfGame = false;
        int playersTurn = 1;
        Player currentPlayer = this.currentPlayer;
        Player opponentPlayer = this.opponentPlayer;

        while (!endOfGame) {
            try {
                currentPlayer.draw(1);

                endOfGame = callTurn(currentPlayer, opponentPlayer);

                if (currentPlayer.getLife() <= 0 && opponentPlayer.getLife() <= 0) {
                    System.out.println("Tie");
                    return;
                } else if (currentPlayer.getLife() <= 0) {
                    System.out.println(opponentPlayer.getName() + " wins");
                    return;
                } else if (opponentPlayer.getLife() <= 0) {
                    System.out.println(currentPlayer.getName() + " wins");
                    return;
                }

                if (playersTurn == 1) {
                    playersTurn = 2;
                    currentPlayer = this.opponentPlayer;
                    opponentPlayer = this.currentPlayer;
                } else if (playersTurn == 2) {
                    playersTurn = 1;
                    currentPlayer = this.currentPlayer;
                    opponentPlayer = this.opponentPlayer;
                }

                currentPlayer.incrementMagicCurr(1);
            } catch (Exception e) {}
        }
    }

    private void initialize(Stack<Card> player1Deck, Stack<Card> player2Deck) throws NoCardsLeft, HandMaxException {
        currentPlayer.getDeck().initialize(player1Deck);
        opponentPlayer.getDeck().initialize(player2Deck);

        if (!testing) {
            currentPlayer.getDeck().shuffle();
            opponentPlayer.getDeck().shuffle();
        }

        currentPlayer.draw(3);
        opponentPlayer.draw(4);
    }

    public void start(Stack<Card> player1Deck, Stack<Card> player2Deck) {
        try {
            initialize(player1Deck, player2Deck);
        } catch (Exception e) {
            System.out.println("Initialize deck failed.");
            System.out.println(e);
            return;
        }

        try {
            currentPlayer.draw(1);
            currentPlayer.incrementMagicCurr(1);
        } catch (Exception e) {}
    }

    public void command(String instruction) {
        String[] splitedCommand = instruction.split("\\s+");
        int numOfCommands = splitedCommand.length;
        String command = splitedCommand[0];

        if (command.equals("help")) {
            helpCommand();
        } else if (command.equals("end")) {
            Player temp = currentPlayer;
            setCurrentPlayer(opponentPlayer);
            setOpponentPlayer(temp);

            try {
                currentPlayer.draw(1);
            } catch (Exception e) {}
            currentPlayer.incrementMagicCurr(1);
            currentPlayer.setCanAttack(true);

            System.out.println(currentPlayer.getName() + " ends turn.");
        } else if (command.equals("quit")) {
            System.out.println("Good game.");
            setWinnerMsg(currentPlayer.getName() + " quits!");
            return;
        } else if (command.equals("attack")) {
            attackCommand(currentPlayer, opponentPlayer, splitedCommand, numOfCommands);
        } else if (command.equals("play")) {
            playCommand(currentPlayer, opponentPlayer, splitedCommand, numOfCommands);
        } else if (command.equals("use")) {
            useCommand(currentPlayer, opponentPlayer, splitedCommand, numOfCommands);
        } else {
            System.out.println("Invalid instruction: " + instruction + ". Please re-enter instruction.");
        }


        if (currentPlayer.getLife() <= 0 && opponentPlayer.getLife() <= 0) {
            System.out.println("Tie");
            setWinnerMsg("Tie");
        } else if (currentPlayer.getLife() <= 0) {
            System.out.println(opponentPlayer.getName() + " wins");
            setWinnerMsg(opponentPlayer.getName() + " wins!");
        } else if (opponentPlayer.getLife() <= 0) {
            System.out.println(currentPlayer.getName() + " wins");
            setWinnerMsg(currentPlayer.getName() + " wins!");
        }

        return;
    }

    private boolean callTurn(Player currentPlayer, Player opponentPlayer) {
        Scanner scanner = new Scanner(System.in);
        boolean endTurn = false;

        while (!endTurn) {
            String instruction = scanner.nextLine();

            String[] splitedCommand = instruction.split("\\s+");
            int numOfCommands = splitedCommand.length;
            if (numOfCommands < 1) {
                System.out.println("Command cannot be empty.");
                return false;
            }

            String command = splitedCommand[0];

            if (command.equals("help")) {
                helpCommand();
            } else if (command.equals("end")) {
                System.out.println(currentPlayer.getName() + " ends turn.");
                endTurn = true;
            } else if (command.equals("quit")) {
                System.out.println("Good game.");
                return true;
            } else if (command.equals("attack")) {
                attackCommand(currentPlayer, opponentPlayer, splitedCommand, numOfCommands);
            } else if (command.equals("play")) {
                playCommand(currentPlayer, opponentPlayer, splitedCommand, numOfCommands);
            } else if (command.equals("use")) {
                useCommand(currentPlayer, opponentPlayer, splitedCommand, numOfCommands);
            } else {
                System.out.println("Invalid instruction: " + instruction + ". Please re-enter instruction.");
            }
        }

        return false;
    }

    private void useCommand(Player currentPlayer, Player opponentPlayer, String[] splitedCommand, int numOfCommands) {
        int fieldIndex = -1;
        int targetFieldIndex = -1;
        int player = -1;

        try {
            if (numOfCommands == 2) {
                fieldIndex = Integer.parseInt(splitedCommand[1]);
                currentPlayer.use(fieldIndex, currentPlayer, opponentPlayer);
            } else if (numOfCommands == 4) {
                fieldIndex = Integer.parseInt(splitedCommand[1]);
                player = Integer.parseInt(splitedCommand[2]);
                targetFieldIndex = Integer.parseInt(splitedCommand[3]);
                Player targetPlayer = player == 1 ? currentPlayer : opponentPlayer;
                currentPlayer.use(fieldIndex, targetPlayer, targetFieldIndex);
            } else {
                System.out.println("Command play should have the either of the following forms");
                System.out.println("\tuse minion [target-player target-card] -- Use minion’s special ability, optionally targeting target-card owned by target-player.");
            }

        } catch (Exception e) {}
    }

    private void playCommand(Player currentPlayer, Player opponentPlayer, String[] splitedCommand, int numOfCommands) {
        int handIndex = -1;
        int fieldIndex = -1;
        int player = -1;

        try {
            if (numOfCommands == 2) {
                handIndex = Integer.parseInt(splitedCommand[1]);
                currentPlayer.play(handIndex, currentPlayer, opponentPlayer);
            } else if (numOfCommands == 4) {
                handIndex = Integer.parseInt(splitedCommand[1]);
                player = Integer.parseInt(splitedCommand[2]);
                fieldIndex = Integer.parseInt(splitedCommand[3]);
                Player targetPlayer = player == 1 ? currentPlayer : opponentPlayer;
                currentPlayer.play(handIndex, fieldIndex, targetPlayer);
            } else {
                System.out.println("Command play should have the either of the following forms");
                System.out.println("\tplay card [target-player target-card] -- Play card, optionally targeting target-card owned by target-player.");
            }

        } catch (Exception e) {}
    }

    private void helpCommand() {
        System.out.println("Commands: help -- Display this message.");
        System.out.println("\tend -- End the current player's turn");
        System.out.println("\t quit -- End the game.");
        System.out.println("\tattack minion other-minion -- Orders minion to attack other-minion.");
        System.out.println("\tattack minion -- Orders minion to attack the opponent.");
        System.out.println("\tplay card [target-player target-card] -- Play card, optionally targeting target-card owned by target-player.");
        System.out.println("\tuse minion [target-player target-card] -- Use minion’s special ability, optionally targeting target-card owned by target-player.");
        System.out.println("\tinspect minion -- View a minion’s card and all enchantments on that minion.");
        System.out.println("\thand -- Describe all cards in your hand.");
        System.out.println("\tboard -- Describe all cards on the board.");
    }

    private void attackCommand(Player currentPlayer, Player opponentPlayer, String[] splitedCommand, int numOfCommands) {
        int attacker;
        int target = -1;

        if (numOfCommands == 2) {
            attacker = Integer.parseInt(splitedCommand[1]);
        } else if (numOfCommands == 3) {
            attacker = Integer.parseInt(splitedCommand[1]);
            target = Integer.parseInt(splitedCommand[2]);
        } else {
            System.out.println("Command Attack should have the either of the following forms");
            System.out.println("\tattack minion other-minion -- Orders minion to attack other-minion.");
            System.out.println("\tattack minion -- Orders minion to attack the opponent.");
            return;
        }

        try {
            int attackersAttack;
            if (attacker == 0) {
                attackersAttack = currentPlayer.getAttack();
                if (!currentPlayer.isCanAttack()) {
                    System.out.println("Can't attack");
                }
            } else {
                attackersAttack = currentPlayer.getField().getAMonstersAttack(attacker);
                if (!currentPlayer.getField().getAMonstersCanAttack(attacker)) {
                    System.out.println("Can't attack");
                }
            }

            if (target == -1) {
                opponentPlayer.getAttacked(attackersAttack);
            } else {
                int targetsAttack = opponentPlayer.getField().getAMonstersAttack(target);
                opponentPlayer.attack(target, attackersAttack);
                currentPlayer.attack(attacker, targetsAttack);
            }

            if (attacker == 0) {
                currentPlayer.setCanAttack(false);
            } else {
                currentPlayer.getField().setAMonstersCanAttack(attacker, false);
            }
        } catch (Exception e) {}
    }
}
