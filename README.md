# Probability Calculator for N-sided Dice

This project is a Java terminal-based application that calculates the probability distribution when rolling `M` number of `N`-sided dice.

The user enters:
- `N` = number of sides per die
- `M` = number of dice rolled each time
- `K` = number of simulations

The program then:
- simulates rolling the dice
- records the sums
- calculates the probability of each possible sum
- displays the probability distribution

## Features
- Accepts user input for `N`, `M`, and `K`
- Simulates rolling `M` number of `N`-sided dice once
- Simulates rolling the dice `K` times
- Calculates the probability distribution of sums
- Displays results in a clean table
- Includes a run-again option
- Includes JUnit tests for core logic methods

## Tasks Implemented

### Task 1
Roll `M` number of `N`-sided dice once and return the sum.

### Task 2
Simulate rolling `M` number of `N`-sided dice `K` times and record the results.

### Task 3
Calculate the probability of each possible sum.

### Task 4
Allow the user to enter `N`, `M`, and `K`, then display the probability distribution.

## Project Structure

```
src/
|—— DiceProbabilityCalculator.java
|—— DiceProbabilityCalculatorTest.java