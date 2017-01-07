/*
 * fraction_13syeb.cpp
 * Author: Emily Bao
 */
#include <iostream>
#include "fraction_13syeb.h"
using namespace std;

/*Exception class that sends error message when user
enters a fraction with a zero value denominator
or input format error*/
FractionException::FractionException(const string& message):message(message) {};

string& FractionException::what(){return message;}

//Default constructor
Fraction::Fraction() : n(0), d(1) {}

//Constructor that takes a single int
Fraction::Fraction(int a) : n(a), d(1) {}

//Constructor that takes two ints
Fraction::Fraction(int a, int b) : n(a), d(b) {
	if (d == 0){
			throw FractionException("Error: denominator can't be zero!");
	}
	check();
}


void Fraction::check() {
    int temp;
    if (n > 0 && d >0 ) {
        temp = gcd (n,d);
    }
    else if (n < 0 && d < 0)
    {
        n = -n;
        d = -d;
        temp = gcd (n,d);
    }
    else if (n < 0){
        temp = gcd (-n,d);
    }
    else{
        n = -n;
        d = -d;
        temp = gcd (-n,d);
    }
    n = n / temp;
    d = d / temp;

}

//Finds greatest common denominator of two values
int gcd(int n, int m){
    if (m<=n && n%m == 0){
        return m;
    }
    if  (n<m){
        return gcd(m,n);
    }
    return gcd(m,n%m);
}

ostream& operator<<(ostream& outStream, const Fraction& frac){
    outStream << frac.n<<"/"<<frac.d;
    return outStream;
}
istream& operator>>(istream& inStream, Fraction& frac){
    string str;
    inStream >> str;
    size_t found=str.find('/');
    int n,d;
    if (found!=std::string::npos){
        string firstStr = str.substr (0,found);
        string secondStr = str.substr (found+1);
        stringstream stream1(firstStr);
        stringstream stream2(secondStr);
        // Throw exception for failing to convert string to int.
        if ((stream1 >> n).fail() || !(stream1 >> std::ws).eof()){
            throw FractionException("Illegal input");
        }
        if ((stream2 >> d).fail() || !(stream1 >> std::ws).eof()){
            throw FractionException("Illegal input");
        }
    }
    else{
        stringstream stream(str);
        // Throw exception for failing to convert string to int.
        if ((stream >> n).fail() || !(stream >> std::ws).eof())
        {
            throw FractionException("Illegal input");
        }
        d = 1;
    }
    frac.setNumerator(n);
    frac.setDenominator(d);
    return inStream;
}

//Accessor for numerator of a fraction object
int Fraction::numerator() const {
	return n;
}
//Accessor for denominator of a fraction object
int Fraction::denominator() const {
	return d;
}

void Fraction::setNumerator (const int num) {
    n=num;
}
void Fraction::setDenominator (const int den) {
    d=den;
}


// Overload + operator
Fraction operator + (const Fraction &frac1, const Fraction &frac2){
    int a,b,c,d;
    a = frac1.n;
    b = frac1.d;
    c = frac2.n;
    d = frac2.d;
    int g = gcd (b,d);
    int x = b / g, y = d / g;
    a *= y;
    c *= x;
    Fraction temp(a+c,b*d/g);
    return temp;
}
Fraction operator + (const Fraction &frac1,const int input){
    Fraction temp(input);
    return frac1+temp;
}
Fraction operator + (const int input,const  Fraction &frac1){
    Fraction temp(input);
    return temp+frac1;
}

// Overload - operator
Fraction operator - (const Fraction &frac1,const  Fraction &frac2){
    Fraction temp = -frac2;
    return frac1+temp;
}
Fraction operator - (const Fraction &frac1,const int input){
    Fraction temp(input);
    cout << input << temp << endl;
    return frac1-temp;
}
Fraction operator - (const int input,const  Fraction &frac1){
    Fraction temp(input);
    return temp - frac1;
}


// Overload * operator
Fraction operator * (const Fraction &frac1,const  Fraction &frac2){
    Fraction temp(frac1.n*frac2.n,frac1.d*frac2.d);
    return  temp;
}
Fraction operator* (const int input,const  Fraction &frac2){
    Fraction temp (input);
    return temp * frac2;
}
Fraction operator* (const Fraction &frac2,const  int input){
    Fraction temp (input);
    return temp * frac2;
}

// Overload / operator
Fraction operator /(const Fraction &frac1,const  Fraction &frac2) {
    Fraction temp (frac2.d,frac2.n);
    return temp * frac1;
}

Fraction operator / (const Fraction &frac1, int input) {
    Fraction temp (input);
    return frac1/temp;
}

Fraction operator / (const int input, const Fraction &frac1) {
    Fraction temp (input);
    return temp/frac1;
}

//Overload +=
Fraction &Fraction::operator+= (const Fraction &f) {
    *this = *this + f;
    return *this;
}

//Negation
Fraction operator - (const Fraction &frac1){
    return Fraction (frac1.n*-1,frac1.d);
}
//Preincrement
Fraction &Fraction::operator++ () {
    n+=d;
    return *this;
}

//Post-increment
Fraction Fraction::operator++ (int) {
    Fraction w (*this);
    ++(*this);
    return w;
}

// Overload boolean operators
bool operator > (const Fraction &frac1, const Fraction &frac2){
    int temp;
    temp  = gcd (frac1.d,frac2.d) ;
    return frac1.n*frac2.d/temp > frac2.n*frac2.d/temp;
}
bool operator < (const Fraction &frac1, const Fraction &frac2) {
    return frac2>frac1;
}
bool operator == (const Fraction &frac1, const Fraction &frac2) {
    return frac1.n==frac2.n&&frac1.d==frac2.d;
}
bool operator <= (const Fraction &frac1, const Fraction &frac2){
    return frac1==frac2 || frac1<frac2;
}
bool operator >= (const Fraction &frac1, const Fraction &frac2){
    return frac1>frac2 || frac1 == frac2;
}
bool operator != (const Fraction &frac1, const Fraction &frac2){
    return !(frac1==frac2);
}

bool operator > (const int input, const Fraction &frac1) {
    Fraction temp(input);
    return temp>frac1;
}
bool operator < (const int input, const Fraction &frac1){
    Fraction temp(input);
    return temp<frac1;
}
bool operator >= (const int input, const Fraction &frac1){
    Fraction temp(input);
    return temp>=frac1;
}
bool operator <= (const int input, const Fraction &frac1){
    Fraction temp(input);
    return temp<=frac1;
}
bool operator == (const int input, const Fraction &frac1){
    Fraction temp(input);
    return temp==frac1;
}
bool operator != (const int input, const Fraction &frac1){
    Fraction temp(input);
    return temp!=frac1;
}



bool operator > (const Fraction &frac1, const int input){
    Fraction temp(input);
    return frac1>temp;
}
bool operator < (const Fraction &frac1, const int input){
    Fraction temp(input);
    return frac1<temp;
}
bool operator >= (const Fraction &frac1, const int input){
    Fraction temp(input);
    return frac1>=temp;
}
bool operator <= (const Fraction &frac1, const int input){
    Fraction temp(input);
    return frac1<=temp;
}
bool operator == (const Fraction &frac1, const int input){
    Fraction temp(input);
    return frac1==temp;
}
bool operator != (const Fraction &frac1, const int input){
    Fraction temp(input);
    return frac1!=temp;
}





