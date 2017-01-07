%{
Name: Siyi Emily Bao
Student Number: 10103388
%}

function [Bspace, Nspace] =a1(A,b)
    %check if matrix dimension is smaller than 1
    [m,m]=size(A);    
    if m<1
        error('Matrix dimension is smaller than 1')
    end
    
    %append b to matrix A
    C =[A b];
    
    %find the reduced row echelon form of C
    D = rref(C);
    
    %take the last column
    Bspace = D(:,end);
    
    %null solution
    Nspace=null(A,'r');
