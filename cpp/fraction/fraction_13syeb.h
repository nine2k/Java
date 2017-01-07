/*
 * Header file for CISC 320 Assignment 2
 * Author: Emily Bao
 */

#include <iostream>
#include <string>
#include <sstream>
using namespace std;

/*
 * This class throws an exception message when the
 * denominator is set as zero
 */
class FractionException {
	public:
		FractionException(const string& message);
		string& what();
	private:
		string message;
};



int gcd(int a, int b);

class Fraction {
private:
    int n;
    int d;
    void check();

public:
    Fraction();
    Fraction(const int a);
    Fraction(const int a, const int b);
    int numerator() const;
    int denominator() const;
    void setNumerator(const int n);
    void setDenominator(int d);


    friend ostream& operator<<(ostream& out, const Fraction& frac);
    friend istream& operator>>(istream& in, Fraction& frac);

    friend Fraction operator + (const Fraction &frac1,const Fraction &frac2);
    friend Fraction operator + (const Fraction &frac1,const int input);
    friend Fraction operator + (const int input,const Fraction &frac1);

    friend Fraction operator - (const Fraction &frac1);
    friend Fraction operator - (const Fraction &frac1,const Fraction &frac2);
    friend Fraction operator - (const Fraction &frac1,const int input);
    friend Fraction operator - (const int input,const Fraction &frac1);

    friend Fraction operator * (const Fraction &frac1,const Fraction &frac2);
    friend Fraction operator * (const Fraction &frac1,const int input);
    friend Fraction operator * (const int input,const Fraction &frac1);

    friend Fraction operator / (const Fraction &frac1,const Fraction &frac2);
    friend Fraction operator / (const Fraction &frac1,int input);
    friend Fraction operator / (const int input, const Fraction &frac1);

    Fraction & operator++();
    Fraction operator++(int);

    Fraction& operator+=(const Fraction& f);

    friend bool operator > (const Fraction &frac1, const Fraction &frac2);
    friend bool operator < (const Fraction &frac1, const Fraction &frac2);
    friend bool operator >= (const Fraction &frac1, const Fraction &frac2);
    friend bool operator <= (const Fraction &frac1, const Fraction &frac2);
    friend bool operator == (const Fraction &frac1, const Fraction &frac2);
    friend bool operator != (const Fraction &frac1, const Fraction &frac2);

    friend bool operator > (const int input, const Fraction &frac1);
    friend bool operator < (const int input, const Fraction &frac1);
    friend bool operator >= (const int input, const Fraction &frac1);
    friend bool operator <= (const int input, const Fraction &frac1);
    friend bool operator == (const int input, const Fraction &frac1);
    friend bool operator != (const int input, const Fraction &frac1);

    friend bool operator > (const Fraction &frac1, const int input);
    friend bool operator < (const Fraction &frac1, const int input);
    friend bool operator >= (const Fraction &frac1, const int input);
    friend bool operator <= (const Fraction &frac1, const int input);
    friend bool operator == (const Fraction &frac1, const int input);
    friend bool operator != (const Fraction &frac1, const int input);



};

