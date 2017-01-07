/*
 * Author: Siyi Emily Bao
 * Header for JumblePuzzle.cpp
 *
 */
#ifndef JUMBLEPUZZLE_H_
#define JUMBLEPUZZLE_H_

#include <iostream>
#include <string>
#include <sstream>
using namespace std;
typedef char* charArrayPtr;


class JumblePuzzle{
	public:
		JumblePuzzle(const string&, const string&);
		JumblePuzzle(const JumblePuzzle&);
		~JumblePuzzle();
		JumblePuzzle& operator=(const JumblePuzzle&);
		charArrayPtr* getJumble() const;
		int getSize() const;
		int getRowPos() const;
		int getColPos() const;
		char getDirection() const;

	private:
		int strLen;
		charArrayPtr* puzzle;
		int row;
		int col;
		char direction;
};

class BadJumbleException{
	public:
		BadJumbleException(const string&);
		string& what();
	private:
		string message;
};

#endif /* JUMBLEPUZZLE_H_ */

