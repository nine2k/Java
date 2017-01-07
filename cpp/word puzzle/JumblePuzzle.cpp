/*
 * Author: Siyi Emily Bao
 * Puzzle game that supports 3 levels
 */

#include "JumblePuzzle.h"
#include <time.h>
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

// Exception class
BadJumbleException::BadJumbleException(const string& m) : message(m){}
string& BadJumbleException::what(){return message;}


JumblePuzzle::JumblePuzzle(const string& targetWord, const string& level){
	strLen = targetWord.length();

	if (strLen < 3 || strLen > 10){
		throw BadJumbleException("[Error]: Target word has to be 3-10 characters in length");
	}
	//Set size of puzzle based on difficulty level
	if (level == "easy" || level == "Easy"){
		//square array 2 times length of word
		strLen = strLen*2;
	}else if (level == "medium" || level == "Medium"){
		//square array 3 times length of word
		strLen = strLen*3;
	}else if (level == "hard" || level == "Hard"){
		//square array 4 times length of word
		strLen = strLen*4;
	}else{
		throw BadJumbleException("[Error]: Please enter a valid level");
	}
	puzzle = new char*[strLen];

	// Create individual arrays on the heap
	for (int i = 0; i < strLen; i++){
		puzzle[i] = new char[strLen];
	}
	const char* letters = "abcdefghijklmnopqrstuvwxyz";
	// create puzzle with random letters
	srand(time(NULL));

	for (int i = 0; i < strLen; i++)
		for (int j = 0; j < strLen; j++)
			//rand() % 25 produces rand numbers from 0-25
			//which corresponds to the 25 char alphabet in letters
			puzzle[i][j] = (char) letters[rand() % 25];

	// hide word
	row = rand() % strLen;
	col = rand() % strLen;

	// position first letter of word into puzzle
	puzzle[row][col] = targetWord.at(0);

	//string direc[4] = {"n", "e", "s", "w"};
	string direc("nesw");
	bool placedWord = 0;

	while (!placedWord){
		// Use a random direction
		int num = rand() % 4; //rand num between 0-3
		direction = direc[num];

		if (direction == 'n'){

			for (int i = 1; i < targetWord.length(); i++){

				if ((row-i) < 0)
					break;
				//last letter in word
				else if (i == (targetWord.length() - 1)) {
					puzzle[row-i][col] = targetWord.at(i);
					placedWord = 1;
				}else{
					puzzle[row-i][col] = targetWord.at(i);
				}
			}
		}
		else if (direction == 'e'){
			// direction east, to the right
			for (int i = 1; i < targetWord.length(); i++){
				if ((col+i) > (strLen-1))
					break;
				else if (i == (targetWord.length() - 1)){
					puzzle[row][col+i] = targetWord.at(i);
					placedWord = 1;
				}else
					puzzle[row][col+i] = targetWord.at(i);
			}
		}
		else if (direction == 's'){
			// direction south, facing down
			for (int i = 1; i < targetWord.length(); i++){
				if ((row+i) > (strLen-1))
					break;
				else if (i == (targetWord.length() - 1)){
					puzzle[row+i][col] = targetWord.at(i);
					placedWord = 1;
				}else
					puzzle[row+i][col] = targetWord.at(i);
			}
		}else {
			// direction west, to the left
			for (int i = 1; i < targetWord.length(); i++){
				if ( (col-i) < 0 )
					break;
				else if (i == (targetWord.length() - 1)){
					puzzle[row][col-i] = targetWord.at(i);
					placedWord = 1;
				}else
					puzzle[row][col-i] = targetWord.at(i);
			}
		}
	}
}

// Copy constructor
JumblePuzzle::JumblePuzzle(const JumblePuzzle& right)
{
	strLen = right.getSize();
	row = right.getRowPos();
	col = right.getColPos();
	direction = right.getDirection();

	puzzle = new char*[strLen];

	// individual arrays
	for (int i = 0; i < strLen; i++)
		puzzle[i] = new char[strLen];

	charArrayPtr* rightJumble = right.getJumble();

	for (int i = 0; i < strLen; i++)
	{
		for (int j = 0; j < strLen; j++)
			puzzle[i][j] = rightJumble[i][j];
	}
}

// Assignment Overloading
JumblePuzzle& JumblePuzzle::operator=(const JumblePuzzle& right)
{
	if (this != &right) // make sure we dont assign to ourself
	{
		row = right.getRowPos();
		col = right.getColPos();
		direction = right.getDirection();
		strLen = right.getSize();

		// Delete old Jumble
		for (int i = 0; i < strLen; i++)
			delete[] puzzle[i];
		delete[] puzzle;

		// Copy new puzzle over
		puzzle = new char*[strLen];

		// Create individual arrays on the heap
		for (int i = 0; i < strLen; i++)
			puzzle[i] = new char[strLen];

		charArrayPtr* rightJumble = right.getJumble();

		for (int i = 0; i < strLen; i++)
		{
			for (int j = 0; j < strLen; j++)
				puzzle[i][j] = rightJumble[i][j];
		}

	}
	return *this;
}

// destructor
JumblePuzzle::~JumblePuzzle()
{
	for (int i = 0; i < strLen; i++)
		delete puzzle[i];

	delete[] puzzle;
}


// JumblePuzzle Accessors
charArrayPtr* JumblePuzzle::getJumble() const
{
	charArrayPtr* newJumble = new char*[strLen];

	for (int i = 0; i < strLen; i++)
		newJumble[i] = new char[strLen];

	for (int i = 0; i < strLen; i++)
	{
		for (int j = 0; j < strLen; j++)
			newJumble[i][j] = puzzle[i][j];
	}

	return newJumble;
}

int JumblePuzzle::getSize() const { return strLen; }
int JumblePuzzle::getRowPos() const { return row; }
int JumblePuzzle::getColPos() const { return col; }
char JumblePuzzle::getDirection() const { return direction; }
